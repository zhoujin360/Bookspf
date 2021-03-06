package cn.Bookspf.model.DO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DBSale {
	private long id;
	private long saleid;
	private Integer bid;
	private String bookname;
	private String isbn;
	private String saletime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getSaleid() {
		return saleid;
	}
	public void setSaleid(long saleid) {
		this.saleid = saleid;
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

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getSaletime() {
		return saletime;
	}

	public void setSaletime(String saletime) {
		this.saletime = saletime;
	}
}
