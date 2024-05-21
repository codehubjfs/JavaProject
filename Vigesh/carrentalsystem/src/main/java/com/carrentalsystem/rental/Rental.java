package com.carrentalsystem.rental;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Rental {
   private long bookingId;
   private LocalDateTime startDateTime;
   private LocalDateTime endDateTime;
   private String rentalStatus;
   private long amount;
public Rental(long bookingId, LocalDateTime startDateTime, LocalDateTime endDateTime, String rentalStatus,
		long amount) {
	super();
	this.bookingId = bookingId;
	this.startDateTime = startDateTime;
	this.endDateTime = endDateTime;
	this.rentalStatus = rentalStatus;
	this.amount = amount;
}
public long getBookingId() {
	return bookingId;
}
public void setBookingId(long bookingId) {
	this.bookingId = bookingId;
}
public LocalDateTime getStartDateTime() {
	return startDateTime;
}
public void setStartDateTime(LocalDateTime startDateTime) {
	this.startDateTime = startDateTime;
}
public LocalDateTime getEndDateTime() {
	return endDateTime;
}
public void setEndDateTime(LocalDateTime endDateTime) {
	this.endDateTime = endDateTime;
}
public String getRentalStatus() {
	return rentalStatus;
}
public void setRentalStatus(String rentalStatus) {
	this.rentalStatus = rentalStatus;
}
public long getAmount() {
	return amount;
}
public void setAmount(long amount) {
	this.amount = amount;
}
   
}
