package com.ecommerce.users;

public class Person {
	
	private String address;
	private String email;
	private long mobileNumber;
	
	public Person() {
		
	}
	
	public Person(String address,String email,long mobileNumber) {
		this.address = address;
		this.email = email;
		this.mobileNumber = mobileNumber;
	}
	
	public Person(String email,long mobileNumber) {
		this.email = email;
		this.mobileNumber = mobileNumber;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	
	public String getAddress() {
		return address;
	}
	
	public String getEmail() {
		return email;
	}
	
	public long getMobileNumber() {
		return mobileNumber;
	}
	
	@Override
	public String toString() {
		return "Email : "+email+"\nMobile Number :  "+mobileNumber+"\nAddress : "+address;
	}
	

}
