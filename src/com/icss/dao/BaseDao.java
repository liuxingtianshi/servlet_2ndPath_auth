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
//				Class.forName(db.getDbdriver());        //��̬װ�����ݿ�����
//				conn = DriverManager.getConnection(db.getUrl(), db.getUser(),db.getPassword());	
				
				Class.forName(DbInfo2.getDbdriver());        //��̬װ�����ݿ�����
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
	 * ������
	 */
	public void beginTransaction() throws Exception{
		this.openConnection();
		this.conn.setAutoCommit(false);         //�����ֶ��ύ�����ģʽ		
	}
	
	/**
	 * �ύ����
	 * @throws Exception
	 */
	public void commit() throws Exception{		
		if(this.conn != null){			
			this.conn.commit();
		}		
	}
	
	/**
	 * �ع�����
	 * @throws Exception
	 */
	public void rollback () throws Exception{
		if(this.conn != null){
			this.conn.rollback();
		}
	}
	
	/**
	 * ���oracle�ķ�ҳsql���
	 * @param sql
	 * @return
	 */
	public String getOracleTurnPageSql(String sql,int iStart,int iEnd){
		String newSql;
		
		newSql = "select * from (select rownum rw ,tb.* from ( "  + sql + ")tb ) where rw >= " + iStart + " and rw<" + iEnd; 
				
		return newSql;
	}
	
	/**
	 * ���㷭ҳ����ʱ�������ѯ�����ļ�¼����
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
 