package com.hallbookingsystem.customexception;

public class PasswordException extends RuntimeException{
    public PasswordException(String message){
        super(message);
    }
}
