package com.fsbay.example.model;

import java.util.ArrayList;

public class Reader {
	private String name;
	private ArrayList<Book> books = new ArrayList<Book>();

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void addBook(Book book) {
		this.books.add(book);
	}

	public ArrayList<Book> getBooks() {
		return this.books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

}