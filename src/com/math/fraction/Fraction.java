package com.math.fraction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
		simplify();
	}
	
	@Override
	public String toString() {
		/*
		if (numerator == 0) {
			return "0";
		} else if (denominator == 0) {
			return "undefined";
		} else if (denominator == 1) {
			return Integer.toString(numerator);
		} else {
			return numerator + "/" + denominator;
		}
		*/
		
		if(denominator == 1) return Integer.toString(numerator);
		return (numerator == 0) ? "0" : numerator + "/" + denominator;
	}
	
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
		
		setNumerator(numerator);
		setDenominator(denominator);
		return new Fraction(numerator, denominator);
	}
	
	public String toSimplify() {
		Fraction f = this;
		Fraction f0 = f.simplify();
		if (denominator == 1) return Integer.toString(f0.getNumerator());
		return (numerator == 0) ? "0" : f0.getNumerator() 
				+ "/" + f0.getDenominator();
	}
	
	//TODO WHY??????????????????????????????????? (prob move to MathUtil.java, cause it doesnt seem to be used here)
	/**
	 * Tests if a number is prime using the Sieve of Eratosthenes
	 * @param n	an integer
	 * @return	true if n is prime, false if n is composite
	 */
	public static boolean isPrime(int n) {
		//Sieve of Eratosthenes
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
	
	//TODO WHY??????????????????????????????????? (prob move to MathUtil.java, cause it doesnt seem to be used here)
	public static List<Integer> divisors(int n) {
		n = Math.abs(n);
		List<Integer> divisors = new ArrayList<Integer>();
		
		if (n == 0 || n == 1) {
			divisors.add(n);
		} else {
			for (int i = 1; i <= Math.sqrt(n); i++) {
				if (n % i == 0) {
					divisors.add(i);
					//avoids duplicate divisors of perfect squares
					if (i != n / i) {
						divisors.add(n / i);
					}
				}
			}
			Collections.sort(divisors);
		}
		return divisors;
	}
	
	//TODO WHY??????????????????????????????????? (prob move to MathUtil.java, cause it doesnt seem to be used here)
	public static List<Integer> primeFactors(int n) {
		n = Math.abs(n);
		//0 and 1 have no prime factors
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
		
		//check if divisible by factors in primesUpToSqrtN
		//upon n being divisible, n = n / divisor
		//repeat until n is prime
		
		while(n != 1 && (isPrime(n) == false)) {
			for (int p : primesUpToSqrtN) {
				if (n % p == 0) {
					primeFactors.add(p);
					n = n / p;
				}
			}
		}
		
		if (isPrime(n)) primeFactors.add(n);
		Collections.sort(primeFactors);
		return primeFactors;
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
	
	public double decimalValue() {
		return (double) numerator / denominator;
	}
}
