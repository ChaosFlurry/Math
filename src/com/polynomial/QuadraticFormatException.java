package com.polynomial;

public class QuadraticFormatException extends Exception {
	private static final long serialVersionUID = 1L;

	public QuadraticFormatException(String message) {
        super(message);
    }
	
    public QuadraticFormatException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
