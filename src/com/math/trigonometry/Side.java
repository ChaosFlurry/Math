package com.math.trigonometry;

public class Side {

	public double angle;
	public double length;
	
	public Side(double angle, double length) {
		this.length = length;
		this.angle = angle;
	}
	
	public double getLength() {
		return length;
	}
	
	public double getAngle() {
		return angle;
	}
	
}
