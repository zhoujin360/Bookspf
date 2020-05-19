package cn.Bookspf.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import cn.Bookspf.model.DO.DBOrder;

@Mapper
@Repository
public interface OrderMapper {
	
	@Select("select * from orders where uid=#{uid}")
	public ArrayList<DBOrder> getOrders(int uid);
	
	
}
