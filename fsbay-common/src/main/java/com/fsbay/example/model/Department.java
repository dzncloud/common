package com.fsbay.example.model;

import java.util.ArrayList;
import java.util.List;

public class Department {
	private String description;

	private List<Student> students = new ArrayList<Student>();

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void addStudent(Student stu){
		students.add(stu);
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Department [description=" + description + ", students="
				+ students + "]";
	}
}
