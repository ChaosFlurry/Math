package com.math.trigonometry;

public class TriangleCreationException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public TriangleCreationException(String message) {
        super(message);
    }
	
    public TriangleCreationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
