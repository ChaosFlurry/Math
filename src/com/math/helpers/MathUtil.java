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
		int gcd = gcd(numerator, denominator);
		numerator = (numerator / gcd);
		denominator = (denominator / gcd);
		if (denominator < 0) {
			numerator *= -1;
			denominator *= -1;
		}
		// f.setNumerator(numerator);
		// f.setDenominator(denominator);
		return new Fraction(numerator, denominator);
	}

	/**
	 * Simplifies a Radical.
	 * 
	 * @param r
	 *            The Radical to be simplified
	 * @return A Radical equivalent to r that cannot be further simplified
	 */
	public static Radical simplify(Radical r) {
		// TODO simplification of higher power roots
		// (4th root of 100 becomes sqrt 10)
		// (6th root of 10000 becomes Radical(100, 3))
		// use fractional powers
		// e.g. Radical(10000, 4)
		// find greatest possible roots of radicand up to index
		// sqrt 10000 is 100 but 4th root of 10000 is 10
		// Radical(100, 2) could still be simplified into Radical(10, 4)

		int coefficient = r.getCoefficient();
		int radicand = r.getRadicand();
		int index = r.getIndex();

		if (radicand == -1 && index % 2 != 0) {
			// any odd root of -1 = -1
			coefficient *= -1;
			radicand = 1;
			index = 1;
		} else if (radicand == 1 && index != 0) {
			// any root of 1 (except 0) = 1
			radicand = 1;
			index = 1;
		} else if (radicand < 0 && index > 0 && index % 2 != 0) {
			// any odd root of -n = -(root n)
			coefficient *= -1;
			radicand *= -1;
		}

		for (int i = index; i > 1; i--) {
			if (hasExactNthRoot(radicand, i)) {
				int nthRoot = nthRoot(radicand, i);
				Fraction fractionalExponent = MathUtil.simplify(new Fraction(i, index));
				radicand = pow(nthRoot, fractionalExponent.getNumerator());
				index = fractionalExponent.getDenominator();
			}
		}

		int nthRoot = MathUtil.nthRoot(radicand, index);
		for (int i = nthRoot; i >= 2; i--) {
			if (radicand % pow(i, index) == 0) {
				radicand /= pow(i, index);
				coefficient *= i;
			}
		}

		// r.setCoefficient(coefficient);
		// r.setRadicand(radicand);
		// r.setIndex(index);
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
		a = Math.abs(a);
		b = Math.abs(b);
		// TODO batch gcd
		// TODO use divisors to find divisors of a and b then find the largest
		// value in the list shared by a and b

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

	public static int pow(int n, int degree) {
		int result;
		if (degree < 0) {
			result = 0;
		} else if (degree == 0) {
			result = 0;
		} else if (degree == 1) {
			result = n;
		} else {
			// 1 * n = n
			result = 1;
			for (int i = 0; i < degree; i++) {
				result *= n;
			}
		}
		return result;
	}

	/**
	 * Finds the (real-valued) nth root of an integer. Returns 0 if n is 0,
	 * degree < 0, or if n < 0 and the degree is even.
	 * 
	 * @param n
	 *            An integer
	 * @return The nth root of n floored to an integer.
	 */
	public static int nthRoot(int n, int degree) {
		if (n == 0 || degree < 0 || (n < 0 && degree % 2 == 0)) {
			return 0;
		}
		if (degree == 1 || n == 1 || n == -1) {
			return n;
		}

		int result = 0;
		if (n > 0) {
			for (int i = 1; i <= n; i++) {
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

	public static boolean hasExactNthRoot(int n, int degree) {
		boolean result = false;
		int root = nthRoot(n, degree);
		int power = pow(root, degree);
		if (power == n) {
			result = true;
		}
		return result;
	}

	/**
	 * Tests if a number is prime.
	 * 
	 * @param n
	 *            An integer
	 * @return true if n is prime, false if n is composite
	 */
	public static boolean isPrime(int n) {
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

		while (n != 1 && (!isPrime(n))) {
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
	 * Returns the sum of n!
	 * 
	 * @param n
	 *            An integer
	 * @return 0 if n is negative, n! otherwise.
	 */
	// http://nntdm.net/papers/nntdm-19/NNTDM-19-2-30_42.pdf
	public static int factorial(int n) {
		if (n < 0)
			return 0;
		return (n == 0 || n == 1) ? 1 : n * factorial(n - 1);
	}
	
	/**
	 * TODO update this javadoc Returns a normalized String of a term of a
	 * polynomial that is ready to be printed. Instead of ...+1x method will
	 * return ... +x -1x will return -x etc.
	 * 
	 * @param n
	 *            An integer
	 * @return A string that does not need to be further formatted to be printed
	 */
	public static String signFormatted(int n) {
		// default case 0
		String result = "";
		if (n == 1)
			result = "+";
		else if (n > 1)
			result = "+" + Integer.toString(n);
		else if (n == -1)
			result = "-";
		else if (n < -1)
			result = Integer.toString(n);
		return result;
	}
}
