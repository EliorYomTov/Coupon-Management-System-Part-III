package com.elior.couponManagementSystem.exception;

public class SecuritySystemException extends Exception {

	private static final long serialVersionUID = 1L;

	public SecuritySystemException() {
		super("Security - Token not found, please Login!");
	}
	
	public SecuritySystemException(String message) {
		super(message);
	}
	
//	super("Security - Unauthorized!");
}
