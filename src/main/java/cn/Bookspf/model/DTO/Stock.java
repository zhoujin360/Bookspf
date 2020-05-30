package cn.Bookspf.model.DTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Stock {
	private long id;
	private long stockid;
	private Integer bid;
	private String isbn;
	private Integer comeout;
	private String cmoetime;
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

	public String getCmoetime() {
		return cmoetime;
	}

	public void setCmoetime(String cmoetime) {
		this.cmoetime = cmoetime;
	}

	public String getOuttime() {
		return outtime;
	}

	public void setOuttime(String outtime) {
		this.outtime = outtime;
	}
}
