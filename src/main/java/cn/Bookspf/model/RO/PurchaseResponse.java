package cn.Bookspf.model.RO;

import java.util.ArrayList;

import cn.Bookspf.model.DO.DBPurchase;
import cn.Bookspf.model.DTO.PurchaseStatistics;

public class PurchaseResponse extends Response{
	private ArrayList<PurchaseStatistics> purchases;
	private ArrayList<DBPurchase> purchasesinfo;

	public PurchaseResponse() {}

	public ArrayList<PurchaseStatistics> getPurchases() {
		return purchases;
	}

	public void setPurchases(ArrayList<PurchaseStatistics> purchases) {
		this.purchases = purchases;
	}

	public ArrayList<DBPurchase> getPurchasesinfo() {
		return purchasesinfo;
	}

	public void setPurchasesinfo(ArrayList<DBPurchase> purchasesinfo) {
		this.purchasesinfo = purchasesinfo;
	}
}
