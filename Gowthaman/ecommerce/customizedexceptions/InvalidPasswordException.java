package com.ecommerce.customizedexceptions;

public class InvalidPasswordException extends Exception{
	public InvalidPasswordException(String message) {
		super(message);
	}
}
