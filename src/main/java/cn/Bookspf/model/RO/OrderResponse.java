package cn.Bookspf.model.RO;

import java.util.ArrayList;

import cn.Bookspf.model.DO.DBOrder;

public class OrderResponse extends Response {
	private ArrayList<DBOrder> orders;

	public OrderResponse() {}

	public OrderResponse(ArrayList<DBOrder> orders) {
		this.orders = orders;
	}

	public ArrayList<DBOrder> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<DBOrder> orders) {
		this.orders = orders;
	}
	
	
	
}
