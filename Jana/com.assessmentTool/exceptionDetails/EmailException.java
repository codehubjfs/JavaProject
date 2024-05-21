package com.exceptionDetails;
/**
 * The EmailException class represents an exception that is thrown when an error related to email occurs.
 * It extends the Exception class.
 * @author Sanjai Prakash
 * @since 24 Feb 2024
 */

public class EmailException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	EmailException(String s)
    {
        super(s);
        
    }
}
