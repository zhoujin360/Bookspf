package cn.Bookspf.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import cn.Bookspf.model.DO.DBSale;

@Mapper
@Repository
public interface SaleMapper {

	//插入销售记录
	@Insert("insert into sale(saleid,bid,isbn,saletime) values(#{saleid},#{bid},#{isbn},#{saletime})")
	public void insertSale(Long saleid, Integer bid, String isbn, String  saletime);
	
	//查询所有销售记录
	@Select("select * from sale")
	public ArrayList<DBSale> getSales();

	//查询某个销售记录
	@Select("select * from sale where saleid=#{saleid}")
	public ArrayList<DBSale> getSaleOfSaleid(Long saleid);

	//查询某个销售记录
	@Select("select distinct saleid,saletime from sale")
	public ArrayList<DBSale> getSalesinfo();

	//查询某个销售记录
	@Select("select distinct saleid,saletime from sale where saleid=#{saleid}")
	public ArrayList<DBSale> getSalesinfoOfSaleid(Long saleid);

	//查询某个销售记录
	@Select("select distinct saleid,saletime from sale where isbn=#{isbn}")
	public ArrayList<DBSale> getSalesinfoOfISBN(String isbn);

	//查询某个销售记录
	@Select("select distinct saleid,saletime from sale where saletime=#{saletime}")
	public ArrayList<DBSale> getSalesinfoOfSaletime(String saletime);


}
