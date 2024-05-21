package com.exceptionDetails;

/**
 * The AlphabetException class represents an exception that is thrown when a non-alphabetical character is encountered.
 * It extends the Exception class and is used to handle situations where an unexpected character occur.
 *
 * @author Sanjai Prakash
 * @since 21 Feb 2024
 */
public class AlphabetException extends Exception{
    AlphabetException(String s)
    {
        super(s);
    }
}
