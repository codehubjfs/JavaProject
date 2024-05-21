package com.ecommerce.customizedexceptions;

public class InvalidCreditCardNumberException extends Exception{
	public InvalidCreditCardNumberException(String message) {
		super(message);
	}
}
