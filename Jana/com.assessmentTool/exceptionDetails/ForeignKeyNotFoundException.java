package com.exceptionDetails;

public class ForeignKeyNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ForeignKeyNotFoundException(String s){
		super();
		System.err.println(s);
	}

}
