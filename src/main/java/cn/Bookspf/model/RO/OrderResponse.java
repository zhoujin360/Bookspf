package cn.Bookspf.model.RO;

import java.util.ArrayList;

import cn.Bookspf.model.DO.DBOrder;
import cn.Bookspf.model.DTO.OrderStatistics;
import cn.Bookspf.model.DTO.User;

public class OrderResponse extends Response {
	private ArrayList<OrderStatistics> orders;
	private ArrayList<DBOrder> ordersinfo;
	
	public ArrayList<DBOrder> getOrdersinfo() {
		return ordersinfo;
	}

	public void setOrdersinfo(ArrayList<DBOrder> ordersinfo) {
		this.ordersinfo = ordersinfo;
	}

	public ArrayList<OrderStatistics> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<OrderStatistics> orders) {
		this.orders = orders;
	}
	
	
	
}
