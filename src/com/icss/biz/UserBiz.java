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
	 * 校验输入的用户名，在数据库中是否存在同名
	 * @param uname
	 * @return  true表示重名了
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
	 * 读取用户的购买记录
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
	 * 用户购书付款
	 * @param uname
	 * @param books
	 * @throws Exception
	 */
	public void buyBooks(String uname,Map<String,Integer> books,double allprice) throws Exception{
		UserDao dao = new UserDao();
		try {
			dao.beginTransaction();                                 //开启事务			
			dao.updateAccount(uname, -allprice);                     //用户的账户扣款
			TBuyRecord br = new TBuyRecord();
			br.setUname(uname);
			br.setBuytime(new Date());
			br.setAllmoney(allprice);                              //扣款，用负数        
			int allcount = 0;
			Set<String> isbns = books.keySet();
			for(String isbn : isbns){
				allcount += books.get(isbn);
			}
			br.setAllcount(allcount); 
			dao.addBuyRecord(br, books);                             //添加购买记录
			dao.commit();                                            //提交事务
		} catch (Exception e) {
			e.printStackTrace();
			dao.rollback();                                          //回滚事务
			throw e;
		}finally{
			dao.closeConnection();                                   //关闭数据库
		}
				
	}
	
	
	/**
	 * 用户注册
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
	 * 传入用户名、密码进行登录，如果登录成功就返回用户对象（包含权限信息和其它信息）
	 * @param uname
	 * @param pwd
	 * @return  要考虑的情况   1.用户名、密码为空怎么办？
	 *                   2.用户名、密码不为空，但是不正确怎么办 ------- User对象 返回为null
	 *                   3.数据库访问异常了，怎么办？                   ---------throw Exception
	 */
	public TUser login(String uname,String pwd) throws InputNullException,Exception{
		TUser user = null;
		
		Log.logger.info("uname=" + uname + "--pwd=" + pwd );
		
		if(uname != null && pwd != null && !uname.equals("") && !pwd.equals("")){
			
			UserDao dao = new UserDao();
			try {
				user =  dao.login(uname, pwd);			
			}catch (Exception e) {
				Log.logger.error(e.getMessage());       //把错误信息记录到日志中
				throw e;           //把异常二次抛出				
			}finally{
				//在此处关闭数据库
				 dao.closeConnection();                //关闭数据库
			}
			
		}else{
			Log.logger.error("用户名或密码为空");       //把错误信息记录到日志中
			throw new InputNullException("用户名或密码为空，请重新输入");
		}
		
		return user;
	}	
}
