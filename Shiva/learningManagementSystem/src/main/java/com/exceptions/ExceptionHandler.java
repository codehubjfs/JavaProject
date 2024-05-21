package com.exceptions;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExceptionHandler {
	public static void passwordValidation(String password) throws NotIntegerException, InvalidPasswordException
	{
		if(password.length()<4)
		{
			throw new InvalidPasswordException("Password must be atleat 4 character length");
		}
	}
	public static void hasValidExtension(String filename) throws InvalidFileException {
        if(!( filename.endsWith(".pdf") || filename.endsWith(".ppt") || filename.endsWith(".docx")))
        {
        	throw new InvalidFileException("Invalid File Format");
        }
    }
	public static void isValidEmail(String email) throws InvalidEmailException {
        String emailRegex = "^[a-zA-Z]{4,}@[a-zA-Z]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()==false)
        {
        //matcher.matches();
        	throw new InvalidEmailException("Email invalid enter valid email .\nExample: JohnDoe@gmail.com");
        }
        if(email.length()<=6)
		{
			throw new InvalidEmailException("Enter email with more then 6 characters");
		}
    }
	public static void isDate(String date) throws InvalidDateException {
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(regex);
        if( ! pattern.matcher(date).matches())
        {
        	throw new InvalidDateException("Enter valid number");
        }
    }
	public static void isNumber(String input) throws IsNumberException {
        if( Pattern.matches("-?\\d+(\\.\\d+)?", input))
        {
        	 throw new IsNumberException("You have entered Number");
        }
    }
	public static void isLengthEnough(String input) throws LengthException
	{
		if(input.length()<4)
		{
			throw new LengthException("Entered Input should be atleast 4 Character length");
		}
	}
	public static void containsNumber(String input) throws ContainsNumberException {
        String regex = "\\d";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if(matcher.find())
        {
        	throw new ContainsNumberException("Do not Enter Number");
        }
    }
	public static void isDateAfterStartDate(LocalDate start,String Date) throws InvalidDateException
	{
		
	}
	public static void containsSpecialCharacters(String input) throws SpecialCharacterException {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\s]");
        Matcher matcher = pattern.matcher(input);
        if( matcher.find())
        {
        	throw new SpecialCharacterException("No special character allowed");
        }
        
    }
}
