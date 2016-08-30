package com.math.radical;

public class RadicalOperationException extends Exception {
	private static final long serialVersionUID = 1L;

	public RadicalOperationException(String message) {
        super(message);
    }
	
    public RadicalOperationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
