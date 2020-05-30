package cn.Bookspf.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import cn.Bookspf.model.DO.DBBook;
import cn.Bookspf.model.DO.DBSort;
import cn.Bookspf.model.DTO.Book;

@Mapper
@Repository
public interface BookMapper {
	
	//查询所有图书
	@Select("select * from book")
	public ArrayList<DBBook> getBooks();

	//查询某图书
	@Select("select * from book where bid=#{bid}")
	public DBBook getBook(Integer bid);

	//查Bid
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

	//修改某书的数量
	@Update("update book set number=#{number} where bid=#{bid}")
	public Integer updateBookNumber(Integer bid,Integer number);

	//查询某书价格
	@Select("select bookprice from book where bid=#{bid}")
	public Double getBookprice(Integer bid);

	
	//查询排行榜
	@Select("select * from book order by hot desc limit 0,10")
	public ArrayList<DBBook> getRankList(); 
	
	//获取发布的图书
	@Select("select * from book limit 0,5")
	public ArrayList<DBBook> getPublishBook(); 

}
