package com.icss.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.BookBiz;
import com.icss.entity.TBook;

public class PreCheckoutSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PreCheckoutSvl() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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

		//从session中提取购物车中的数据
		Map<String,Integer> shopcar = (Map<String,Integer>)request.getSession().getAttribute("ShopCar");
 		if(shopcar!=null){
			BookBiz biz = new BookBiz();
			try {
				List<TBook> books = biz.getShopCarBooks(shopcar.keySet());	
				Set<String> isbns = shopcar.keySet();         //购物车中所有书 的isbn
				for(String isbn : isbns){
					try {
						int buycount = 1;
						buycount = Integer.parseInt(request.getParameter(isbn));	
						shopcar.put(isbn,buycount);           //替换购物车中原来的默认数量					
					} catch (Exception e) {
						e.printStackTrace();
					}					
				}
				//计算图书的总价
				double allprice = 0;
				for(TBook book : books){
					int buycount = shopcar.get(book.getIsbn());
					allprice += book.getPrice()*buycount;
					book.setBuycount(buycount);
				}
				request.setAttribute("allprice",allprice);
				request.setAttribute("books",books);
				request.getRequestDispatcher("/main/PreCheckout.jsp").forward(request, response);
			} catch (Exception e) {
				request.setAttribute("msg","网络连接异常");
				request.getRequestDispatcher("/error/error.jsp").forward(request, response);
			}			
			
		}else{
			request.setAttribute("msg","请从购物车进入，你无权直接访问");
			request.getRequestDispatcher("/error/error.jsp").forward(request, response);
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
