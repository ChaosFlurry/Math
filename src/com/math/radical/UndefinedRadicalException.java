package com.math.radical;

public class UndefinedRadicalException extends Exception {
	private static final long serialVersionUID = 1L;

	public UndefinedRadicalException(String message) {
        super(message);
    }
	
    public UndefinedRadicalException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
