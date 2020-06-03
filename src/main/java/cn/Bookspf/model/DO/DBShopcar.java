package cn.Bookspf.model.DO;

public class DBShopcar {
	private long id;
	private long carid;
	private Integer uid;
	private Integer bid;
	private String bookname;
	private Double bookprice;
	private Integer booknumber;
	private Double total;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCarid() {
		return carid;
	}
	public void setCarid(long carid) {
		this.carid = carid;
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
	public Integer getBooknumber() {
		return booknumber;
	}
	public void setBooknumber(Integer booknumber) {
		this.booknumber = booknumber;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public Double getBookprice() {
		return bookprice;
	}

	public void setBookprice(Double bookprice) {
		this.bookprice = bookprice;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
}
