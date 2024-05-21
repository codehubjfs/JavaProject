package com.carrentalsystem.exception;

public class InvalidPhoneNumberException extends Exception {
    public InvalidPhoneNumberException(String errorMessage) {
        super(errorMessage);
    }
}
