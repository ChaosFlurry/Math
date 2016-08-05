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
	
	public String toSimplify() {
		Fraction f = this;
		Fraction f0 = MathUtil.simplify(f);
		if(denominator == 1) return Integer.toString(f0.getNumerator());
		return (numerator == 0) ? "0" : f0.getNumerator() + "/" + f0.getDenominator();
	}
	
	public Fraction reciprocal() {
		return new Fraction(denominator, numerator);
	}
	
	public Fraction add(int n) {
		Fraction result = new Fraction(
				numerator + n * denominator,
				denominator);
		result = MathUtil.simplify(result);
		return result;
	}
	
	public Fraction add(Fraction f) {
		int f1d = this.getDenominator();
		int f2d = f.getDenominator();
		int f1n = this.getNumerator();
		int f2n = f.getNumerator();
		
		int gcd = MathUtil.gcd(f1d, f2d);
		
		// 1/4 + 1/6 = 1/4 * 3/3 + 1/6 * 2/2
		// 1/4 + 1/6 = (1 * 3 + 1 * 2) / 6
		// f1 + f2 = n * (d1 / gcd) + n1 * (d / gcd) / d * (d1 / gcd)
		
		Fraction result =  new Fraction(
				f1n * (f2d / gcd) + f2n * (f1d / gcd),
				f1d * (f2d / gcd));
		result = MathUtil.simplify(result);
		return result;
	}
	
	public Fraction subtract(int n) {
		Fraction result = new Fraction(
				getNumerator() - n * getDenominator(),
				getDenominator());
		result = MathUtil.simplify(result);
		return result;
	}
	
	public Fraction subtract(Fraction f) {
		int f1n = this.getNumerator();
		int f1d = this.getDenominator();
		int f2n = f.getNumerator();
		int f2d = f.getDenominator();
		
		int gcd = MathUtil.gcd(f1d, f2d);
		
		// 1/4 - 1/6 = 1/4 * 3/3 - 1/6 * 2/2
		// 1/4 - 1/6 = (1 * 3 - 1 * 2) / 6
		// f1 - f2 = (f1n * (f2d / gcd) - f2n * (f1d / gcd)) / (f1d * (f2d / gcd))
		
		Fraction result =  new Fraction(
				f1n * (f2d / gcd) - f2n * (f1d / gcd),
				f1d * (f2d / gcd));
		result = MathUtil.simplify(result);
		return result;
	}
	
	public Fraction mutliply(int n) {
		Fraction result = new Fraction(
				getNumerator() * n,
				getDenominator());
		result =  MathUtil.simplify(result);
		return result;
	}
	
	public Fraction multiply(Fraction f) {
		int f1n = this.getNumerator();
		int f1d = this.getDenominator();
		int f2n = f.getNumerator();
		int f2d = f.getDenominator();
		
		// 1/4 * 1/6 = (1 * 1) / (4 * 6)
		// f1 * f2 = f1n * f2n / f1d * f2d
		
		Fraction result = new Fraction(
				f1n * f2n,
				f1d * f2d);
		result = MathUtil.simplify(result);
		return result;
	}
	
	public Fraction divide(int n) {
		Fraction result = new Fraction(
				numerator,
				denominator * n);
		result = MathUtil.simplify(result);
		return result;
	}
	
	public Fraction divide(Fraction f) {
		int f1n = this.getNumerator();
		int f1d = this.getDenominator();
		int f2n = f.getNumerator();
		int f2d = f.getDenominator();
		
		// 1/4 / 1/6 = 1/4 * 6/1
		// 1/4 / 1/6 = (1 * 6) / (4 * 1)
		// f1 / f2 = f1n * f2d / f1d * f2n
		
		Fraction result = new Fraction(
				f1n * f2d,
				f1d * f2n);
		result = MathUtil.simplify(result);
		return result;
	}
	
	public Fraction pow(int power) {
		Fraction result = new Fraction(
				(int) Math.pow(getNumerator(), power),
				(int) Math.pow(getDenominator(), power));
		result = MathUtil.simplify(result);
		return result;
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
