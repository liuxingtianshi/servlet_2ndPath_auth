package com.icss.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.IRole;
import com.icss.biz.UserBiz;
import com.icss.entity.TUser;
import com.icss.util.Log;

public class RegSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RegSvl() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String uname = request.getParameter("uname");
		response.setContentType("text/html");
		response.setCharacterEncoding("gbk");
		if(uname != null){			
			PrintWriter out = response.getWriter();				
			//这是ajax提交
			UserBiz biz = new UserBiz();
			try {
				boolean bRet = biz.validUserName(uname);
				if(bRet){
					out.write("你输入的用户名，已经存在");	
				}else{
					out.write("你输入的用户名，可以使用");
				}				
			} catch (Exception e) {
				e.printStackTrace();
				out.write("网络异常，请和运营商联系");
			}
			out.flush();
			out.close();			
		}else{
			request.getRequestDispatcher("/main/register.jsp").forward(request, response);	
		}
		
        		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		String account = request.getParameter("account");
		if(uname != null && pwd!=null && !uname.equals("") && !pwd.equals("")){
			UserBiz biz = new UserBiz();
			TUser user = new TUser();
			user.setUname(uname);
			user.setPwd(pwd);
			user.setRegtime(new Date());
			user.setRole(IRole.ORDINARY);
			try {
				user.setAccount(Double.parseDouble(account));	
			} catch (Exception e) {
				e.printStackTrace();
				user.setAccount(0);
			}			
			try {
				biz.regist(user);	
				request.setAttribute("msg", user.getUname() + "注册成功，请登陆");
				request.getRequestDispatcher("/main/login.jsp").forward(request, response);
			}
			catch(SQLIntegrityConstraintViolationException e){
				Log.logger.error(e.getMessage());
				request.setAttribute("msg","你输入的用户名已经存在，请重新输入");
				request.getRequestDispatcher("/main/register.jsp").forward(request, response);
			} catch (Exception e) {
				Log.logger.error(e.getMessage());
				request.setAttribute("msg", "系统网络异常，请和运营商联系");
				request.getRequestDispatcher("/error/error.jsp").forward(request, response);
			}			
		}else{
			request.setAttribute("msg", "你的用户名或密码为空,请重新输入");
			request.getRequestDispatcher("/main/register.jsp").forward(request, response);
		}
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
