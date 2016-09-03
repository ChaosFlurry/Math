package com.polynomial;

public class UndefinedSlopeException extends Exception {
	private static final long serialVersionUID = 1L;

	public UndefinedSlopeException(String message) {
        super(message);
    }
	
    public UndefinedSlopeException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
