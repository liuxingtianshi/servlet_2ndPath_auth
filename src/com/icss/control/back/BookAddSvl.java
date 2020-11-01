package com.icss.control.back;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.BookBiz;
import com.icss.entity.TBook;
import com.icss.exception.InputNullException;
import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;

public class BookAddSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public BookAddSvl() {
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
		request.getRequestDispatcher("/back/BookAdd.jsp").forward(request, response);
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
		
		SmartUpload  smu = new SmartUpload();	                              //创建SmartUpload
		smu.initialize(this.getServletConfig(),request,response);             //初始化
		smu.setCharset("gbk");                                                //设置基本属性
		smu.setMaxFileSize(100*1024);	                                      //图片最大允许100k		
		smu.setAllowedFilesList("gif,jpg,png,bmp");
		try {
			smu.upload();                                                     //服务器端处理客户端传入的字节信息和文本信息
			com.jspsmart.upload.Request  req =  smu.getRequest();             //获得smartupload中的request对象
			TBook book = new TBook();
			String isbn = req.getParameter("isbn");
			if(isbn == null || isbn.equals("")){
			       	throw new InputNullException("isbn输入为空，请重新输入");
			}
			book.setIsbn(isbn);
			book.setBname(req.getParameter("bname"));
			book.setAuthor(req.getParameter("author"));
			book.setPress(req.getParameter("press"));		
			book.setPrice( Double.parseDouble(req.getParameter("price")));			
			try {
				SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy");
				String pubdate = req.getParameter("pubdate");
				if(pubdate != null && !pubdate.trim().equals("")){
					book.setPubdate(sf.parse(pubdate));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}		
			File file = smu.getFiles().getFile(0);            //支持多个文件同时上传
		    if( file != null ){
		    	int size = file.getSize();
				byte[] bytesPic = new byte[size];
				for(int i=0;i<size;i++){
					bytesPic[i] = file.getBinaryData(i);					
				}			
				book.setPic(bytesPic);
		    }	
			BookBiz biz = new BookBiz(); 
			biz.addBook(book);		
			request.setAttribute("msg", book.getBname() + "录入成功");
			request.getRequestDispatcher("/back/BookAdd.jsp").forward(request, response);
		}
		catch(java.lang.SecurityException e){
			e.printStackTrace();
			request.setAttribute("msg", "上传文件不合格:" + e.getMessage());
			request.getRequestDispatcher("/back/BookAdd.jsp").forward(request, response);
		}
		catch(InputNullException e){
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/back/BookAdd.jsp").forward(request, response);
		}
		catch(NumberFormatException e){			
			e.printStackTrace();
			request.setAttribute("msg", "价格输入有误，请输入正确信息");
			request.getRequestDispatcher("/back/BookAdd.jsp").forward(request, response);
		
		} catch (Exception e) {
			e.printStackTrace();	
			request.setAttribute("msg",e.getMessage());
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
