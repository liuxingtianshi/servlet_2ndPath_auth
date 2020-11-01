package com.icss.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineUserListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent arg0) {
		ServletContext context = arg0.getSession().getServletContext();
		Object obj = context.getAttribute("OnlineUser");
		if(obj == null){
			context.setAttribute("OnlineUser",1);
			System.out.println("当前在线人数1人");
		}else{
			Integer user = (Integer)obj;
			user +=1;
			context.setAttribute("OnlineUser",user);
			System.out.println("当前在线人数"+ user);
		}		
		arg0.getSession().setMaxInactiveInterval(300);
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		ServletContext context = arg0.getSession().getServletContext();
		Integer user = (Integer)context.getAttribute("OnlineUser");
		user = user-1;
		context.setAttribute("OnlineUser", user);
		System.out.println(arg0.getSession().getId() + "退出系统");
		System.out.println("当前在线人数" + user);
		
	}

}
