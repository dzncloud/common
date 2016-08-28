package com.fsbay.example.model;

import java.util.ArrayList;
import java.util.List;

public class School {
	private String name;
	private String address;
	private List<Department> departments= new ArrayList<Department>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public void addDepartment(Department dept){
		departments.add(dept);
	}

	@Override
	public String toString() {
		return "School [name=" + name + ", address=" + address
				+ ", departments=" + departments + "]";
	}
}
