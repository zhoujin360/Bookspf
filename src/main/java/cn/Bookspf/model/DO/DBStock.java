package cn.Bookspf.model.DO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DBStock {
	private long id;
	private long stockid;
	private Integer bid;
	private String isbn;
	private Integer comeout;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date stocktime;
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
	public Date getStocktime() {
		return stocktime;
	}
	public void setStocktime(Date stocktime) {
		this.stocktime = stocktime;
	}
	
}
