package cn.Bookspf.model.DO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DBStock {
	private long id;
	private long stockid;
	private Integer bid;
	private String bookname;
	private String isbn;
	private Integer comeout;
	private String cometime;
	private String outtime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getStockid() {
		return stockid;
	}
	public void setStockid(long stockid) {
		this.stockid = stockid;
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
	public Integer getComeout() {
		return comeout;
	}
	public void setComeout(Integer comeout) {
		this.comeout = comeout;
	}

	public String getCometime() {
		return cometime;
	}

	public void setCometime(String cometime) {
		this.cometime = cometime;
	}

	public String getOuttime() {
		return outtime;
	}

	public void setOuttime(String outtime) {
		this.outtime = outtime;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
}
