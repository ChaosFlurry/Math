package fraction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fraction {

	int numerator;
	int denominator;
	
	public Fraction(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
		simplify();
	}
	
	public int getNumerator() {
		return numerator;
	}
	
	public void setNumerator(int numerator) {
		this.numerator = numerator;
		simplify();
	}
	
	public int getDenominator() {
		return denominator;
	}
	
	public void setDenominator(int denominator) {
		this.denominator = denominator;
		simplify();
	}
	
	public double decimalValue() {
		return (double) getNumerator() / getDenominator();
	}
	
	@Override
	public String toString() {
		if (getNumerator() == 0) {
			return "0";
		} else if (getDenominator() == 0) {
			return "undefined";
		} else if (getDenominator() == 1) {
			return Integer.toString(getNumerator());
		} else {
			return getNumerator() + "/" + getDenominator();
		}
	}
	
	public void simplify() {
		int gcd = gcd(getNumerator(), getDenominator());
		setNumerator(getNumerator() / gcd);
		setDenominator(getDenominator() / gcd);
		if (getDenominator() < 0) {
			setNumerator(getNumerator() * -1);
			setDenominator(getDenominator() * -1);
		}
	}
	
	public Fraction reciprocal() {
		return new Fraction(getDenominator(), getNumerator());
	}
	
	public Fraction add(int n) {
		Fraction result = new Fraction(
				getNumerator() + n * getDenominator(),
				getDenominator());
		result.simplify();
		return result;
	}
	
	public Fraction add(Fraction f) {
		int gcd = gcd(this.getDenominator(), f.getDenominator());
		
		int f1n = this.getNumerator();
		int f1d = this.getDenominator();
		int f2n = f.getNumerator();
		int f2d = f.getDenominator();
		
		// 1/4 + 1/6 = 1/4 * 3/3 + 1/6 * 2/2
		// 1/4 + 1/6 = (1 * 3 + 1 * 2) / 6
		// f1 + f2 = f1n * (f2d / gcd) + f2n * (f1d / gcd) / f1d * (f2d / gcd)
		
		Fraction result =  new Fraction(
				f1n * (f2d / gcd) + f2n * (f1d / gcd),
				f1d * (f2d / gcd));
		result.simplify();
		return result;
	}
	
	public Fraction subtract(int n) {
		Fraction result = new Fraction(
				getNumerator() - n * getDenominator(),
				getDenominator());
		result.simplify();
		return result;
	}
	
	public Fraction subtract(Fraction f) {
		int gcd = gcd(this.getDenominator(), f.getDenominator());
		
		int f1n = this.getNumerator();
		int f1d = this.getDenominator();
		int f2n = f.getNumerator();
		int f2d = f.getDenominator();
		
		// 1/4 - 1/6 = 1/4 * 3/3 - 1/6 * 2/2
		// 1/4 - 1/6 = (1 * 3 - 1 * 2) / 6
		// f1 - f2 = (f1n * (f2d / gcd) - f2n * (f1d / gcd)) / (f1d * (f2d / gcd))
		
		Fraction result =  new Fraction(
				f1n * (f2d / gcd) - f2n * (f1d / gcd),
				f1d * (f2d / gcd));
		result.simplify();
		return result;
	}
	
	public Fraction mutliply(int n) {
		Fraction result = new Fraction(
				getNumerator() * n,
				getDenominator());
		result.simplify();
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
		result.simplify();
		return result;
	}
	
	public Fraction divide(int n) {
		Fraction result = new Fraction(
				getNumerator(),
				getDenominator() * n);
		result.simplify();
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
		result.simplify();
		return result;
	}
	
	public Fraction pow(int power) {
		Fraction result = new Fraction(
				(int) Math.pow(getNumerator(), power),
				(int) Math.pow(getDenominator(), power));
		result.simplify();
		return result;
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
}
