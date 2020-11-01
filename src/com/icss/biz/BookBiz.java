package com.icss.biz;

import java.util.List;
import java.util.Set;

import com.icss.dao.BookDao;
import com.icss.entity.TBook;
import com.icss.util.Log;

public class BookBiz {
	
	/**
	 * ���ͼ������(��������ͼƬ)
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
	 * ͨ��isbn����ȡ��ķ���ͼƬ
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
	 * ��ȡ�����ҳ��Ҫ��ʾ��ͼ����Ϣ
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
	 * ���ݹ��ﳵ�е��������ϣ���ȡ����ͼ�����ϸ��Ϣ
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
