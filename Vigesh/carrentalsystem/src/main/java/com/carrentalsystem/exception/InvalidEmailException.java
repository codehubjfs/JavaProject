package com.carrentalsystem.exception;
//validating email
public class InvalidEmailException extends Exception {
    /**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

	public InvalidEmailException(String errorMessage) {
        super(errorMessage);
    }
}
