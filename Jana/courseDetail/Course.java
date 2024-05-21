package com.courseDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Course {
	
	Scanner sc = new Scanner(System.in);
	
	private int cId;
	private String cName;
	
	List<Assessment> assessments = new ArrayList<>();
	
	public Course(int cId, String cName) {
		super();
		this.cId = cId;
		this.cName = cName;
	}
	

	public Course(int cId) {
		super();
		this.cId = cId;
	}



	public Course(String cName) {
		super();
		this.cName = cName;
	}



	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}


	public void setcName(String cName) {
		this.cName = cName;
	}


	public void removecourse(int cId) {
		
	}

}

