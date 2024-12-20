package com.school.management.exception;

public class InvaildPasswordException extends RuntimeException{
	
	
	private static final long serialVersionUID = 1L;

	public InvaildPasswordException(String message) {
		super(message);
	}

}
