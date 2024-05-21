package com.ecommerce.customizedexceptions;


import java.util.Map;

import com.ungalkadai.components.SubCategory;

public class Validation {
	public static int isOptionValid(int start,int end,int option) throws InvalidMenuChoiceException {
		if(option>end | option<start) {
			throw new InvalidMenuChoiceException("option should be between "+start+" to "+end+"");
		}else {
			return option;
		}
	}
	
	public static String isMobileNumberValid(String mobileNumber) throws InvalidMobileNumberException{
		 if(!mobileNumber.matches("^[9876]\\d{9}$"))
	        {
	            throw new InvalidMobileNumberException("Invalid Phone Number");
	        }
	        return mobileNumber;
	}
	
	public static String isEmailIdValid(String email) throws InvalidEmailException{
		if(!email.matches("^[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z]{2,4}$"))
        {
            throw  new InvalidEmailException("EmailAddress is invalid format - Enter Valid Email Address");
        }
        return email;
	}
	
	public static String isPasswordValid(String password) throws InvalidPasswordException{
		if(!password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%#?&])[A-Za-z\\d@$!%*#?&]{8,}$"))
        {
            throw new InvalidPasswordException("Invalid Password");
        }
        return password;
	}
	
	public static String isAadharNumberValid(String aadharNumber) throws InvalidAadharNumberException{
		if(!aadharNumber.matches("^[1-9]\\d{3}\\d{4}\\d{4}$")) {
			throw new InvalidAadharNumberException("Invalid aadhar Number");
		}
		return aadharNumber;
	}
	
	public static int isSubCategoryValid(int id,Map<Integer,SubCategory> subCategories) throws InvalidSubCategoryException{
		if(!subCategories.containsKey(id)) {
			throw new InvalidSubCategoryException("choosen Sub-category does not exit");
		}
		return id;
	}
	
	public static int isCvvValid(int cvv) throws InvalidCvvException{
		if(!(Integer.toString(cvv).length()==3 || Integer.toString(cvv).length()==4)) {
			throw new InvalidCvvException("Provided  Card Verification Value(Cvv) is not valid");
		}
		return cvv;
	}
	
	public static String isCreditCardNumberValid(String card) throws InvalidCreditCardNumberException{
		if(!(card.replaceAll(" ", "").length()==16)) {
			throw new InvalidCreditCardNumberException("Provided  Card card Number is not valid");
		}
		return card;
	}
}
