package cn.Bookspf.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import cn.Bookspf.model.DO.DBOrder;

@Mapper
@Repository
public interface OrderMapper {
	
	
	//查询所有订单的统计信息
	@Select("select distinct orderid,uid,createtime,paytime from orders")
	public ArrayList<DBOrder> getOrders();
	
	
	//查询所有订单的总价
	@Select("select sum(bookprice) from orders group by orderid")
	public ArrayList<Integer> getOrderPrice();
	
	//查询某个订单信息
	@Select("select * from orders where orderid=#{orderid}")
	public ArrayList<DBOrder> getOrderOfOrderid(long orderid);
}
