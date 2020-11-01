package com.icss.entity;

import java.util.Date;

public class TUser {
	private String uname;
	private String pwd;
	private int  role;           //用户角色  ‘1' 表示管理员   ’2' 表示VIP   3表示普通用户
	private Date regtime;        //在enity中日期一定要使用java.util.Date
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
