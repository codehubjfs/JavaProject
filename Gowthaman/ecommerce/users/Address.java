package com.ecommerce.users;

public class Address {
	private int doorNumber;
	private String streetName;
	private String pincode;
	private String district;
	private String state;
	private String country;
	
	public Address() {
		
	}
	
	public Address(int doorNumber,String streetName,String pincode,String district,String state,String country) {
		this.doorNumber = doorNumber;
		this.streetName = streetName;
		this.pincode = pincode;
		this.district = district;
		this.state = state;
		this.country = country;
	}
	
	public void setDoorNumber(int doorNumber) {
		this.doorNumber  = doorNumber;
	}
	
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	public void setPincode(String pincode) {
		this.pincode  = pincode;
	}
	
	public void setDistrict(String district) {
		this.district = district;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public int getDoorNumber() {
		return doorNumber;
	}
	
	public String getStreetName() {
		return  streetName;
	}
	
	public String getPincode() {
		return pincode;
	}
	
	public String getDistrict() {
		return district;
	}
	
	public String getState() {
		return state;
	}
	
	public String getCountry() {
		return country;
	}
	
	@Override 
	public String toString() {
		return doorNumber+","+streetName+","+pincode+","+district+","+state+","+country;
	}
}
