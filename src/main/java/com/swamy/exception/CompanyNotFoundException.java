package com.swamy.exception;

public class CompanyNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CompanyNotFoundException() {
		super();
	}
	
	public CompanyNotFoundException(String message) {
		super(message);
	}
	
}

