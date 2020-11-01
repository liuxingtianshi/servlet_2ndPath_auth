package com.icss.biz;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.icss.dao.UserDao;
import com.icss.dto.Buyinfo;
import com.icss.dto.TurnPage;
import com.icss.entity.TBuyRecord;
import com.icss.entity.TUser;
import com.icss.exception.InputNullException;
import com.icss.util.Log;

public class UserBiz {
	
	/**
	 * У��������û����������ݿ����Ƿ����ͬ��
	 * @param uname
	 * @return  true��ʾ������
	 * @throws Exception
	 */
	public boolean validUserName(String uname) throws Exception{
		boolean bRet;
		
		UserDao dao = new UserDao();
		try {
			bRet = dao.validUserName(uname);	
		} catch (Exception e) {
			throw e;
		}finally{
			dao.closeConnection();
		}		
		
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
		
		List<Buyinfo> buyinfos;
		
		UserDao dao = new UserDao();
		try {
			buyinfos = dao.getBuyinfos(uname, beginDate, endDate,tp);	
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			dao.closeConnection();
		}		
		
		return buyinfos;
	}
	
	/**
	 * �û����鸶��
	 * @param uname
	 * @param books
	 * @throws Exception
	 */
	public void buyBooks(String uname,Map<String,Integer> books,double allprice) throws Exception{
		UserDao dao = new UserDao();
		try {
			dao.beginTransaction();                                 //��������			
			dao.updateAccount(uname, -allprice);                     //�û����˻��ۿ�
			TBuyRecord br = new TBuyRecord();
			br.setUname(uname);
			br.setBuytime(new Date());
			br.setAllmoney(allprice);                              //�ۿ�ø���        
			int allcount = 0;
			Set<String> isbns = books.keySet();
			for(String isbn : isbns){
				allcount += books.get(isbn);
			}
			br.setAllcount(allcount); 
			dao.addBuyRecord(br, books);                             //��ӹ����¼
			dao.commit();                                            //�ύ����
		} catch (Exception e) {
			e.printStackTrace();
			dao.rollback();                                          //�ع�����
			throw e;
		}finally{
			dao.closeConnection();                                   //�ر����ݿ�
		}
				
	}
	
	
	/**
	 * �û�ע��
	 * @param user
	 * @throws Exception
	 */
	public void regist(TUser user) throws SQLIntegrityConstraintViolationException,Exception{
		
		if(user != null){			
			UserDao dao = new UserDao();
			try {
				dao.addUser(user);	
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}finally{
				dao.closeConnection();
			}						
		}	
		
	}
	
	
	/**
	 * �����û�����������е�¼�������¼�ɹ��ͷ����û����󣨰���Ȩ����Ϣ��������Ϣ��
	 * @param uname
	 * @param pwd
	 * @return  Ҫ���ǵ����   1.�û���������Ϊ����ô�죿
	 *                   2.�û��������벻Ϊ�գ����ǲ���ȷ��ô�� ------- User���� ����Ϊnull
	 *                   3.���ݿ�����쳣�ˣ���ô�죿                   ---------throw Exception
	 */
	public TUser login(String uname,String pwd) throws InputNullException,Exception{
		TUser user = null;
		
		Log.logger.info("uname=" + uname + "--pwd=" + pwd );
		
		if(uname != null && pwd != null && !uname.equals("") && !pwd.equals("")){
			
			UserDao dao = new UserDao();
			try {
				user =  dao.login(uname, pwd);			
			}catch (Exception e) {
				Log.logger.error(e.getMessage());       //�Ѵ�����Ϣ��¼����־��
				throw e;           //���쳣�����׳�				
			}finally{
				//�ڴ˴��ر����ݿ�
				 dao.closeConnection();                //�ر����ݿ�
			}
			
		}else{
			Log.logger.error("�û���������Ϊ��");       //�Ѵ�����Ϣ��¼����־��
			throw new InputNullException("�û���������Ϊ�գ�����������");
		}
		
		return user;
	}	
}
