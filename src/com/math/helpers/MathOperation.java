package com.math.helpers;

import java.util.List;

import com.math.fraction.Fraction;

public class MathOperation {
	/**
	 * Adds an integer to a Fraction.
	 * 
	 * @param f
	 *            A Fraction
	 * @param n
	 *            An integer
	 * @return f + n as a Fraction
	 */
	public static Fraction add(Fraction f, int n) {
		int numerator = f.getNumerator();
		int denominator = f.getDenominator();

		Fraction result = new Fraction(numerator + n * denominator, denominator);
		MathUtil.simplify(result);
		return result;
	}

	/**
	 * Adds two Fractions.
	 * 
	 * @param f1
	 *            A Fraction
	 * @param f2
	 *            The Fraction to be added
	 * @return f1 + f2 as a Fraction
	 */
	public static Fraction add(Fraction f1, Fraction f2) {
		int f1n = f1.getNumerator();
		int f1d = f1.getDenominator();
		int f2n = f2.getNumerator();
		int f2d = f2.getDenominator();
		int gcd = MathUtil.gcd(f1d, f2d);

		int numerator = f1n * (f2d / gcd) + f2n * (f1d / gcd);
		int denominator = f1d * (f2d / gcd);

		Fraction result = new Fraction(numerator, denominator);
		MathUtil.simplify(result);
		return result;
	}

	/**
	 * Adds multiple Fractions.
	 * 
	 * @param fractions
	 *            An arbitrary amount of Fractions
	 * @return The sum of all Fractions as a Fraction
	 */
	public static Fraction add(Fraction... fractions) {
		Fraction result = new Fraction(0, 1);
		for (Fraction f : fractions) {
			result = MathOperation.add(result, f);
		}
		return result;
	}

	/**
	 * Adds a List of Fractions.
	 * 
	 * @param fractions
	 *            A List of Fractions
	 * @return The sum of all Fractions as a Fraction
	 */
	public static Fraction add(List<Fraction> fractions) {
		Fraction result = new Fraction(0, 1);
		for (Fraction f : fractions) {
			result = MathOperation.add(result, f);
		}
		return result;
	}

	/**
	 * Subtracts an integer from a Fraction.
	 * 
	 * @param f
	 *            A Fraction
	 * @param n
	 *            An integer
	 * @return f - n as a Fraction
	 */
	public static Fraction subtract(Fraction f, int n) {
		int numerator = f.getNumerator();
		int denominator = f.getDenominator();

		Fraction result = new Fraction(numerator - n * denominator, denominator);
		MathUtil.simplify(result);
		return result;
	}

	/**
	 * Subtracts a Fraction from an integer.
	 * 
	 * @param n
	 *            An integer
	 * @param f
	 *            A Fraction
	 * @return n - f as a Fraction
	 */
	public static Fraction subtract(int n, Fraction f) {
		int numerator = f.getNumerator();
		int denominator = f.getDenominator();

		Fraction result = new Fraction(n * denominator - numerator, denominator);
		MathUtil.simplify(result);
		return result;
	}

	/**
	 * Subtracts two Fractions.
	 * 
	 * @param f1
	 *            A Fraction
	 * @param f2
	 *            The fraction to be subtracted
	 * @return f1 - f2 as a Fraction
	 */
	public static Fraction subtract(Fraction f1, Fraction f2) {
		int f1n = f1.getNumerator();
		int f1d = f1.getDenominator();
		int f2n = f2.getNumerator();
		int f2d = f2.getDenominator();
		int gcd = MathUtil.gcd(f1d, f2d);

		int numerator = f1n * (f2d / gcd) - f2n * (f1d / gcd);
		int denominator = f1d * (f2d / gcd);

		Fraction result = new Fraction(numerator, denominator);
		MathUtil.simplify(result);
		return result;
	}

	/**
	 * Subtracts multiple Fractions.
	 * 
	 * @param fractions
	 *            An arbitrary amount of Fractions
	 * @return The difference of all Fractions as a Fraction
	 */
	public static Fraction subtract(Fraction... fractions) {
		Fraction result = new Fraction(0, 1);
		for (Fraction f : fractions) {
			result = subtract(result, f);
		}
		return result;
	}

	/**
	 * Subtracts a List of Fractions.
	 * 
	 * @param fractions
	 *            A List of Fractions
	 * @return The difference of all Fractions as a Fraction
	 */
	public static Fraction subtract(List<Fraction> fractions) {
		Fraction result = new Fraction(0, 1);
		for (Fraction f : fractions) {
			result = subtract(result, f);
		}
		return result;
	}

	/**
	 * Multiplies a Fraction by an integer.
	 * 
	 * @param f
	 *            A Fraction
	 * @param n
	 *            An integer
	 * @return f * n as a Fraction
	 */
	public static Fraction multiply(Fraction f, int n) {
		int numerator = f.getNumerator();
		int denominator = f.getDenominator();
		Fraction result = new Fraction(numerator * n, denominator);
		MathUtil.simplify(result);
		return result;
	}

	/**
	 * Multiplies two Fractions.
	 * 
	 * @param f1
	 *            A Fraction
	 * @param f2
	 *            Another Fraction
	 * @return f1 * f2 as a Fraction
	 */
	public static Fraction multiply(Fraction f1, Fraction f2) {
		int f1n = f1.getNumerator();
		int f1d = f1.getDenominator();
		int f2n = f2.getNumerator();
		int f2d = f2.getDenominator();

		int numerator = f1n * f2n;
		int denominator = f1d * f2d;

		Fraction result = new Fraction(numerator, denominator);
		MathUtil.simplify(result);
		return result;
	}

	/**
	 * Multiplies multiple Fractions.
	 * 
	 * @param fractions
	 *            An arbitrary amount of Fractions
	 * @return The product of all Fractions as a Fraction
	 */
	public static Fraction multiply(Fraction... fractions) {
		if (fractions.length == 0) {
			return new Fraction(0, 1);
		}

		Fraction result = new Fraction(1, 1);
		for (Fraction f : fractions) {
			result = multiply(f, result);
		}
		return result;
	}

	/**
	 * Multiplies a List of Fractions.
	 * 
	 * @param fractions
	 *            A List of Fractions
	 * @return The product all Fractions as a Fraction.
	 */
	public static Fraction multiply(List<Fraction> fractions) {
		if (fractions.size() == 0) {
			return new Fraction(0, 1);
		}

		Fraction result = new Fraction(1, 1);
		for (Fraction f : fractions) {
			result = multiply(f, result);
		}
		return result;
	}

	/**
	 * Divides a Fraction by an integer.
	 * 
	 * @param f
	 *            A Fraction
	 * @param n
	 *            An integer
	 * @return f / n as a Fraction
	 */
	public static Fraction divide(Fraction f, int n) {
		int numerator = f.getNumerator();
		int denominator = f.getDenominator();

		Fraction result = new Fraction(numerator, denominator * n);
		MathUtil.simplify(result);
		return result;
	}

	/**
	 * Divides two Fractions.
	 * 
	 * @param f1
	 *            A Fraction
	 * @param f2
	 *            A Fraction
	 * @return f1 / f2 as a Fraction
	 */
	public static Fraction divide(Fraction f1, Fraction f2) {
		int f1n = f1.getNumerator();
		int f1d = f1.getDenominator();
		int f2n = f2.getNumerator();
		int f2d = f2.getDenominator();

		int numerator = f1n * f2d;
		int denominator = f1d * f2n;

		Fraction result = new Fraction(numerator, denominator);
		MathUtil.simplify(result);
		return result;
	}

	/**
	 * Divides multiple Fractions.
	 * 
	 * @param fractions
	 *            An arbitrary amount of Fractions
	 * @return The quotient of all Fractions as a Fraction
	 */
	public static Fraction divide(Fraction... fractions) {
		if (fractions.length == 0) {
			return new Fraction(0, 1);
		}

		Fraction result = new Fraction(1, 1);
		for (Fraction f : fractions) {
			result = divide(f, result);
		}
		return result;
	}

	/**
	 * Divides a List of Fractions.
	 * 
	 * @param fractions
	 *            A List of Fractions.
	 * @return The quotient of all Fractions as a Fraction
	 */
	public static Fraction divide(List<Fraction> fractions) {
		if (fractions.size() == 0) {
			return new Fraction(0, 1);
		}

		Fraction result = new Fraction(1, 1);
		for (Fraction f : fractions) {
			result = divide(f, result);
		}
		return result;
	}

	/**
	 * Raises a Fraction to the nth power.
	 * 
	 * @param f
	 *            A fraction
	 * @param power
	 *            An integer
	 * @return f ** n
	 */
	public static Fraction pow(Fraction f, int power) {
		int numerator = (int) Math.pow(f.getNumerator(), power);
		int denominator = (int) Math.pow(f.getDenominator(), power);

		Fraction result = new Fraction(numerator, denominator);
		MathUtil.simplify(result);
		return result;
	}
}
