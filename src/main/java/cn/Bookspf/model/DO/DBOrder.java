package cn.Bookspf.model.DO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DBOrder {
	private long id;
	private long orderid;
	private Integer uid;
	private Integer bid;
	private String isbn;
	private Double bookprice;
	private String createtime;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
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

	public Double getBookprice() {
		return bookprice;
	}

	public void setBookprice(Double bookprice) {
		this.bookprice = bookprice;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}




	
}
