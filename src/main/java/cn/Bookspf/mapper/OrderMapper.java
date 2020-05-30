package cn.Bookspf.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import cn.Bookspf.model.DO.DBOrder;

@Mapper
@Repository
public interface OrderMapper {
	
	//插入一个订单
	@Insert("insert into orders values(#{orderid},#{uid},#{bid},#{isbn},#{bookprice},#{createtime})")
	public void insertOrder(Long orderid,Integer uid,Integer bid,String isbn,Double bookprice,String createtime);

	//查询所有订单的统计信息
	@Select("select distinct orderid,uid,createtime,paytime from orders")
	public ArrayList<DBOrder> getOrders();

	//查询某个订单信息
	@Select("select distinct orderid,uid,createtime,paytime from orders where orderid=#{orderid}")
	public ArrayList<DBOrder> getOrderOfOrderid(Long orderid);

	//查询某个订单详情
	@Select("select  orderid,bid,isbn,bookprice from orders where orderid=#{orderid}")
	public ArrayList<DBOrder> getOrderinfoOfOrderid(Long orderid);

	//查询某个订单信息
	@Select("select distinct orderid,uid,createtime,paytime from orders where uid=#{uid}")
	public ArrayList<DBOrder> getOrderOfUid(Integer uid);

	//查询某个订单信息
	@Select("select distinct orderid,uid,createtime,paytime from orders where createtime=#{createtime}")
	public ArrayList<DBOrder> getOrderOfCreatetime(String createtime);




	//查询所有订单的总价
	@Select("select sum(bookprice) from orders group by orderid")
	public ArrayList<Integer> getOrderPrice();

	//查询所有订单的总价
	@Select("select sum(bookprice) from orders where orderid=#{orderid} group by orderid")
	public ArrayList<Integer> getOrderPriceOfOrderid(long orderid);

	//查询所有订单的总价
	@Select("select sum(bookprice) from orders where uid=#{uid} group by orderid")
	public ArrayList<Integer> getOrderPriceOfUid(Integer uid);

	//查询所有订单的总价
	@Select("select sum(bookprice) from orders where createtime=#{createtime} group by orderid")
	public ArrayList<Integer> getOrderPriceOfCreatetime(String createtime);


}
