package com.math.fraction;

import com.math.helpers.MathUtil;

/**
 * 
 * @author ChaosFlurry
 *
 */
public class Fraction {

	int numerator;
	int denominator;
	
	public Fraction(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
		MathUtil.simplify(this);
	}
	
	@Override
	public String toString() {
		if(denominator == 1) return Integer.toString(numerator);
		return (numerator == 0) ? "0" : numerator + "/" + denominator;
	}
	
	/*public String toSimplify() {
		Fraction f0 = MathUtil.simplify(this);
		if (denominator == 1) return Integer.toString(f0.getNumerator());
		return (numerator == 0) ? "0" : f0.getNumerator() 
				+ "/" + f0.getDenominator();
	}*/
	
	public int getNumerator() {
		return numerator;
	}
	
	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}
	
	public int getDenominator() {
		return denominator;
	}
	
	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}
	
	public double decimalValue() {
		return (double) numerator / denominator;
	}
}
