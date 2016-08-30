package com.math.fraction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.math.helpers.MathUtil;

/***
 * 
 * @author ChaosFlurry
 *
 */
public class Fraction {

	int numerator;
	int denominator;

	public Fraction(int numerator, int denominator) {
		if (denominator < 0) {
			numerator *= -1;
			denominator *= -1;
		}
		this.numerator = numerator;
		this.denominator = denominator;
	}

	@Override
	public String toString() {
		if (isUndefined()) {
			return "Undefined";
		}
		if (denominator == 1)
			return Integer.toString(numerator);
		return (numerator == 0) ? "0" : numerator + "/" + denominator;
	}

	/**
	 * Returns a Fraction in its lowest terms. A fraction cannot be further
	 * simplified when its numerator or denominator is prime or 1. If the
	 * denominator of a fraction is negative, it is changed to positive.
	 * 
	 * @param f
	 *            The Fraction to be simplified
	 * @return A Fraction in lowest terms equivalent to f
	 */
	public Fraction simplify() {
		int numerator = this.numerator;
		int denominator = this.denominator;
		int gcd = MathUtil.gcd(numerator, denominator);
		numerator = (numerator / gcd);
		denominator = (denominator / gcd);
		if (denominator < 0) {
			numerator *= -1;
			denominator *= -1;
		}
		return new Fraction(numerator, denominator);
	}

	/**
	 * Returns a Fraction in its lowest terms. A fraction cannot be further
	 * simplified when its numerator or denominator is prime or 1. If the
	 * denominator of a fraction is negative, it is changed to positive.
	 * 
	 * @param f
	 *            The Fraction to be simplified
	 * @return A Fraction in lowest terms equivalent to f
	 */
	public static Fraction simplify(Fraction f) {
		int numerator = f.getNumerator();
		int denominator = f.getDenominator();
		int gcd = MathUtil.gcd(numerator, denominator);
		numerator = (numerator / gcd);
		denominator = (denominator / gcd);
		if (denominator < 0) {
			numerator *= -1;
			denominator *= -1;
		}
		return new Fraction(numerator, denominator);
	}
	
	public static Fraction parseFraction(String s) 
			throws FractionFormatException {
		s = s.trim();
		Pattern number = Pattern.compile("^(-?\\d+)$");
		Pattern fraction = Pattern.compile("^(-?\\d+)\\/(-?\\d+)$");
		Matcher numberMatcher = number.matcher(s);
		Matcher fractionMatcher = fraction.matcher(s);
		
		if (numberMatcher.find()) {
			return new Fraction(Integer.parseInt(numberMatcher.group(1)), 1);
		} else if (fractionMatcher.find()) {
			return new Fraction(Integer.parseInt(fractionMatcher.group(1)),
					Integer.parseInt(fractionMatcher.group(2)));
		} else {
			throw new FractionFormatException("Invalid Fraction.");
		}
	}

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

	public boolean isUndefined() {
		if (denominator == 0)
			return true;
		else
			return false;
	}

	public double decimalValue() {
		return (double) numerator / denominator;
	}
}
