package cn.Bookspf.model.RO;

import java.util.ArrayList;

import cn.Bookspf.model.DO.DBPurchase;

public class PurchaseResponse extends Response{
	private ArrayList<DBPurchase> purchases;
	
	public PurchaseResponse() {}
	public PurchaseResponse(ArrayList<DBPurchase> purchases) {
		this.purchases=purchases;
	}
	public ArrayList<DBPurchase> getPurchases() {
		return purchases;
	}
	public void setPurchases(ArrayList<DBPurchase> purchases) {
		this.purchases = purchases;
	}
	
	
}
