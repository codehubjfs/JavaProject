package com.ecommerce.customizedexceptions;

public class InvalidAadharNumberException extends Exception{
	public InvalidAadharNumberException(String message) {
		super(message);
	}
}
