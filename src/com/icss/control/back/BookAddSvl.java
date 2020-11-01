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
		
		SmartUpload  smu = new SmartUpload();	                              //����SmartUpload
		smu.initialize(this.getServletConfig(),request,response);             //��ʼ��
		smu.setCharset("gbk");                                                //���û�������
		smu.setMaxFileSize(100*1024);	                                      //ͼƬ�������100k		
		smu.setAllowedFilesList("gif,jpg,png,bmp");
		try {
			smu.upload();                                                     //�������˴���ͻ��˴�����ֽ���Ϣ���ı���Ϣ
			com.jspsmart.upload.Request  req =  smu.getRequest();             //���smartupload�е�request����
			TBook book = new TBook();
			String isbn = req.getParameter("isbn");
			if(isbn == null || isbn.equals("")){
			       	throw new InputNullException("isbn����Ϊ�գ�����������");
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
			File file = smu.getFiles().getFile(0);            //֧�ֶ���ļ�ͬʱ�ϴ�
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
			request.setAttribute("msg", book.getBname() + "¼��ɹ�");
			request.getRequestDispatcher("/back/BookAdd.jsp").forward(request, response);
		}
		catch(java.lang.SecurityException e){
			e.printStackTrace();
			request.setAttribute("msg", "�ϴ��ļ����ϸ�:" + e.getMessage());
			request.getRequestDispatcher("/back/BookAdd.jsp").forward(request, response);
		}
		catch(InputNullException e){
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/back/BookAdd.jsp").forward(request, response);
		}
		catch(NumberFormatException e){			
			e.printStackTrace();
			request.setAttribute("msg", "�۸�����������������ȷ��Ϣ");
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
