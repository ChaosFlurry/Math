package com.polynomial;

import java.util.ArrayList;
import java.util.List;

import com.math.fraction.Fraction;
import com.math.helpers.MathUtil;
import com.math.radical.Radical;

public class PolySolver {
	int a;
	int b;
	int c;
	
	List<String> stringSolutions;
	List<Double> decimalSolutions;
	
	public PolySolver(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
		stringSolutions = new ArrayList<String>();
		decimalSolutions = new ArrayList<Double>();
		solve();
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}
	
	public List<String> getStringSolutions() {
		return stringSolutions;
	}
	
	public List<Double> getDecimalSolutions() {
		return decimalSolutions;
	}
	
	public void solve() {
		Radical discriminant;
		int denominator;
		
		discriminant = new Radical(MathUtil.pow(b, 2) - 4*a*c, 2);
		denominator = 2*a;
		
		int numerator;
		int numeratorGCD;
		int combinedGCD;
		
		if (discriminant.isUndefined() || denominator == 0) {
			stringSolutions.add("No real solutions.");
			decimalSolutions.add(Double.NaN);
		} else if (discriminant.isANumber()) {
			//positive case
			numerator = -b + MathUtil.nthRoot(discriminant.getRadicand(), 2);
			denominator = 2*a;
			numeratorGCD = MathUtil.gcd(denominator, numerator);
			combinedGCD = MathUtil.gcd(numeratorGCD, denominator);
			numerator /= combinedGCD;
			denominator /= combinedGCD;
			
			stringSolutions.add(new Fraction(numerator, denominator).simplify().toString());
			decimalSolutions.add(new Fraction(numerator, denominator).decimalValue());
			
			//negative case
			if (discriminant.isZero() == false) {
				numerator = -b - MathUtil.nthRoot(discriminant.getRadicand(), 2);
				denominator = 2*a;
				numeratorGCD = MathUtil.gcd(denominator, numerator);
				combinedGCD = MathUtil.gcd(numeratorGCD, denominator);
				numerator /= combinedGCD;
				denominator /= combinedGCD;
				stringSolutions.add(new Fraction(numerator, denominator).simplify().toString());
				decimalSolutions.add(new Fraction(numerator, denominator).decimalValue());
			}
		} else if (discriminant.isUndefined() == false) {
			discriminant = discriminant.simplify();
			numeratorGCD = MathUtil.gcd(b, discriminant.getCoefficient());
			combinedGCD = MathUtil.gcd(denominator, numeratorGCD);
			discriminant.setCoefficient(discriminant.getCoefficient() / combinedGCD);
			denominator /= combinedGCD;
			String solution = "";
			//formatting
			if (-b / combinedGCD != 0) {
				//n + 0 = n, so 0 does not need to be displayed
				solution += "(";
				solution += Integer.toString(-b / combinedGCD);
			}
			solution += "+/-";
			solution += discriminant;
			if (-b / combinedGCD != 0) {
				//close bracket opened only if -b / combinedGCD is not 0
				solution += ")";
			}
			if (denominator != 1) {
				solution += "/";
				solution += denominator;
			}
			if (denominator == 0) {
				solution = "Undefined";
			}
			stringSolutions.add(solution);
			decimalSolutions.add((-b + discriminant.decimalValue()) / denominator);
			decimalSolutions.add((-b - discriminant.decimalValue()) / denominator);
		}
	}
}
