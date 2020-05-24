package cn.Bookspf.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import cn.Bookspf.model.DO.DBSale;

@Mapper
@Repository
public interface SaleMapper {
	
	//查询所有销售记录
	@Select("select * from sale")
	public ArrayList<DBSale> getSales();
	
	
	
}
