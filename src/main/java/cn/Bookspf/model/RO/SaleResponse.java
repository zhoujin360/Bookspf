package cn.Bookspf.model.RO;

import java.util.ArrayList;

import cn.Bookspf.model.DO.DBSale;
import cn.Bookspf.model.DTO.Sale;

public class SaleResponse extends Response{
	private ArrayList<DBSale> sales;
	
	public SaleResponse() {}
	public SaleResponse(ArrayList<DBSale> sales) {
		this.sales=sales;
	}
	public ArrayList<DBSale> getSales() {
		return sales;
	}
	public void setSales(ArrayList<DBSale> sales) {
		this.sales = sales;
	}
	
	
}
