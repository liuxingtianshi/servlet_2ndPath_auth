package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.icss.dto.Buyinfo;
import com.icss.dto.TurnPage;
import com.icss.entity.TBuyDetail;
import com.icss.entity.TBuyRecord;
import com.icss.entity.TUser;

public class UserDao  extends BaseDao{
	
	/**
	 * У��������û����������ݿ����Ƿ����ͬ��
	 * @param uname
	 * @return  true��ʾ������
	 * @throws Exception
	 */
	public boolean validUserName(String uname) throws Exception{
		boolean bRet = false;
		
		String sql = "select * from tuser where uname = ?";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, uname);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
		   bRet = true;	
		}
		rs.close();
		ps.close();
		
		return bRet;	
	}
	
	/**
	 * ��ȡ�û��Ĺ����¼
	 * @param uname
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	public List<Buyinfo> getBuyinfos(String uname,Date beginDate,Date endDate,TurnPage tp) throws Exception{
		List<Buyinfo> buyinfos = new ArrayList<Buyinfo>();
		
		String sql = "select u.uname,bk.bname,d.count,bk.price,bk.press,bk.author,br.buytime,br.allmoney,br.allcount " +
				     " from tbuydetail d,tbuyrecord br,tbook bk,tuser u " +
				     " where br.bid = d.buyid  and bk.isbn = d.isbn  and u.uname = br.uname";
		if(uname != null && !uname.equals("")){
			sql = sql + " and u.uname like '%" + uname + "%'";
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		if(beginDate != null){
			sql = sql + " and buytime >= to_date('" + sf.format(beginDate) + "','yyyy-mm-dd')";
		}
		if(endDate != null){
			sql = sql + " and buytime <= to_date('" + sf.format(endDate) + "','yyyy-mm-dd')";
		}
		tp.allRecordCount = this.getOrcaleTurnPageAllCount(sql);               //������������ļ�¼����
		tp.allPages = (tp.allRecordCount-1)/tp.OnePageCount +1 ;
		if(tp.currentPageNum < 1)
			tp.currentPageNum = 1;		
		if(tp.currentPageNum > tp.allPages){
			tp.currentPageNum = tp.allPages;
		}
		int iStart=0,iEnd=0;
		iStart = (tp.currentPageNum-1)*tp.OnePageCount +1; 
		iEnd = iStart + tp.OnePageCount;
		String newSql = this.getOracleTurnPageSql(sql, iStart, iEnd);	
	
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(newSql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Buyinfo info = new Buyinfo();
			info.setAllcount(rs.getInt("allcount"));
			info.setAllmoney(rs.getDouble("allmoney"));
			info.setAuthor(rs.getString("author"));
			info.setBname(rs.getString("bname"));
			info.setBuytime(rs.getTimestamp("buytime"));
			info.setCount(rs.getInt("count"));
			info.setPress(rs.getString("press"));
			info.setPrice(rs.getDouble("price"));
			info.setUname(rs.getString("uname"));
			buyinfos.add(info);
		}
		rs.close();
		ps.close();
		
		return buyinfos;
	}
	
	
	/**
	 * ��ֵ���˻��ۿ�
	 * @param money  money>0��ʾ��ֵ ��money<0�������ѿۿ�
	 * @throws Exception
	 */
	public void updateAccount(String uname,double money) throws Exception{
		String sql = "update tuser set account=account+? where uname=?";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setDouble(1, money);
		ps.setString(2, uname);
		ps.executeUpdate();
		ps.close();
	}
	
	/**
	 * ����û��Ĺ����¼
	 * @param br
	 * @throws Exception
	 */
	public void addBuyRecord(TBuyRecord br,Map<String,Integer> books) throws Exception {
		String sql = "insert into  tbuyrecord values( (select nvl(max(bid),0)+1 from tbuyrecord),?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1,br.getUname());
		ps.setTimestamp(2, new java.sql.Timestamp(br.getBuytime().getTime()));
		ps.setDouble(3,br.getAllmoney());
		ps.setInt(4,br.getAllcount());
		ps.executeUpdate();
		ps.close();
		
		//ѭ������addBuyDetail
		Set<String> isbns = books.keySet();
		for(String isbn : isbns){
			TBuyDetail detail = new TBuyDetail();
			detail.setCount(books.get(isbn));
			detail.setDprice(0);
			detail.setIsbn(isbn);
			addBuyDetail(detail);
		}		
	}
	
	/**
	 * ��ӹ�����ϸ
	 * @param detail
	 * @throws Exception
	 */
	private void addBuyDetail(TBuyDetail detail) throws Exception{
		
		String sql = "insert into tbuydetail values( (select nvl(max(id),0)+1 from tbuydetail),?, (select nvl(max(bid),0) from tbuyrecord),?,?)";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, detail.getIsbn());
		ps.setInt(2, detail.getCount());
		ps.setDouble(3, detail.getDprice());
		ps.executeUpdate();
		ps.close();
		updateBookCount(detail.getIsbn(),-detail.getCount());
	}
	
	/**
	 * ��ӿ���ۿ��
	 * @param bkcount  bkcount>0��ͼ����⣬bkcount<0�ǳ��� 
	 * @throws Exception
	 */
	public void updateBookCount(String isbn,int bkcount) throws Exception{
		String sql="update tbook set bkcount=bkcount+? where isbn=?";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setInt(1, bkcount);
		ps.setString(2,isbn);
		ps.executeUpdate();
		ps.close();
	}
	
	
	
	/**
	 * ����û�
	 * @param user
	 * @throws Exception
	 */
	public void addUser(TUser user) throws SQLIntegrityConstraintViolationException,Exception{
		this.openConnection();
		String sql = "insert into tuser values(?,?,?,?,?)";
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, user.getUname());
		ps.setString(2,user.getPwd());	
		ps.setTimestamp(3, new Timestamp(user.getRegtime().getTime())); //TimestampΪ��Сʱ�����ʱ��
		ps.setDouble(4, user.getAccount());
		ps.setString(5, String.valueOf(user.getRole()));             //����ת�ַ���
		ps.executeUpdate();
		ps.close();
	}	
	
	
	/**
	 * �ڴ˴��������ݿ⣬�����û���ݵ�У��
	 * @param uname
	 * @param pwd
	 * @return
	 */
	public TUser login(String uname,String pwd) throws Exception{
		TUser user = null;
		
		this.openConnection();
			
		
		//�������ݿ⣬��ȡ���ݿ��Connection
		try {           
			if(this.conn != null){
				String sql = "select * from tuser where uname=? and pwd=?";				
				PreparedStatement  ps = conn.prepareStatement(sql);
				ps.setString(1, uname);
				ps.setString(2, pwd);				
				ResultSet rs = ps.executeQuery();    //ʹ��PreparedStatement����ִ��sql��䣬���ؽ����
				if(rs != null){
				   	while(rs.next()){
				   		user = new TUser();
				   		//rs.getString("uname");
				   		//rs.getString("pwd");
				   		java.util.Date date = rs.getDate("regtime");
				   		double account = rs.getDouble("account");
				   		int role = Integer.parseInt(rs.getString("role"));
				   		user.setUname(uname);
				   		user.setPwd(pwd);
				   		user.setRole(role);
				   		user.setAccount(account);
				   		user.setRegtime(date);				   		
				   	}
				   	rs.close();
				}
				ps.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}		
		
		return user;
	}
}
