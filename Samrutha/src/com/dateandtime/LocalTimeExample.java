package com.dateandtime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeExample {
	public static void main(String[] args) {
		
		ZoneId india = ZoneId.of("Asia/Kolkata");
		LocalDate date = LocalDate.parse("2024-05-15");
		LocalTime time = LocalTime.of(9, 30);
		ZonedDateTime courseStart = ZonedDateTime.of(date, time, india);
		ZonedDateTime fromHere = ZonedDateTime.now();
		ZonedDateTime newCourseStart = courseStart.plusDays(2).minusMinutes(30);
		System.out.println("Course start: " + courseStart);
		System.out.println("From here: " + fromHere);
		System.out.println("New course start: " + newCourseStart);
		
		
		// Re-scheduling the meet
		System.out.println();
		LocalDate meetDate = LocalDate.of(2024, 04, 24);
		LocalTime meetTime = LocalTime.of(15, 00);
		ZonedDateTime actualMeet = ZonedDateTime.of(meetDate, meetTime, india);
		System.out.println("Actual meet: " + actualMeet);
		ZonedDateTime newMeet = actualMeet.plusDays(1);
		System.out.println("Re-scheduled meet: " + newMeet);
		
		//format() -----> date to string
		System.out.println();
		LocalDateTime now = LocalDateTime.now();
		System.out.println("Before formating: " + now);
		DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ss");
		String formattedDateTime = now.format(formatTime);
		System.out.println("After formating: " + formattedDateTime);
		
		//parse() ------> string to date
		String birthDate="Apr 24 2003";
		System.out.println("Before parsing: " + birthDate);
		LocalDate dates = LocalDate.parse(birthDate,DateTimeFormatter.ofPattern("MMM dd yyyy"));
		System.out.println("After parsing: " + dates);
		String birthTimes = "01:45";
		System.out.println("Before parsing: " + birthTimes);
		LocalTime times = LocalTime.parse(birthTimes, DateTimeFormatter.ofPattern("HH:mm"));
		System.out.println("After parsing: " + times);
	}
}
