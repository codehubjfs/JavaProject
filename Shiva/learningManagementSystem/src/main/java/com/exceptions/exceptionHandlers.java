package com.exceptions;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class exceptionHandlers {
	public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z]{4,}@[a-zA-Z]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
	public static boolean isNumber(String input) {
        return Pattern.matches("-?\\d+(\\.\\d+)?", input);
    }
	public static boolean isString(String input) {
        return input.matches("[a-zA-Z]+");
    }
	public static boolean isNumberInt(String input) {
        return Pattern.matches("\\d+", input);
    }
	public static boolean isDate(String date) {
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(date).matches();
    }
	public static boolean containsSpecialCharacters(String input) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\s]");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
}
