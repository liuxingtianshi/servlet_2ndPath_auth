package com.icss.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.UserBiz;
import com.icss.entity.TUser;

public class CheckoutSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CheckoutSvl() {
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
		
		try {
			double allprice = Double.parseDouble(request.getParameter("allprice"));
			UserBiz biz = new UserBiz();
			TUser user = (TUser)request.getSession().getAttribute("user");
			Map<String,Integer> shopcar = (Map<String,Integer>)request.getSession().getAttribute("ShopCar");
			if(user != null){	
				if(shopcar != null){
					try {
						if(user.getAccount()>=allprice){
							biz.buyBooks(user.getUname(),shopcar,allprice);	
							request.setAttribute("allprice", allprice);
							request.setAttribute("usercount",user.getAccount()-allprice);	
							user.setAccount(user.getAccount()-allprice);               //修改session中的用户金额
							request.getSession().setAttribute("ShopCar", null);
							request.getRequestDispatcher("/main/CheckoutOK.jsp").forward(request, response);	
						}else{
							request.setAttribute("msg","购买失败，你的账户余额不足，请及时充值");
							request.getRequestDispatcher("/error/error.jsp").forward(request, response);
						}						
					} catch (Exception e) {
						request.setAttribute("msg","网络异常");
						request.getRequestDispatcher("/error/error.jsp").forward(request, response);
					}								
				}else{
					request.setAttribute("msg","你无权访问,请重新登录");
					request.getRequestDispatcher("/error/error.jsp").forward(request, response);
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg","网络异常");
			request.getRequestDispatcher("/error/error.jsp").forward(request, response);
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
