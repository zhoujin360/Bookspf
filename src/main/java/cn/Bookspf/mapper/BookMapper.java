package cn.Bookspf.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import cn.Bookspf.model.DO.DBBook;
import cn.Bookspf.model.DTO.Book;

@Mapper
@Repository
public interface BookMapper {
	
	//查询所有图书
	@Select("select * from book")
	public ArrayList<DBBook> getBooks();
	
	//插入图书记录
	@Insert("insert into book(bid,bookname,sortid,author,description,bookprice,added) values(#{bid},#{bookname},#{sortid},#{author},#{description},#{bookprice},#{added})")
	public void addBook(Book book);
	
	//插入图书记录(无描述)
	@Insert("insert into book(bid,bookname,sortid,author,bookprice,added) values(#{bid},#{bookname},#{sortid},#{author},#{bookprice},#{added})")
	public void addBookNoDescription(Book book);
}
