package com.ecommerce.customizedexceptions;

public class InvalidCvvException extends Exception{
	public InvalidCvvException(String message) {
		super(message);
	}
}
