package com.icss.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LogoutSvl() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}


	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         request.getSession().invalidate();    //清除放在session中的数据
         //request.getRequestDispatcher("/MainSvl").forward(request, response);
         
         String path = request.getContextPath();
         String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
         response.sendRedirect(basePath + "MainSvl");
                  
        
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
