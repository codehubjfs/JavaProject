package com.dateandtime;
import java.time.*;
import java.time.temporal.ChronoUnit;
public class TimeDemo {

	public static void main(String[] args) {
		//find current time
		LocalTime time = LocalTime.now();
		System.out.println("Current time: " + time);
		
		//Check out time is after
		LocalTime outTime = LocalTime.parse("17:30:00");
		if (time.isAfter(outTime))
			System.out.println("Out time is after");
		
		//Check out time is before
		if(time.isBefore(outTime))
			System.out.println("Out time is before");
		
		//find no of hours to sleep
		LocalTime bedTime = LocalTime.parse("22:30:00");
		System.out.println("Time to reach sleeping time: " + bedTime.minusHours(time.getHour()));
		
		//Chrono unit
		System.out.println("get time: " + ChronoUnit.HOURS);
	}

}
