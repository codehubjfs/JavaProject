package com.ecommerce.customizedexceptions;

public class InvalidEmailException extends Exception{
	public InvalidEmailException(String message) {
		super(message);
	}
}
