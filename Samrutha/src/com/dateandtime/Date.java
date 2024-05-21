package com.dateandtime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class Date {
	public static void main(String[] args) {

				LocalDate localdate = LocalDate.now();
				System.out.println("Localdate: " + localdate);
				System.out.println("add 1 to Localdate: " + localdate.plusDays(1));
				System.out.println("minus 1 to Localdate: " + localdate.minusMonths(1));
				
				LocalDate birthday = LocalDate.of(2003, 5, 15);
				System.out.println("Bithday: " + birthday);
				
				boolean pastOrFuture = birthday.isBefore(localdate);
				System.out.println("Livinf in past? " + pastOrFuture);
				
				boolean leapYear = birthday.isLeapYear();
				System.out.println("Birthday is leap year or not: " + leapYear);
				
				DayOfWeek dayOfWeek = birthday.getDayOfWeek();
				System.out.println("Day of birthday: " + dayOfWeek);
				
				//parse - converts string to date
				LocalDate date = LocalDate.parse("2002-08-24");
				System.out.println("Date: " + date);
				
				//Chrono unit
				System.out.println();
				LocalDate prevMonth = localdate.minus(2,ChronoUnit.MONTHS);
				System.out.println(ChronoUnit.FOREVER);
				System.out.println("using chrono unit: " + prevMonth);
				
				System.out.println("Total number of days from 1970 to today: "+ localdate.toEpochDay());
				
				//isBefore and isAfter
				System.out.println("Is the after date: " + LocalDate.parse("2003-05-15").isAfter(localdate));
				System.out.println("Is the before date: " + LocalDate.parse("2003-05-15").isBefore(localdate));
				
				//TemporalAdjusters
				//To get 1st day of next month
				LocalDate dayOfNextMonth = localdate.with(TemporalAdjusters.firstDayOfNextMonth());
				System.out.println("1st day of next month: " + dayOfNextMonth + " " +dayOfNextMonth.getDayOfWeek());
				
				//get next monday
				LocalDate nextMonday = localdate.with(TemporalAdjusters.next(dayOfWeek.MONDAY));
				System.out.println("1st day of next monday: " + nextMonday + " " + nextMonday.getDayOfWeek());
			
				//Previous monday
				LocalDate prevMonday = localdate.with(TemporalAdjusters.previous(dayOfWeek.MONDAY));
				System.out.println("1st day of previous monday: " + prevMonday+ " " + prevMonth.getDayOfWeek());
				
				//last day of month
				LocalDate lastDayOfMonth = localdate.with(TemporalAdjusters.lastDayOfMonth());
				System.out.println("last day of month: " + lastDayOfMonth + " " + lastDayOfMonth.getDayOfWeek());
	}
}
