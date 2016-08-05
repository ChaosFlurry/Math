package com.math.helpers;

import com.math.fraction.Fraction;
/***
 * 
 * @author Archiving
 *
 */
public class MathUtil {

	/*
	//TODO make it return a Fraction, and then change a bunch of code inside Fraction.java
	public static void simplify(Fraction frac) {
		//WHEN TO ACTUALLY USE GETTERS
		int numerator = frac.getNumerator();
		int denominator = frac.getDenominator();
		int gcd = gcd(numerator, denominator);
		numerator = (numerator / gcd);
		denominator = (denominator / gcd);
		if (denominator < 0) {
			numerator *= -1;
			denominator *= -1;
		}	
	}
	*/
	
	public static Fraction simplify(Fraction frac) {
		//WHEN TO ACTUALLY USE GETTERS
		int numerator = frac.getNumerator();
		int denominator = frac.getDenominator();
		int gcd = gcd(numerator, denominator);
		numerator = (numerator / gcd);
		denominator = (denominator / gcd);
		if (denominator < 0) {
			numerator *= -1;
			denominator *= -1;
		}	
		return new Fraction(numerator, denominator);
	}
	
	
	
	/**
	 * Uses the Euclidean algorithm to determine the greatest common 
	 * denominator of two numbers.
	 * 
	 * @param a	an integer
	 * @param b	another integer
	 * @return	the greatest common denominator of a and b
	 */
	public static int gcd(int a, int b) {
		//Euclidean algorithm
		//gcd(a, b) == gcd(a % b, b)
		//when a or b == 0, the gcd is the non-zero value
		//(which is also the sum of a and b)
		
		int gcd;
		if (a == b) {
			gcd = a;
		} else {
			while (a != 0 && b != 0 ) {
				//temporary variable
				int t = b;
				b = a % b;
				a = t;
			}
			gcd = a + b;
		}
		return gcd;
	}
	
	public static int lcm(int a, int b) {
		return a / gcd(a, b) * b;
	}
	
	
}
