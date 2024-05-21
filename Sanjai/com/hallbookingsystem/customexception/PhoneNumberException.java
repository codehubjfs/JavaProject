package com.hallbookingsystem.customexception;

public class PhoneNumberException extends RuntimeException{
    public PhoneNumberException(String s){
        super(s);
    }
}
