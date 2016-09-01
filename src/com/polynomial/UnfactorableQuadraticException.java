package com.polynomial;

public class UnfactorableQuadraticException extends Exception {
	private static final long serialVersionUID = 1L;

	public UnfactorableQuadraticException(String message) {
        super(message);
    }
	
    public UnfactorableQuadraticException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
