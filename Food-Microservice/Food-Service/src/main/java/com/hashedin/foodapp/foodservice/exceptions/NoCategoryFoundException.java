package com.hashedin.foodapp.foodservice.exceptions;

public class NoCategoryFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3787909924461243863L;
	
	public NoCategoryFoundException(String message) {
		super(message);
	}

}
