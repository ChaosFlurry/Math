package com.math.helpers;

import com.math.fraction.Fraction;
/***
 * 
 * @author Archiving
 * 
 * Utility class containing methods to manipulate Fractions and Radicals
 */
public class MathUtil {

	/*
	//TODO make it return a Fraction, and then change a bunch of code inside Fraction.java
	public static void simplify(Fraction f) {
		//Fraction f vs Fraction f
		int numerator = f.getNumerator();
		int denominator = f.getDenominator();
		int gcd = gcd(numerator, denominator);
		numerator = (numerator / gcd);
		denominator = (denominator / gcd);
		if (denominator < 0) {
			numerator *= -1;
			denominator *= -1;
		}	
	}
	*/
	
	/**
	 * Returns a fraction in its lowest terms. 
	 * A fraction cannot be further simplified when its numerator 
	 * or denominator is prime or 1. 
	 * If the denominator of a fraction is negative, it is changed to positive
	 * (by multiplying both the numerator and denominator by -1). 
	 * 
	 * @param f A Fraction to be simplified
	 * @return A Fraction in lowest terms equivalent to f
	 */
	/*
	public static Fraction simplify(Fraction f) {

		int numerator = f.getNumerator();
		int denominator = f.getDenominator();
		int gcd = gcd(numerator, denominator);
		numerator = (numerator / gcd);
		denominator = (denominator / gcd);
		if (denominator < 0) {
			numerator *= -1;
			denominator *= -1;
		}	
		return new Fraction(numerator, denominator);
	}
	*/
	
	/**
	 * Returns the reciprocal of a Fraction f (1/f), which can be represented 
	 * as d/n where n is the numerator and d is the denominator. 
	 * 
	 * @param f A Fraction
	 * @return The reciprocal of f as a Fraction
	 */
	public static Fraction reciprocal(Fraction f) {
		int numerator = f.getDenominator();
		int denominator = f.getNumerator();
		return new Fraction(numerator, denominator);
	}
	
	/**
	 * Returns the absolute value of a fraction. 
	 * The absolute value cannot be less than 0. 
	 * If the denominator of a fraction is negative, it is changed to positive
	 * (by multiplying both the numerator and denominator by -1). 
	 * 
	 * @param f A Fraction
	 * @return A Fraction which has the absolute value of f
	 */
	public static Fraction abs(Fraction f) {
		/*
		The numerator and denominator are changed to a positive value (if already
		not positive), since the absolute value of a fraction cannot equate to 
		a value less than 0.
		*/
		int numerator = (f.getNumerator() < 0) 
				? f.getNumerator() * -1 : f.getNumerator();
		int denominator = (f.getDenominator() < 0) 
				? f.getDenominator() * -1 : f.getDenominator();
		return new Fraction(numerator, denominator);
	}
	
	/**
	 * Uses the Euclidean algorithm to determine the greatest common 
	 * denominator of two numbers.
	 * 
	 * @param a	An integer
	 * @param b	Another integer
	 * @return The greatest common denominator of a and b
	 */
	public static int gcd(int a, int b) {
		//TODO: Refactor as a reciprocal function
		
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
	
	//Add methods:
	/**
	 * Adds an integer to a Fraction.
	 * 
	 * @param f A Fraction
	 * @param n An integer
	 * @return f + n as a Fraction
	 */
	public static Fraction add(Fraction f, int n) {
		int numerator = f.getNumerator();
		int denominator = f.getDenominator();
		
		Fraction result = new Fraction(
				numerator + n * denominator,
				denominator);
		result.simplify();
		return result;
	}
	
	/**
	 * Adds 2 Fractions together. 
	 * 
	 * @param f1 A Fraction
	 * @param f2 The Fraction to be added
	 * @return f1 + f2 as a Fraction
	 */
	public static Fraction add(Fraction f1, Fraction f2) {
		int f1n = f1.getNumerator();
		int f1d = f1.getDenominator();
		int f2n = f2.getNumerator();
		int f2d = f2.getDenominator();
		int gcd = gcd(f1d, f2d);
		
		Fraction result =  new Fraction(
				f1n * (f2d / gcd) + f2n * (f1d / gcd),
				f1d * (f2d / gcd));
		result.simplify();
		return result;
	}
	
	/**
	 * Subtracts an integer from a Fraction. 
	 * 
	 * @param f A Fraction
	 * @param n An integer
	 * @return f - n as a Fraction
	 */
	public static Fraction subtract(Fraction f, int n) {
		int numerator = f.getNumerator();
		int denominator = f.getDenominator();
		
		Fraction result = new Fraction(
				numerator - n * denominator,
				denominator);
		result.simplify();
		return result;
	}
	
	/**
	 * Subtracts 2 Fractions. 
	 * 
	 * @param f1 A Fraction
	 * @param f2 The fraction to be subtracted
	 * @return f1 - f2 as a Fraction
	 */
	public static Fraction subtract(Fraction f1, Fraction f2) {
		int f1n = f1.getNumerator();
		int f1d = f1.getDenominator();
		int f2n = f2.getNumerator();
		int f2d = f2.getDenominator();
		int gcd = gcd(f1d, f2d);
		
		Fraction result =  new Fraction(
				f1n * (f2d / gcd) - f2n * (f1d / gcd),
				f1d * (f2d / gcd));
		result.simplify();
		return result;
	}
	
	/**
	 * Mutliplies a Fraction by an integer. 
	 * 
	 * @param f A Fraction
	 * @param n An integer
	 * @return f * n as a Fraction
	 */
	public static Fraction multiply(Fraction f, int n) {
		int numerator = f.getNumerator();
		int denominator = f.getDenominator();
		Fraction result = new Fraction(
				numerator * n,
				denominator);
		result.simplify();
		return result;
	}
	
	/**
	 * Multiplies 2 Fractions. 
	 * 
	 * @param f1 A Fraction
	 * @param f2 Another Fraction
	 * @return f1 * f2 as a Fraction
	 */
	public static Fraction multiply(Fraction f1, Fraction f2) {
		int f1n = f1.getNumerator();
		int f1d = f1.getDenominator();
		int f2n = f2.getNumerator();
		int f2d = f2.getDenominator();
		
		Fraction result = new Fraction(
				f1n * f2n,
				f1d * f2d);
		result.simplify();
		return result;
	}
	
	/**
	 * Divides a Fraction by an integer. 
	 * 
	 * @param f A Fraction
	 * @param n An integer
	 * @return f / n as a Fraction
	 */
	public static Fraction divide(Fraction f, int n) {
		int numerator = f.getNumerator();
		int denominator = f.getDenominator();
		
		Fraction result = new Fraction(
				numerator,
				denominator * n);
		result.simplify();
		return result;
	}
	
	/**
	 * Divides 2 Fractions. 
	 * 
	 * @param f1 A Fraction
	 * @param f2 A Fraction
	 * @return f1 / f2 as a Fraction
	 */
	public static Fraction divide(Fraction f1, Fraction f2) {
		int f1n = f1.getNumerator();
		int f1d = f1.getDenominator();
		int f2n = f2.getNumerator();
		int f2d = f2.getDenominator();
		
		Fraction result = new Fraction(
				f1n * f2d,
				f1d * f2n);
		result.simplify();
		return result;
	}
	
	/**
	 * Raises a Fraction to the nth power. 
	 * 
	 * @param f A Fraction
	 * @param power An integer
	 * @return f ** n
	 */
	public Fraction pow(Fraction f, int power) {
		int numerator = f.getNumerator();
		int denominator = f.getDenominator();
		Fraction result = new Fraction(
				(int) Math.pow(numerator, power),
				(int) Math.pow(denominator, power));
		result.simplify();
		return result;
	}
}
