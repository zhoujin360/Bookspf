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
	@Insert("insert into orders(orderid,uid,bid,isbn,bookprice,createtime) values(#{orderid},#{uid},#{bid},#{isbn},#{bookprice},#{createtime})")
	public void insertOrder(Long orderid,Integer uid,Integer bid,String isbn,Double bookprice,String createtime);

	//查询所有订单的统计信息
	@Select("select distinct orderid,uid,createtime from orders")
	public ArrayList<DBOrder> getOrders();

	//查询某个订单信息
	@Select("select distinct orderid,uid,createtime from orders where orderid=#{orderid}")
	public ArrayList<DBOrder> getOrderOfOrderid(Long orderid);

	//查询某个订单详情
	@Select("select  orderid,bid,isbn,bookprice from orders where orderid=#{orderid}")
	public ArrayList<DBOrder> getOrderinfoOfOrderid(Long orderid);

	//查询某个订单信息
	@Select("select distinct orderid,uid,createtime from orders where uid=#{uid}")
	public ArrayList<DBOrder> getOrderOfUid(Integer uid);

	//查询某个订单信息
	@Select("select distinct orderid,uid,createtime from orders where createtime=#{createtime}")
	public ArrayList<DBOrder> getOrderOfCreatetime(String createtime);

	//查询所有订单的总价
	@Select("select sum(bookprice) from orders group by orderid")
	public ArrayList<Double> getOrderPrice();

	//查询所有订单的总价
	@Select("select sum(bookprice) from orders where orderid=#{orderid} group by orderid")
	public ArrayList<Double> getOrderPriceOfOrderid(Long orderid);

	//查询所有订单的总价
	@Select("select sum(bookprice) from orders where uid=#{uid} group by orderid")
	public ArrayList<Double> getOrderPriceOfUid(Integer uid);

	//查询所有订单的总价
	@Select("select sum(bookprice) from orders where createtime=#{createtime} group by orderid")
	public ArrayList<Double> getOrderPriceOfCreatetime(String createtime);

	//查询bid
	@Select("select bid from orders where orderid=#{orderid}")
	public ArrayList<Integer> getBidsOfOrderid(Long orderid);


	//用户
	//查询某个订单信息
	//查询某个订单信息
	@Select("select distinct orderid,uid,createtime from orders where orderid=#{orderid} and uid=#{uid}")
	public ArrayList<DBOrder> getOrderOfOrderidAndUid(Long orderid,Integer uid);

	//查询某个订单信息
	@Select("select distinct orderid,uid,createtime from orders where createtime=#{createtime} and uid=#{uid}")
	public ArrayList<DBOrder> getOrderOfCreatetimeAndUid(String createtime,Integer uid);

}
