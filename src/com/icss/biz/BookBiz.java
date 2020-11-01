package com.icss.biz;

import java.util.List;
import java.util.Set;

import com.icss.dao.BookDao;
import com.icss.entity.TBook;
import com.icss.util.Log;

public class BookBiz {
	
	/**
	 * 获得图书详情(不含封面图片)
	 * @param isbn
	 * @return
	 * @throws Exception
	 */
	public TBook getBookDetail(String isbn) throws Exception{
		TBook book = null;
		
		BookDao dao = new BookDao();
		try {
			book = dao.getBookDetail(isbn);
		} catch (Exception e) {
			e.printStackTrace();
			Log.logger.error(e.getMessage());
			throw e;
		}finally{
			dao.closeConnection();
		}
		
		return book;
		
	}
	
	/**
	 * 
	 * @param book
	 * @throws Exception
	 */
	public void addBook(TBook book) throws Exception{
		BookDao dao = new BookDao();
		try {
			dao.addBook(book);	
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}	
	}
	
	/**
	 * 通过isbn，读取书的封面图片
	 * @param isbn
	 * @return
	 * @throws Exception
	 */
	public byte[] getPic(String isbn) throws Exception{
		byte[] pic;
		
		BookDao dao = new BookDao();
		try {
			pic= dao.getPic(isbn);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return pic;
	}
	
	/**
	 * 读取书城主页需要显示的图书信息
	 * @return
	 * @throws Exception
	 */
	public List<TBook> getMainBooks() throws Exception{
		List<TBook> books;
		
		BookDao dao = new BookDao();
		try {
			books = dao.getMainBooks();	
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			dao.closeConnection();
		}	
		
		return books;		
	}
	
	/**
	 * 根据购物车中的主键集合，提取所有图书的详细信息
	 * @param shopcar
	 * @return
	 */
	public List<TBook> getShopCarBooks(Set<String> shopcar) throws Exception{
		List<TBook> books;
			
		BookDao dao = new BookDao();
		try {
			books = dao.getShopCarBooks(shopcar);		
		} catch (Exception e) {
			throw e;
		}finally{
			dao.closeConnection();
		}
		
		return books;
	}

}
