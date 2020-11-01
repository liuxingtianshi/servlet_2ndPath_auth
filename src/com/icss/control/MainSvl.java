package com.icss.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.icss.biz.BookBiz;
import com.icss.biz.UserBiz;
import com.icss.entity.TBook;
import com.icss.entity.TUser;

public class MainSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MainSvl() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		
//		HttpServletRequest req = (HttpServletRequest)request;
//		//�Զ���¼
//        Cookie[] cks = req.getCookies();
//        if(cks != null){
//        	for(int i=0;i<cks.length;i++){
//            	Cookie ck = cks[i];
//            	if(ck.getName().equals("uinfo")){
//            		String uinfo = ck.getValue();
//            		String[] up = uinfo.split(",");
//            		UserBiz ubiz = new UserBiz();
//            		try {
//            			TUser user = ubiz.login(up[0], up[1]);
//            			if(user != null){
//            				req.getSession().setAttribute("user",user);
//            			}else{
//            				//�Զ���¼ʧ�ܣ������������޸���
//            				ck.setMaxAge(0);          //ɾ��ԭ����Cookie
//            			}
//    				} catch (Exception e) {
//    					e.printStackTrace();
//    				}        		
//            	}
//            }	
//        }		
 		BookBiz biz = new BookBiz();
		try {
			List<TBook> books = biz.getMainBooks();
			request.setAttribute("books", books);
			request.getRequestDispatcher("/main/main.jsp").forward(request,response);
		} catch (Exception e) {
			String msg = "���������쳣�����������Ӫ����ϵ";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/error/error.jsp").forward(request, response);
		}		
	}
		
	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
