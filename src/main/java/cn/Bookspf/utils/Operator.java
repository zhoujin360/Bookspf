package cn.Bookspf.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import cn.Bookspf.mapper.OrderMapper;
import cn.Bookspf.model.DO.DBOrder;
import cn.Bookspf.model.DTO.OrderStatistics;

public class Operator {
	
	public Operator() {}
	
	//所有查询订单的统计信息
	public ArrayList<OrderStatistics> getOrders(OrderMapper orderMapper){
		ArrayList<OrderStatistics> orders= new ArrayList<OrderStatistics>();
		ArrayList<DBOrder> order = orderMapper.getOrders();
		ArrayList<Integer> price = orderMapper.getOrderPrice();
		for(int i=0;i<order.size();i++) {
			OrderStatistics temp=new OrderStatistics();
			temp.setOrderid(order.get(i).getOrderid());
			temp.setUid(order.get(i).getUid());
			temp.setCreatetime(order.get(i).getCreatetime());
			temp.setPaytime(order.get(i).getPaytime());
			temp.setTotal(price.get(i));
			orders.add(temp);
		}
		return orders;
	}
	
	
}
