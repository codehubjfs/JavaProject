package com.dateandtime;

import java.time.*;

public class TimeZoneDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ZonedDateTime zone = ZonedDateTime.now();
		System.out.println("Zoned date and time with default: " + zone);
		
		ZoneId id1 = ZoneId.systemDefault();
		System.out.println("Default zone id: " + id1);
		
		//Default UST time
		System.out.println("Default UST time: " + Instant.now());
		
		//particular zone
		ZoneId id2 = ZoneId.of("Asia/Kolkata");
		System.out.println("Particular zoneId: " + id2);
		ZoneId id3 = ZoneId.of("Asia/Tokyo");
		System.out.println("Particular zoneId: " + id3);
		
		//Available time zone
		System.out.println("Available time zones: ");
		System.out.println(ZoneId.getAvailableZoneIds());
		
		
		LocalTime localtime = LocalTime.now(id3);
		System.out.println("Local time of default time zone is: " + localtime);
		
		//limit and default values
		System.out.println();
		System.out.println("MAX time: " + LocalTime.MAX);
		System.out.println("MIN Time: " + LocalTime.MIN);
		System.out.println("MIDNIGHT time: " + LocalTime.MIDNIGHT);
		System.out.println("NOON time: " + LocalTime.NOON);
		
	}

}
