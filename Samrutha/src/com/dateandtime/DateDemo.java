package com.dateandtime;

import java.time.LocalDate;

public class DateDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalDate dates = LocalDate.now();
		System.out.println(dates.getDayOfMonth()==13);
		
		//check friday or not
		LocalDate days = LocalDate.parse("2024-04-13");
		System.out.println(dates.getDayOfWeek().equals("FRIDAY"));
	}

}
