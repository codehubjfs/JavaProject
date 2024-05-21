package com.issuesystem.userdefinedxception;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateException {
    public static String checkDate(Scanner sc) {
        String output = null;
        while (true) {
            SimpleDateFormat expectedDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            expectedDateFormat.setLenient(false); // Set lenient mode to false to enforce strict parsing

            // Prompt user for input date
            System.out.print("Enter the date (DD-MMM-YYYY) like (26-mar-2002): ");
            String dateString = sc.nextLine();

            Date date = null;
            ParseException parseException = null;

            try {
                java.util.Date utilDate = expectedDateFormat.parse(dateString);
                date = new Date(utilDate.getTime()); // Convert to java.sql.Date
                output = expectedDateFormat.format(date);
                break;
            } catch (ParseException e) {
                parseException = e;
                System.out.println("Invalid date format. Please enter date in the format DD-MMM-YYYY.");
            }
        }
        return output;
    }   

}
