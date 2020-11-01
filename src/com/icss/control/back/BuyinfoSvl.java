package com.icss.control.back;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.UserBiz;
import com.icss.dto.Buyinfo;
import com.icss.dto.TurnPage;

public class BuyinfoSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public BuyinfoSvl() {
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
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
     
		String uname = request.getParameter("uname");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String page = request.getParameter("page");
		SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy");		 
		Date bDate=null,eDate=null;
		try {
			if(beginDate != null && !beginDate.equals(""))
				bDate = sf.parse(beginDate);	
		} catch (Exception e) {
			 e.printStackTrace();
		}
		try {
			if(endDate != null && !endDate.equals(""))
				eDate = sf.parse(endDate);	
		} catch (Exception e) {
			e.printStackTrace();
		}	
		TurnPage tp = new TurnPage();       
		try {
			if(page != null)
				tp.currentPageNum = Integer.parseInt(page);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		UserBiz biz = new UserBiz();
		try {
			List<Buyinfo> buyinfos = biz.getBuyinfos(uname, bDate, eDate,tp);
			request.setAttribute("buyinfos", buyinfos);
			request.setAttribute("uname", uname);
			request.setAttribute("beginDate",beginDate);
			request.setAttribute("endDate",endDate);
			request.setAttribute("allRecordCount", tp.allRecordCount);
			request.setAttribute("allPages",tp.allPages);
			request.setAttribute("currentPageNum", tp.currentPageNum);
			request.getRequestDispatcher("/back/BuyInfo.jsp").forward(request, response);
			
		} catch (Exception e) {
			request.setAttribute("msg", "网络异常，请联系网络运营商");
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
