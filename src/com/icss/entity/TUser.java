package com.icss.entity;

import java.util.Date;

public class TUser {
	private String uname;
	private String pwd;
	private int  role;           //�û���ɫ  ��1' ��ʾ����Ա   ��2' ��ʾVIP   3��ʾ��ͨ�û�
	private Date regtime;        //��enity������һ��Ҫʹ��java.util.Date
	private double account;	
	
	public Date getRegtime() {
		return regtime;
	}
	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}
	public double getAccount() {
		return account;
	}
	public void setAccount(double account) {
		this.account = account;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
	

}
