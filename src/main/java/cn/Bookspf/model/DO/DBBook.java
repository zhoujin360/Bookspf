package cn.Bookspf.model.DO;

public class DBBook {
	private Integer bid;
	private String bookname;
	private Integer hot;
	private Integer sortid;
	private String sortname;
	private String author;
	private String description;
	private Double bookprice;
	private Integer added;
	private Integer number;
	
	
	
	public Integer getBid() {
		return bid;
	}



	public void setBid(Integer bid) {
		this.bid = bid;
	}



	public String getBookname() {
		return bookname;
	}



	public void setBookname(String bookname) {
		this.bookname = bookname;
	}



	public Integer getHot() {
		return hot;
	}



	public void setHot(Integer hot) {
		this.hot = hot;
	}



	public Integer getSortid() {
		return sortid;
	}



	public void setSortid(Integer sortid) {
		this.sortid = sortid;
	}



	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Double getBookprice() {
		return bookprice;
	}



	public void setBookprice(Double bookprice) {
		this.bookprice = bookprice;
	}



	public Integer getAdded() {
		return added;
	}



	public void setAdded(Integer added) {
		this.added = added;
	}



	public Integer getNumber() {
		return number;
	}



	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	@Override
	public String toString() {
		return "DBBook [bid=" + bid + ", bookname=" + bookname + ", hot=" + hot + ", sortid=" + sortid + ", author="
				+ author + ", description=" + description + ", bookprice=" + bookprice + ", added=" + added
				+ ", number=" + number + "]";
	}
	
}
