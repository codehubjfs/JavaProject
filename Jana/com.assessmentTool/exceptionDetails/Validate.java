package com.exceptionDetails;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Validate {
	
	static Scanner sc = new Scanner(System.in);
	public static String validateEmailValidate(String email) throws EmailException {
		if (!email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")) {
			throw new EmailException("Invalid Email, Please Enter Valid Email Address!");
		}
		return email;
	}

	public static String passwordValidate(String password) throws PasswordException {

		if (password.length() < 8) {
			throw new PasswordException("Password length must be at least 8 characters");
		}
		if (!password.matches(".*[a-z].*")) {
			throw new PasswordException("Password must contain at least one lowercase character");
		}
		if (!password.matches(".*[A-Z].*")) {
			throw new PasswordException("Password must contain at least one uppercase character");
		}
		if (!password.matches(".*[!@#$%^&()_+].*")) {
			throw new PasswordException("Password must contain at least one special character: !@#$%^&*()_+");
		}
		if (!password.matches(".*\\d.*")) {
			throw new PasswordException("Password must contain at least one digit");
		}
		return password;
	}

	public static boolean DateValidater(String date) throws DateValidator {
//		if (!date.matches("^(0[1-9]|[12]\\d|3[01])-(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)-\\d{4}$")) {
//			throw new DateValidator("Invalid date format. Please use dd-MMM-yyyy format.");
//		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false); // Disable lenient parsing

		try {
			java.util.Date parsedDate = sdf.parse(date);
			LocalDate localDate = parsedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate currentDate = LocalDate.now();

			if (localDate.isBefore(currentDate)) {
				throw new DateValidator("The date should not be in the past!");
			}

			return true;
		} catch (ParseException e) {
			throw new DateValidator("Invalid date format. Please enter date in the format yyyy-MM-dd.");
		}
	}

	public static boolean validateGender(String gender) {
		return "M".equals(gender) || "F".equals(gender) || "T".equals(gender);
	}

	public static boolean validateName(String name) {
	    // Name should contain only alphabetic characters and spaces
	    return name.matches("[a-zA-Z ]+");
	}


	public static boolean validateAnswer(String answer) throws InvalidOptionException {
		if (!answer.matches("[a-dA-D]")) {
			throw new InvalidOptionException(
					"Invalid option entered. Please enter only A, B, C, or D (case insensitive).");
		}
		return true;
	}
	public static String getValidTime(String message) {
		
        String timePattern = "^([01]\\d|2[0-3]):([0-5]\\d)$"; // Regular expression for HH:MM format (24-hour)
        Pattern pattern = Pattern.compile(timePattern);
        String time = "";
        boolean isValid = false;

        while (!isValid) {
            System.out.println(message);
            time = sc.next();

            Matcher matcher = pattern.matcher(time);
            if (matcher.matches()) {
                isValid = true;
            } else {
                System.out.println("Invalid time format. Please enter the time in HH:MM(24Hrs format).");
            }
        }
        return time;
    }
	
	public static double getValidDuration() {
        double duration = 0;
        boolean isValid = false;

        while (!isValid) {
            try {
                System.out.println("Enter Duration:");
                duration = sc.nextDouble();
                isValid = true; // Exit loop if input is valid
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for duration.");
                sc.next(); // Clear the invalid input
            }
        }

        return duration;
    }

    public static int getValidTotalMarks() {
        int totalMarks = 0;
        boolean isValid = false;

        while (!isValid) {
            try {
                System.out.println("Enter Total Marks:");
                totalMarks = sc.nextInt();
                isValid = true; // Exit loop if input is valid
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for total marks.");
                sc.next(); // Clear the invalid input
            }
        }

        return totalMarks;
    }

}
