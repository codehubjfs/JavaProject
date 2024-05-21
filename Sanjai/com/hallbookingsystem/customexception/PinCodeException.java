package com.hallbookingsystem.customexception;

public class PinCodeException extends RuntimeException {
    public PinCodeException(String message){
        super(message);
    }
}
