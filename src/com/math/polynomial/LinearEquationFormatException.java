package com.math.polynomial;

public class LinearEquationFormatException extends Exception {
	private static final long serialVersionUID = 1L;

	public LinearEquationFormatException(String message) {
        super(message);
    }
	
    public LinearEquationFormatException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
