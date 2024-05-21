package com.exceptionDetails;
/**
 * The DateValidator class represents a custom exception for date validation errors.
 * @author Sanjai Prakash
 * @since 21 Feb 2024
 */
public class DateValidator extends Exception {
    DateValidator(String s)
    {
        super(s);
    }
}
