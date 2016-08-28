package com.fsbay.example.model;

public class Student {
	@Override
	public String toString() {
		return "Student [name=" + name + ", sex=" + sex + "]";
	}

	private String name;
	private String sex;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
