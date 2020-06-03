package cn.Bookspf.model.RO;

import java.util.ArrayList;

import cn.Bookspf.model.DO.DBShopcar;

public class ShopcarResponse extends Response{
	private ArrayList<DBShopcar> shopcars;
	
	public ShopcarResponse() {}
	public ShopcarResponse(ArrayList<DBShopcar> shopcars) {
		this.shopcars=shopcars;
	}
	public ArrayList<DBShopcar> getShopcars() {
		return shopcars;
	}
	public void setShopcars(ArrayList<DBShopcar> shopcars) {
		this.shopcars = shopcars;
	}

}
