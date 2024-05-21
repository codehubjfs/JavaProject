package com.hallbookingsystem.customexception;


/**
 * The Validate class provides various static methods to validate user inputs.
 * @author Sanjai
 * @since 09-May-2024
 */
public class Validate {
    public static byte validateOption(String option) throws NumberInputException {
            if (!option.matches("\\d+")) {
                throw new NumberInputException(" Invalid Input- Enter valid Option");
            }
            return Byte.parseByte(option);
    }

    public static String validateName(String name) throws FullNameException {

            if (name.length() > 20) {
                throw new FullNameException("Name should be less than 20 characters ");
            }
            else if (!name.matches("^[a-zA-Z\\s]{3,}$")) {
                throw new FullNameException("Name should Contains only Alphabets space");
            }
            return name;
    }

    public static String validateNumber(String contactNumber) throws PhoneNumberException{
            if (!contactNumber.matches("^[9876]\\d{9}$")) {
                throw new PhoneNumberException("Invalid Phone Number");
            }
            return contactNumber;

    }

    public static String validateEmail(String email) throws EmailException{
            if (!email.matches("^[a-zA-Z0-9]+@[a-zA-Z.-]+\\.[a-zA-Z]{2,4}$")) {
                throw new EmailException("EmailAddress is invalid format - Enter Valid Email Address");
            }
            return email;
    }

    public static String validateUserName(String username) throws UserNameException {
            if (!username.matches("^[A-Za-z][A-Za-z0-9_]{7,29}$")) {
                throw new UserNameException("Username is Invalid format - Enter Valid Username");
            }
            return username;
    }


    public static String validatePassword(String password) throws PasswordException {
            if(!password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}")){
                throw new PasswordException("Password in Invalid Format - Enter Valid Password");
            }
            return password;
    }
    public static String validPincode(String pincodce) throws PinCodeException {
            if(!pincodce.matches("^[1-9]{1}[0-9]{2}\\s{1}[0-9]{3}$")){
                throw new PinCodeException("Invalid PinCode ");
            }
        return pincodce;
    }
    public static int validInteger(String number) throws IntegerException {
        if(!number.matches("\\d+")){
            throw new IntegerException("Invalid Number");
        }
        return Integer.parseInt(number);
    }
    public static String validateDate(String date) throws DateValidator {
        if(!date.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/((19|20)\\d\\d)$"))
        {
            throw new DateValidator("Invalid date format");
        }
        return date;
    }
}
