package cn.Bookspf.model.DO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DBOrder {
	private Integer id;
	private Integer orderid;
	private Integer uid;
	private Integer bid;
	private String isbn;
	private Double bookprice;
	private Integer paid;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createtime;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date paytime;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
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

	public Integer getPaid() {
		return paid;
	}

	public void setPaid(Integer paid) {
		this.paid = paid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getPaytime() {
		return paytime;
	}

	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}

	@Override
	public String toString() {
		return "DBOrder [id=" + id + ", orderid=" + orderid + ", uid=" + uid + ", bid=" + bid + ", isbn=" + isbn
				+ ", bookprice=" + bookprice + ", paid=" + paid + ", createtime=" + createtime + ", paytime=" + paytime
				+ "]";
	}
	
}
