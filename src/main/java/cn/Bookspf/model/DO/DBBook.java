package cn.Bookspf.model.DO;

public class DBBook {
	private int bid;
	private String bookname;
	private int hot;
	private int sortid;
	private String author;
	private String description;
	private Double bookprice;
	private int added;
	private int number;
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public int getHot() {
		return hot;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}
	public int getSortid() {
		return sortid;
	}
	public void setSortid(int sortid) {
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
	public int getAdded() {
		return added;
	}
	public void setAdded(int added) {
		this.added = added;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		return "DBBook [bid=" + bid + ", bookname=" + bookname + ", hot=" + hot + ", sortid=" + sortid + ", author="
				+ author + ", description=" + description + ", bookprice=" + bookprice + ", added=" + added
				+ ", number=" + number + "]";
	}
	
}
