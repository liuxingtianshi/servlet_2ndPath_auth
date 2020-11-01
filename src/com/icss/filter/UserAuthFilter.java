package com.icss.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.icss.entity.TUser;

public class UserAuthFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;
		TUser user = (TUser)request.getSession().getAttribute("user");
		if(user == null){
			request.setAttribute("msg","你无权访问当前页，请先登录");
			request.getRequestDispatcher("/main/login.jsp").forward(req, res);
		}else{
			chain.doFilter(req, res);
		}		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
