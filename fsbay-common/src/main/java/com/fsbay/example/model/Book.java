package com.fsbay.example.model;

import java.util.ArrayList;

public class Book {
	private String title;
	private String author;
	private ArrayList chapters = new ArrayList(20);

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		return this.author;
	}

	/**
	 * 用于配合不同方法的解析
	 */
	public void setInfo(String title, String author) {
		this.title = title;
		this.author = author;
	}

	public void addReadedChapter(Chapter chapter) {
		this.chapters.add(chapter);
	}

	public ArrayList getReadedChapters() {
		return this.chapters;
	}
}