package com.math.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.math.fraction.Fraction;
import com.math.radical.Radical;

/***
 * 
 * @author Archiving
 * 
 *         Utility class containing methods to manipulate Fractions and Radicals
 */
public class MathUtil {

	/**
	 * Returns a fraction in its lowest terms. A fraction cannot be further
	 * simplified when its numerator or denominator is prime or 1. If the
	 * denominator of a fraction is negative, it is changed to positive (by
	 * multiplying both the numerator and denominator by -1).
	 * 
	 * @param f
	 *            The Fraction to be simplified
	 * @return A Fraction in lowest terms equivalent to f
	 */
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
		//f.setNumerator(numerator);
		//f.setDenominator(denominator);
		return new Fraction(numerator, denominator);
	}
	
	/**
	 * 
	 * @param r The Radical to be simplified
	 * @return A Radical equivalent to r that cannot be further simplified
	 */
	public static Radical simplify(Radical r) {
		int coefficient = r.getCoefficient();
		int radicand = r.getRadicand();
		int index = r.getIndex();

		if (radicand < 0 && index % 2 != 0) {
			coefficient *= -1;
			radicand *= -1;
		}
		
		int nthRoot = MathUtil.nthRoot(radicand, index);
		for (int i = nthRoot; i >= 2; i--) {
			if (radicand % Math.pow(i, index) == 0) {
				radicand = (int) (radicand / Math.pow(i, index));
				coefficient = coefficient * i;
			}
		}
		//r.setCoefficient(coefficient);
		//r.setRadicand(radicand);
		// index remains the same
		return new Radical(coefficient, radicand, index);
	}

	/**
	 * Returns the reciprocal of a Fraction f (1/f), which can be represented as
	 * d/n where n is the numerator and d is the denominator.
	 * 
	 * @param f
	 *            A Fraction
	 * @return The reciprocal of f as a Fraction
	 */
	public static Fraction reciprocal(Fraction f) {
		int numerator = f.getDenominator();
		int denominator = f.getNumerator();
		return new Fraction(numerator, denominator);
	}

	/**
	 * Returns the absolute value of a fraction. The absolute value cannot be
	 * less than 0. If the denominator of a fraction is negative, it is changed
	 * to positive (by multiplying both the numerator and denominator by -1).
	 * 
	 * @param f
	 *            A Fraction
	 * @return A Fraction which has the absolute value of f
	 */
	
	public static Fraction abs(Fraction f) {
		/*
		 * The numerator and denominator are changed to a positive value (if
		 * already not positive), since the absolute value cannot equate to a
		 * value less than 0.
		 */
		int numerator = (f.getNumerator() < 0) ? f.getNumerator() * -1 : f.getNumerator();
		int denominator = (f.getDenominator() < 0) ? f.getDenominator() * -1 : f.getDenominator();
		return new Fraction(numerator, denominator);
	}

	/**
	 * Uses the Euclidean algorithm to determine the greatest common denominator
	 * of two numbers.
	 * 
	 * @param a
	 *            An integer
	 * @param b
	 *            Another integer
	 * @return The greatest common denominator of a and b
	 */
	public static int gcd(int a, int b) {
		// TODO: Refactor as a reciprocal function

		// Euclidean algorithm
		// gcd(a, b) == gcd(a % b, b)
		// when a or b == 0, the gcd is the non-zero value
		// (which is also the sum of a and b)

		int gcd;
		if (a == b) {
			gcd = a;
		} else {
			while (a != 0 && b != 0) {
				// temporary variable
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
	
	/**
	 * Finds the nth root of an integer. Returns 0 if n is 0 or the degree is even.
	 * 
	 * @param n An integer
	 * @return The nth root of n floored to an integer.
	 */
	public static int nthRoot(int n, int degree) {
		if (n < 0 && degree % 2 == 0) {
			return 0;
		}
		
		int result = 0;
		if (n > 0) {
			for (int i = 1; i < n; i++) {
				if (Math.pow(i, degree) > n) {
					result = i - 1;
					break;
				}
			}
		} else if (n < 0) {
			for (int i = -1; i > n; i--) {
				if (Math.pow(i, degree) < n) {
					result = i + 1;
					break;
				}
			}
		}
		return result;
	}

	/**
	 * Tests if a number is prime using the Sieve of Eratosthenes
	 * 
	 * @param n
	 *            An integer
	 * @return true if n is prime, false if n is composite
	 */
	public static boolean isPrime(int n) {
		// Sieve of Eratosthenes
		//

		int sqrtN = (int) Math.sqrt(n);
		boolean isPrime;
		if (n == 0 || n == 1) {
			isPrime = false;
		} else if (n < 0) {
			isPrime = false;
		} else if (n % 2 == 0) {
			isPrime = false;
		} else {
			isPrime = true;
			for (int i = 3; i <= sqrtN; i += 2) {
				if (n % i == 0) {
					isPrime = false;
					break;
				}
			}
		}
		return isPrime;
	}

	/**
	 * Returns the divisors of a number.
	 * 
	 * @param n
	 *            An integer
	 * @return A List of all divisors of n
	 */
	public static List<Integer> divisors(int n) {
		n = Math.abs(n);
		List<Integer> divisors = new ArrayList<Integer>();

		if (n == 0 || n == 1) {
			divisors.add(n);
		} else {
			for (int i = 1; i <= Math.sqrt(n); i++) {
				if (n % i == 0) {
					divisors.add(i);
					// avoids duplicate divisors of perfect squares
					if (i != n / i) {
						divisors.add(n / i);
					}
				}
			}
			Collections.sort(divisors);
		}
		return divisors;
	}

	/**
	 * Returns the prime factors of a number.
	 * 
	 * @param n
	 *            An integer
	 * @return A List of all prime factors of n
	 */
	public static List<Integer> primeFactors(int n) {
		n = Math.abs(n);
		// 0 and 1 have no prime factors
		if (n == 0 || n == 1) {
			return new ArrayList<Integer>();
		}

		List<Integer> primeFactors = new ArrayList<Integer>();
		List<Integer> primesUpToSqrtN = new ArrayList<Integer>();

		int sqrtN = (int) Math.sqrt(n);
		primesUpToSqrtN.add(2);
		for (int i = 3; i <= sqrtN; i += 2) {
			if (isPrime(i)) {
				primesUpToSqrtN.add(i);
			}
		}

		// check if divisible by factors in primesUpToSqrtN
		// upon n being divisible, n = n / divisor
		// repeat until n is prime

		while (n != 1 && (isPrime(n) == false)) {
			for (int p : primesUpToSqrtN) {
				if (n % p == 0) {
					primeFactors.add(p);
					n = n / p;
				}
			}
		}

		if (isPrime(n))
			primeFactors.add(n);
		Collections.sort(primeFactors);
		return primeFactors;
	}
	
	/***
	 * Uses Factorials
	 * 
	 * @param n
	 *            An integer
	 * @return 0 if n is negative, n! elsewise.
	 */
	// http://nntdm.net/papers/nntdm-19/NNTDM-19-2-30_42.pdf
	public static int factorial(int n) {
		if (n < 0)
			return 0;
		return (n == 0 || n == 1) ? 1 : n * factorial(n - 1);
	}

	/*
	 * public void simplify() { System.out.println("Radicand: " +
	 * getRadicand()); if (!(MathUtil.isPrime(getRadicand()))) { if (getDegree()
	 * == 0) { undefined = true; } else { //if n % nthRoot == 0; then
	 * coefficient *= i, radicand /= i //iterate from nthRoot to 2 (lowest
	 * possible prime factor) int nthRoot = (int) Math.pow(getRadicand(), (1.0 /
	 * getDegree())); for (int i = nthRoot; i >= 2; i--) { if (getRadicand() % i
	 * == 0) { setRadicand(getRadicand() / i); setCoefficient(getCoefficient() *
	 * i); } } } }
	 * 
	 * }
	 */
}
