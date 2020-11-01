package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.icss.entity.TBook;

public class BookDao extends BaseDao{
	
	/**
	 * 根据购物车中的主键集合，提取所有图书的详细信息
	 * @param shopcar
	 * @return
	 */
	public List<TBook> getShopCarBooks(Set<String> shopcar) throws Exception {
		List<TBook> books;
		
 		String isbns = "";
		int ii=0;
		for(String isbn : shopcar){
			if(ii==0)
			 isbns = "'" + isbn + "'";
			else{
				isbns = isbns + "," + "'" + isbn + "'";
			}
			ii++;
		}
		String sql = "select isbn,bname,pubdate,press,author,price from tbook where isbn in( "  + isbns + "  )";
		this.openConnection();
		Statement st = this.conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		books = new ArrayList<TBook>();
		while(rs.next()){
		   	TBook book = new TBook();
			book.setAuthor(rs.getString("author"));
        	book.setBname(rs.getString("bname"));
        	book.setIsbn(rs.getString("isbn"));
        	book.setPress(rs.getString("press"));
        	book.setPrice(rs.getDouble("price"));
        	book.setPubdate(rs.getDate("pubdate"));
        	books.add(book);
		}
		
		
		return books;
	}
	
	
	/**
	 * 获得图书详情(不含封面图片)
	 * @param isbn
	 * @return
	 * @throws Exception
	 */
	public TBook getBookDetail(String isbn) throws Exception{
		TBook book = null;
		
		String sql = "select isbn,bname,pubdate,press,author,price,bkcount,descb from tbook where isbn=?";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1,isbn);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			book = new TBook();
			book.setAuthor(rs.getString("author"));
        	book.setBkcount(rs.getInt("bkcount"));
        	book.setBname(rs.getString("bname"));
        	book.setDescb(rs.getString("descb"));
        	book.setIsbn(rs.getString("isbn"));
        	book.setPress(rs.getString("press"));
        	book.setPrice(rs.getDouble("price"));
        	book.setPubdate(rs.getDate("pubdate"));
        	break;
		}
		rs.close();
		ps.close();
		
		return book;		
	}
	
	/**
	 * 
	 * @param book
	 * @throws Exception
	 */
	public void addBook(TBook book) throws Exception{
		String sql = "insert into tbook values(?,?,?,?,?,?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, book.getIsbn());
		ps.setString(2, book.getBname());
		ps.setBytes(3, book.getPic());       //插入图片
		ps.setDate(4, new java.sql.Date(book.getPubdate().getTime()));
		ps.setString(5, book.getPress());
		ps.setString(6, book.getAuthor());
		ps.setDouble(7, book.getPrice());
		ps.setInt(8, book.getBkcount());
		ps.setString(9, book.getDescb());
		ps.executeUpdate();
		ps.close();
	}
	
	/**
	 * 通过isbn，读取书的封面图片
	 * @param isbn
	 * @return
	 * @throws Exception
	 */
	public byte[] getPic(String isbn) throws Exception{
		byte[] pic = null;
		
		String sql = "select pic from tbook where isbn = ?";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, isbn);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			pic = rs.getBytes("pic");
		}
		rs.close();
		ps.close();
		
		return pic;
	}
	
	/**
	 * 读取书城主页需要显示的图书信息
	 * @return
	 * @throws Exception
	 */
	public List<TBook> getMainBooks() throws Exception{
		List<TBook> books = new ArrayList<TBook>();
		String sql = "select isbn,bname,pubdate,press,author,price,bkcount,descb from tbook";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
        while(rs.next()){
        	TBook book = new TBook();
        	book.setAuthor(rs.getString("author"));
        	book.setBkcount(rs.getInt("bkcount"));
        	book.setBname(rs.getString("bname"));
        	book.setDescb(rs.getString("descb"));
        	book.setIsbn(rs.getString("isbn"));
        	book.setPress(rs.getString("press"));
        	book.setPrice(rs.getDouble("price"));
        	book.setPubdate(rs.getDate("pubdate"));
        	books.add(book);
        }		
        rs.close();
        ps.close();
        
		return books;		
	}

}
