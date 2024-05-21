package com.carrentalsystem.exception;

public class Validate {
     public static String validatePassword(String password) throws PasswordException {
    	 if(!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")) {
    	    throw new PasswordException("Enter Valid Password");
    	 }
    	 return password;
    	 
     }
     public static String validateUsername(String username) throws UsernameException {
    	 if(!username.matches("^[a-zA-Z0-9_-]{3,16}$")) {
    		 throw new UsernameException("Enter Valid Username");
    	 }
    	 return username;
     }
     
     public static int ValidateNumber(String number)throws NumberException{
    	 if(!number.matches("^[0-9]+$")) {
    		 throw new NumberException("Invalid input Enter a Number");
    	 }
    	 return Integer.parseInt(number);
     }
     public static String validateEmail(String email) throws InvalidEmailException {
         if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
             throw new InvalidEmailException("Email address format is invalid.");
         }
         return email;
     }
     public static long validatePhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
         // Regular expression for a valid phone number 
         String regex = "[6789]{1}[0-9]{9}";

         if (!phoneNumber.matches(regex)) {
             throw new InvalidPhoneNumberException("Phone number format is invalid.");
         }
         return Long.parseLong(phoneNumber);
     }
     public static void validateFirstName(String firstName) throws InvalidFirstNameException {
         if (firstName == null || firstName.trim().isEmpty()) {
             throw new InvalidFirstNameException("First name cannot be null or empty.");
         }
        
     }

     public static void validateLastName(String lastName) throws InvalidLastNameException {
         if (lastName == null || lastName.trim().isEmpty()) {
             throw new InvalidLastNameException("Last name cannot be null or empty.");
         }
         // Add other validation rules as needed
     }
}

