package cn.Bookspf.model.DTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Sale {
	private long id;
	private long saleid;
	private Integer bid;
	private String isbn;
	private String operator;
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
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getSaletime() {
		return saletime;
	}

	public void setSaletime(String saletime) {
		this.saletime = saletime;
	}
}
