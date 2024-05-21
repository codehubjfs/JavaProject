package com.userdefiendexception;

/**
 * 
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import com.jobportal.users.*;

/**
 * The Validation class is the class that is used for the validation for all
 * the input entered by the customer.
 * @author GOPAL jamocha
 * @since 12 5 2024
 *
 */

public class Validation {
	public static int validateOption(String option)throws OptionException {
		if(!option.matches("\\d"))
		{
			throw new OptionException("Ivalid Option Enter Menu option number :");
		}
		return Integer.parseInt(option);
	}
		
	public static boolean validateCompanyName(String companyName) throws CompanyNameValidationException {
	    // Check if the company name is null or empty
	    if (companyName == null || companyName.isEmpty()) {
	        throw new CompanyNameValidationException("Company name cannot be null or empty.");
	    }
	    
	    // Check if the company name contains only alphabetic characters
	    if (!companyName.matches("[a-zA-Z]+")) {
	        throw new CompanyNameValidationException("Company name should contain only alphabetic characters.");
	    }
	    
	    return true; // Return true if validation passes
	}
	public static boolean ValidateCharacterSequences(String charachterSequence) throws CharachterSequenceException {
	    // Check if the company name is null or empty
	    if ( charachterSequence== null || charachterSequence.isEmpty()) {
	        throw new CharachterSequenceException("Invalid input. cannot be null or empty.");
	    }
	    
	    // Check if the company name contains  alphabetic characters and space
	    if (!charachterSequence.matches("[a-zA-Z, ]+")) {
	        throw new CharachterSequenceException("Invalid input. contain only alphabetic characters ");
	    }
	    
	    return true; // Return true if validation passes
	}
		
	
	public static boolean checkUserName(String userName)throws CheckUserNameException{
		Pattern pattern=Pattern.compile("^[a-zA-Z0-9_-]{3,20}$");
		Matcher matcher=pattern.matcher(userName);
		return matcher.matches();
	}
	
//	public static boolean checkUserName(String userName) throws CheckUserNameException {
//	    Pattern pattern = Pattern.compile("^[a-zA-Z0-9_-]{3,20}$");
//	    Matcher matcher = pattern.matcher(userName);
//	    if (!matcher.matches()) {
//	        throw new CheckUserNameException("Invalid username format");
//	    }
//	    return true;
//	}

	public static boolean checkPassword(String password) {
		Pattern pattern = Pattern.compile("(?=.*[a-z]+)(?=.*[A-Z]+)(?=.*[!@#$%^&*()_+]+)(?=.*[0-9]+).{8,}$");
		Matcher matcher=pattern.matcher(password);
		return matcher.matches();
	}
	
	 public static void validateNumberOfOpenings(int numberOfOpenings) throws NumberException {
	        if (numberOfOpenings <= 0) {
	            throw new NumberException("Invalid input. Number of openings must be positive.");
	        }
	        if (numberOfOpenings > 1000) {
	            throw new NumberException("Invalid input. Number of openings must be less than or equal to 1000.");
	        }
	    }
	
	public static boolean checkEmail(String email) {
		Pattern pattern= Pattern.compile("(?=.*^[a-zA-z]+)(?=.*[0-9]+)(?=.*(@))(?=.*[a-zA-Z-_]+)(?=.*[\\\\.])(?=.*[a-zA-Z]+).{8,}$");
		Matcher matcher=pattern.matcher(email);
		return matcher.matches();
	}
	
	public static boolean validateName(String name) {
		String regex = "[a-zA-Z]+([ '-][a-zA-Z]+)*";
		return name.matches(regex);
	}
	
	public static boolean validateContactNumber(String contactNumber) {
		String regex = "^\\d{10}$";
		return contactNumber.matches(regex);
	}
	
	public static boolean validateEmail(String email) {
		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		return email.matches(regex);
	}
	
	public static boolean validateAddress(String address) {
		String regex = "^[#.0-9a-zA-Z\\s,-]+$";
		return address.matches(regex);
	}
	
	public static boolean validateCity(String city) {
		String regex = "[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*";
		return city.matches(regex);
	}
	
	
	public static boolean validateCountry(String country) {
		String regex = "[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*";
		return country.matches(regex);
	}
	
	
	
  
//	  public static boolean isValidPhoneNumber(String phoneNumber) {
//	      return Pattern.matches("\\d{10}", phoneNumber); // Basic pattern matching for 10-digit phone numbers
//	  }
	public static boolean isValidPhoneNumber(String phoneNumber) {
	    return Pattern.matches("(\\+91|00|0)?[7-9]\\d{9}", phoneNumber); // Allows optional country code +91, 00, or 0 followed by 9 digits
	}

	
	  public static boolean isValidGender(String gender) {
		    return gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("other");
		}
	
	
	public static boolean isValidPostalCode(String postalcode) {
	
	return Pattern.matches("^\\d{6}$", postalcode);// US Zip Code pattern matching
	}
	
	public static boolean isValidDate(String dateStr) {
	    String regex = "^\\d{2}-\\d{2}-\\d{4}$"; // Regex pattern for DD-MM-YYYY format
	    return dateStr.matches(regex);
	}

	public static boolean isValidAddress(String address) {
	////Regular expression pattern for address (allowing alphanumeric characters, spaces, commas, periods, and hyphens)
	String regex = "^[a-zA-Z0-9\\s,.\\-]*$";
	return address.matches(regex);
	}
	public static boolean isValidCity(String city) {
	////Regular expression pattern for city (allowing alphabetic characters and spaces)
	String regex = "^[a-zA-Z\\s]*$";
	return city.matches(regex);
	}

	public static boolean isValidCountry(String country) {
	////Regular expression pattern for country (allowing alphabetic characters and spaces)
	String regex = "^[a-zA-Z\\s]*$";
	return country.matches(regex);
	}
	public static boolean CheckValidLoginDetails(Connection con, String username, String password, String statement) {
	    try {
	        String Username = null, Password = null;
	        PreparedStatement pet = con.prepareStatement(statement);
	        ResultSet set = pet.executeQuery();
	        while (set.next()) {
	            Username = set.getString(3);
	            Password = set.getString(4);
	            if (Username!=null && Password!=null && username.equals(Username) && password.equals(Password)) {
	                return true;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	public static boolean isValidNumber(String input) throws NumberException {
	    // Regular expression to match only digits
	    String regex = "\\d+"; // Match one or more digits
	    
	    if (input.matches(regex)) {
	        return true;
	    } else {
	        throw new NumberException("Input should contain only numeric characters.");
	    }
	}

}