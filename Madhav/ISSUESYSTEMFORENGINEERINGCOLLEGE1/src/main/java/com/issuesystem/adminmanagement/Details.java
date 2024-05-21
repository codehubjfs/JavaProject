package com.issuesystem.adminmanagement;

import java.time.LocalDate;

public class Details {
	private String username;
	private String password;
	private String role;
	private int departmentId;
	private LocalDate dateOfJoining;
	private String mailId;
	private String section;
	private String name;
	private int year;
	private String number;

	public Details(String username,String number, String password,String mailId,LocalDate dateOfJoining, int year,String section, String role, int department_id,String name) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.departmentId = department_id;
		this.dateOfJoining = dateOfJoining;
		this.mailId = mailId;
		this.section = section;
		this.name = name;
		this.year = year;
	}



	public Details(String username,String password,int departmentId,String mailId,String name,String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.departmentId = departmentId;
		this.mailId = mailId;
		this.name = name;
			
	}
	
	public Details(String username,String password,String mailId,String name,String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.mailId = mailId;
		this.name = name;
			
	}
	



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public int getDepartmentId() {
		return departmentId;
	}



	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}



	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}



	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}



	public String getMailId() {
		return mailId;
	}



	public void setMailId(String mailId) {
		this.mailId = mailId;
	}



	public String getSection() {
		return section;
	}



	public void setSection(String section) {
		this.section = section;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getYear() {
		return year;
	}



	public void setYear(int year) {
		this.year = year;
	}



	public String getNumber() {
		return number;
	}



	public void setNumber(String number) {
		this.number = number;
	}







	
	
	
	
	

}
