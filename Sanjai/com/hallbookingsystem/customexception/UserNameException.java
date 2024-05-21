package com.hallbookingsystem.customexception;

public class UserNameException extends  RuntimeException{
    public UserNameException(String message){
        super(message);
    }
}
