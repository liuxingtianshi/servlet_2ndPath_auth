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
		//1. �ж��û��Ƿ��¼��
		//2. �ж��û��Ľ�ɫ�Ƿ�Ϊ����Ա
		HttpServletRequest request = (HttpServletRequest)req;
		TUser user = (TUser)request.getSession().getAttribute("user");
		if(user == null){
			request.setAttribute("msg","����Ȩ���ʵ�ǰҳ�����ȵ�¼");
			request.getRequestDispatcher("/main/login.jsp").forward(req, res);
		}else{
			if(user.getRole() == IRole.ADMIN){
		        chain.doFilter(req, res);				
			}else{
				request.setAttribute("msg","��ķ���Ȩ�޲��㣬�����µ�¼");
				request.getRequestDispatcher("/main/login.jsp").forward(req, res);				
			}		
		}	
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
