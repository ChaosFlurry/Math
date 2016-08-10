package com.math.radical;

public class RadicalObject extends Radical {
	int coefficient;
	int radicand;
	int index;
	
	public RadicalObject(int coefficient, int radicand, int index) {
		this.coefficient = coefficient;
		this.radicand = radicand;
		this.index = index;
	}
	
	@Override
	public String toString() {
		//temp
		return "RadicalObject";
	}
	
	@Override
	public double decimalValue() {
		
	}

	@Override
	public void simplify() {
		
	}
}
