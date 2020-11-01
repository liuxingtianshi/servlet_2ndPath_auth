package com.icss.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.util.Log;

public class PageViewFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		ServletContext context = req.getSession().getServletContext();
		String path = req.getContextPath();
		String basePath = req.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";			
		String uri = basePath +  req.getRequestURI();
		//System.out.println("请求地址：" + uri);	
		
		
		Object obj = context.getAttribute("PageView");
		Integer PV = 0;
		if(obj == null){
			PV = 1;
		}else{
			PV = (Integer)obj;			
		}
		context.setAttribute("PageView",PV+1);
		//System.out.println("系统访问总数,PV=" + (PV+1));
		
		
		long date = (new Date()).getTime();
		
		
		chain.doFilter(req, res);
		
		date = (new Date()).getTime() - date;
		//System.out.println("页面执行时间为(毫秒)：" + date);
		
		
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
