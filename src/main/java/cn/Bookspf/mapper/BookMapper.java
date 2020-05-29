package cn.Bookspf.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import cn.Bookspf.model.DO.DBBook;
import cn.Bookspf.model.DTO.Book;

@Mapper
@Repository
public interface BookMapper {
	
	//查询所有图书
	@Select("select * from book")
	public ArrayList<DBBook> getBooks();

	@Select("select bid from book where bid=#{bid}")
	public String findBid(Integer bid);
	
	//插入图书记录
	@Insert("insert into book values(#{bid},#{bookname},#{hot},#{sortid},#{author},#{description},#{bookprice},#{added},#{number})")
	public void addBook(Book book);

	//插入图书记录
	@Update("update book set bookname=#{bookname}, sortid=#{sortid}, author=#{author}, description=#{description}, bookprice=#{bookprice}, added=#{added} where bid=#{bid}")
	public void alterBook(Book book);

	//删除图书
	@Delete("delete from book where bid=#{bid}")
	public void deleteBook(Integer bid);

	//查询某书的数量
	@Select("select number from book where bid=#{bid}")
	public Integer getBookNumber(Integer bid);

	//查询某书的数量
	@Update("update book set number=#{number} where bid=#{bid}")
	public Integer updateBookNumber(Integer bid,Integer number);


}
