package com.exceptionDetails;
/**
 * The InvalidInput class represents an exception that is thrown when an invalid input is encountered.
 * This exception extends the Exception class.
 *
 * @author Sanjai Prakash
 * @since 24 Feb 2024
 */
public class InvalidInput extends Exception{
    public InvalidInput(String s)
    {
        super(s);
    }
}
