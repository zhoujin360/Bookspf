package cn.Bookspf.model.DTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Purchase {
	private long id;
	private long purchaseid;
	private Integer bid;
	private String isbn;
	private Integer purchaseprice;
	private Integer operator;
	private String purchasetime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPurchaseid() {
		return purchaseid;
	}
	public void setPurchaseid(long purchaseid) {
		this.purchaseid = purchaseid;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Integer getPurchaseprice() {
		return purchaseprice;
	}
	public void setPurchaseprice(Integer purchaseprice) {
		this.purchaseprice = purchaseprice;
	}

	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public String getPurchasetime() {
		return purchasetime;
	}

	public void setPurchasetime(String purchasetime) {
		this.purchasetime = purchasetime;
	}
}
