package cn.Bookspf.model.RO;

import java.util.ArrayList;

import cn.Bookspf.model.DO.DBStock;

public class StockResponse extends Response{
	private ArrayList<DBStock> stocks;
	
	public StockResponse() {}
	public StockResponse(ArrayList<DBStock> stocks) {
		this.stocks=stocks;
	}
	public ArrayList<DBStock> getStocks() {
		return stocks;
	}
	public void setStocks(ArrayList<DBStock> stocks) {
		this.stocks = stocks;
	}
	
	
}
