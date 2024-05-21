package com.carrentalsystem.car;

import java.util.Comparator;
///it has attributes of car
public class Car implements Comparator<Car>{
     private long carId;
     private String carName;
     private String carModel;
     private long rentalRate;
     private String availability;
     private int seatNumber;
     private int baggage;
     private String fuelType;
     private String mileage;
     private String vehicleNumber;
     private String carType;
     public Car() {
    	 
     }
	public Car(long carId, String carName, String carModel, long rentalRate, String availability, int seatNumber,
			int baggage, String fuelType, String mileage,String vehicleNumber,String carType) {
		super();
		this.carId = carId;
		this.carName = carName;
		this.carModel = carModel;
		this.rentalRate = rentalRate;
		this.availability = availability;
		this.seatNumber = seatNumber;
		this.baggage = baggage;
		this.fuelType = fuelType;
		this.mileage = mileage;
		this.vehicleNumber=vehicleNumber;
		this.carType=carType;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public long getCarId() {
		return carId;
	}
	public void setCarId(long carId) {
		this.carId = carId;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	
	 public int compare(Car car1, Car car2) {
         return Long.compare(car1.getCarId(), car2.getCarId());
     }
	//to view car details
	 public String toString() {
		    return String.format("%-12s%-14s%-13s%-15s%-6s%-9s%-11s%-10s%-17s%-10s\n",
		            "CAR_NAME", "MODEL", "RENTAL_RATE", "AVAILABILITY", "SEAT", "BAGGAGE",
		            "FUEL_TYPE", "MILEAGE", "VEHICLE_NUMBER", "CAR_TYPE") +
		            String.format("%-12s%-14s%-13d%-15s%-6d%-9d%-11s%-10s%-17s%-10s\n",
		             carName, carModel, rentalRate, availability, seatNumber, baggage,
		            fuelType, mileage, vehicleNumber, carType);
		}









	public long getRentalRate() {
		return rentalRate;
	}
	public void setRentalRate(long rentalRate) {
		this.rentalRate = rentalRate;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public int getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	public int getBaggage() {
		return baggage;
	}
	public void setBaggage(int baggage) {
		this.baggage = baggage;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
     
}
