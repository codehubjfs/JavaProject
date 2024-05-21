package com.carrentalsystem.rental;

public class RentalPackage {
    
	private String duration;
	private String carType;
	private Double amount;
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public RentalPackage(String duration, String carType, Double amount) {
		super();
		this.duration = duration;
		this.carType = carType;
		this.amount = amount;
	}
	
}
