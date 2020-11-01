package com.icss.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.icss.biz.IRole;
import com.icss.entity.TUser;

public class AdminAuthFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {		
		//1. 判断用户是否登录了
		//2. 判断用户的角色是否为管理员
		HttpServletRequest request = (HttpServletRequest)req;
		TUser user = (TUser)request.getSession().getAttribute("user");
		if(user == null){
			request.setAttribute("msg","你无权访问当前页，请先登录");
			request.getRequestDispatcher("/main/login.jsp").forward(req, res);
		}else{
			if(user.getRole() == IRole.ADMIN){
		        chain.doFilter(req, res);				
			}else{
				request.setAttribute("msg","你的访问权限不足，请重新登录");
				request.getRequestDispatcher("/main/login.jsp").forward(req, res);				
			}		
		}	
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
