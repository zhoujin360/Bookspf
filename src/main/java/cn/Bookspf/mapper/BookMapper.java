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

	//管理员****************************************
	//查询所有图书
	@Select("select * from book")
	public ArrayList<DBBook> getBooks();

	//插入图书记录
	@Insert("insert into book values(#{bid},#{bookname},#{hot},#{sortid},#{author}," +
			"#{description},#{bookprice},#{added},#{number})")
	public void addBook(Book book);

	//更新图书记录
	@Update("update book set bookname=#{bookname}, sortid=#{sortid}, author=#{author}," +
			"description=#{description}, bookprice=#{bookprice}, added=#{added} where bid=#{bid}")
	public void alterBook(Book book);

	//删除图书
	@Delete("delete from book where bid=#{bid}")
	public void deleteBook(Integer bid);

	//查Bid
	@Select("select bid from book where bid=#{bid}")
	public String findBid(Integer bid);

	//查询某书的数量
	@Select("select number from book where bid=#{bid}")
	public Integer getBookNumberOfAdmin(Integer bid);

	//修改某书的数量
	@Update("update book set number=#{number} where bid=#{bid}")
	public Integer updateBookNumberOfAdmin(Integer bid,Integer number);

	//查询书籍名称
	@Select("select bookname from book where bid=#{bid}")
	public  String getBooknameOfAdmin(Integer bid);
	//*************************************************************







	//用户******************************************
	//查询某图书
	@Select("select * from book where bid=#{bid} and added = 1")
	public DBBook getBook(Integer bid);

	//获取bid
	@Select("select bid from book where bookname=#{bookname} and added = 1")
	public Integer getBid(String bookname);

	//查询某书价格
	@Select("select bookprice from book where bid=#{bid} and added = 1")
	public Double getBookprice(Integer bid);

	//查询某书的数量
	@Select("select number from book where bid=#{bid} and added = 1")
	public Integer getBookNumber(Integer bid);

	//修改某书的数量
	@Update("update book set number=#{number} where bid=#{bid} and added = 1")
	public Integer updateBookNumber(Integer bid,Integer number);

	//查询书籍名称
	@Select("select bookname from book where bid=#{bid} and added = 1")
	public  String getBookname(Integer bid);

	//查询热度
	@Select("select hot from book where bid=#{bid} and added = 1")
	public  Integer getHot(Integer bid);

	//更新热度
	@Update("update book set hot=#{hot} where bid=#{bid} and added = 1")
	public void updateHot(Integer bid,Integer hot);

	//查询排行榜
	@Select("select * from book where added=1 order by hot desc limit 0,10 ")
	public ArrayList<DBBook> getRankList(); 
	
	//获取发布的图书
	@Select("select * from book where added=1 limit 0,5")
	public ArrayList<DBBook> getPublishBook();

	//*************************************************************
}
