package com.ecommerce.customizedexceptions;

public class InvalidMenuChoiceException extends Exception{
	public InvalidMenuChoiceException(String msg) {
		super(msg);
	}
}
