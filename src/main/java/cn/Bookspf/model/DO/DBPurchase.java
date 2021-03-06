package cn.Bookspf.model.DO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DBPurchase {
	private long id;
	private long purchaseid;
	private Integer bid;
	private String bookname;
	private String isbn;
	private Integer number;
	private Double purchaseprice;
	private Double total;
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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Double getPurchaseprice() {
		return purchaseprice;
	}

	public void setPurchaseprice(Double purchaseprice) {
		this.purchaseprice = purchaseprice;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
}
