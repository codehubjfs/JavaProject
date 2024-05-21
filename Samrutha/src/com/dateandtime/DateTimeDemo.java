package com.dateandtime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class DateTimeDemo {

	public static void main(String[] args) {
		LocalDateTime  dateTime = LocalDateTime.now();
		System.out.println("Currrent date and time: "+dateTime);
		
		LocalDateTime  flightTime = LocalDateTime.parse("2024-04-05T19:30:45");
		System.out.println("Time to reach flight time: " + flightTime.minusDays(4).minusHours(dateTime.getHour()));
	}

}
