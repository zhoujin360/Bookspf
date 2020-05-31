package cn.Bookspf.model.RO;

import java.util.ArrayList;

import cn.Bookspf.model.DO.DBShopcar;
import cn.Bookspf.model.DTO.ShopcarStatistics;

public class ShopcarResponse extends Response{
	private ArrayList<DBShopcar> shopcars;
	private ArrayList<ShopcarStatistics> shopcarsinfo;
	
	public ShopcarResponse() {}
	public ArrayList<DBShopcar> getShopcars() {
		return shopcars;
	}
	public void setShopcars(ArrayList<DBShopcar> shopcars) {
		this.shopcars = shopcars;
	}

	public ArrayList<ShopcarStatistics> getShopcarsinfo() {
		return shopcarsinfo;
	}

	public void setShopcarsinfo(ArrayList<ShopcarStatistics> shopcarsinfo) {
		this.shopcarsinfo = shopcarsinfo;
	}
}
