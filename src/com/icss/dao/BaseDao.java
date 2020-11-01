package com.icss.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.icss.util.DbInfo2;

public class BaseDao {
	
	protected Connection conn;
	
	public void openConnection() throws Exception{
		try {
			if(this.conn == null){
//				DbInfo db = DbInfo.instance();
//				Class.forName(db.getDbdriver());        //动态装载数据库驱动
//				conn = DriverManager.getConnection(db.getUrl(), db.getUser(),db.getPassword());	
				
				Class.forName(DbInfo2.getDbdriver());        //动态装载数据库驱动
				conn = DriverManager.getConnection(DbInfo2.getUrl(), DbInfo2.getUser(),DbInfo2.getPassword());		
				
			}			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}		
	}
	
	public void closeConnection() {
		if(this.conn != null ){
			try {
				this.conn.close();	
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}		
	}
	
	/**
	 * 打开事务
	 */
	public void beginTransaction() throws Exception{
		this.openConnection();
		this.conn.setAutoCommit(false);         //开启手动提交事务的模式		
	}
	
	/**
	 * 提交事务
	 * @throws Exception
	 */
	public void commit() throws Exception{		
		if(this.conn != null){			
			this.conn.commit();
		}		
	}
	
	/**
	 * 回滚事务
	 * @throws Exception
	 */
	public void rollback () throws Exception{
		if(this.conn != null){
			this.conn.rollback();
		}
	}
	
	/**
	 * 获得oracle的翻页sql语句
	 * @param sql
	 * @return
	 */
	public String getOracleTurnPageSql(String sql,int iStart,int iEnd){
		String newSql;
		
		newSql = "select * from (select rownum rw ,tb.* from ( "  + sql + ")tb ) where rw >= " + iStart + " and rw<" + iEnd; 
				
		return newSql;
	}
	
	/**
	 * 计算翻页操作时，满足查询条件的记录总数
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public int getOrcaleTurnPageAllCount(String sql) throws Exception{
		int iRet = 0;
		
		String newSql = "select count (*) count from (" + sql + ")";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(newSql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			iRet = rs.getInt("count");
		}
		rs.close();
		ps.close();
		
		return iRet;
	}

}
 