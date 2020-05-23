package cn.Bookspf.model.RO;

import java.util.ArrayList;

import cn.Bookspf.model.DO.DBBook;

public class BookResponse extends Response{
	private ArrayList<DBBook> books;

	
	public BookResponse() {}
	public BookResponse(ArrayList<DBBook> books) {
		this.books=books;
	}
	
	public ArrayList<DBBook> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<DBBook> books) {
		this.books = books;
	}
	
	
}
