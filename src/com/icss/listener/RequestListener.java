package com.icss.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class RequestListener implements ServletRequestListener {

	public void requestDestroyed(ServletRequestEvent arg0) {

		HttpServletRequest request = (HttpServletRequest)arg0.getServletRequest();
		String uri = request.getRequestURI();
		//System.out.println(  uri + "��ķ��ʽ�����");
		
	}

	public void requestInitialized(ServletRequestEvent arg0) {
      
	    HttpServletRequest request = (HttpServletRequest)arg0.getServletRequest();
		String uri = request.getRequestURI();
		//System.out.println("����ʵĵ�ַ��:" + uri);
	}

}
