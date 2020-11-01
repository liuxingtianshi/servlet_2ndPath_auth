package com.icss.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.BookBiz;
import com.icss.entity.TBook;
import com.icss.entity.TUser;
import com.icss.util.Log;

public class ShopCarSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShopCarSvl() {
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

		
		//从session中提取购物车中的数据
		Map<String,Integer> shopcar = (Map<String,Integer>)request.getSession().getAttribute("ShopCar");
		if(shopcar !=null){
			BookBiz biz = new BookBiz();
			try {
				List<TBook> books = biz.getShopCarBooks(shopcar.keySet());	
				request.setAttribute("books", books);
				request.getRequestDispatcher("/main/ShopCar.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				Log.logger.error(e.getMessage());
				request.setAttribute("msg","网络异常，请和网络运营商联系");
				request.getRequestDispatcher("/main/ShopCar.jsp").forward(request, response);
			}				
		}else{
			request.getRequestDispatcher("/main/ShopCar.jsp").forward(request, response);
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
