package com.math.polynomial;

public class PointFormatException extends Exception{
	private static final long serialVersionUID = 1L;

	public PointFormatException(String message) {
        super(message);
    }
	
    public PointFormatException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
