package cn.Bookspf.model.RO;

import java.util.ArrayList;

import cn.Bookspf.model.DO.DBOrder;
import cn.Bookspf.model.DTO.User;

public class OrderResponse extends Response {
	private ArrayList<DBOrder> orders;
	
	public OrderResponse(boolean status,String mes,ArrayList<DBOrder> orders) {
		this.status=status;
		this.mes=mes;
		this.orders=orders;
	}

	public ArrayList<DBOrder> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<DBOrder> orders) {
		this.orders = orders;
	}
	
	
	
	
}
