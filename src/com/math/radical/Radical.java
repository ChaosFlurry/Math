package com.math.radical;

import com.math.fraction.Fraction;

/****
 * 
 * @author ChaosFlurry
 */

public class Radical {
	int coefficient;
	int radicand;
	int index;
	//boolean undefined;

	// let a radical be more than 1 radicand
	// i.e. Radical r can be represented as multiple terms as e.g. 2sqrt5 +
	// sqrt3
	// let there be a list of Integers denoting coefficients, radicands, and
	// degrees for each term

	// TODO fractional coefficients/radicands?
	public Radical(int radicand, int index) {
		// check if radicand is negative
		// check if index is 0
		this.coefficient = 1;
		this.radicand = radicand;
		this.index = index;

	}

	public Radical(int coefficient, int radicand, int index) {
		this.coefficient = coefficient;
		this.radicand = radicand;
		this.index = index;
	}

	public Radical(int radicand, Fraction index) {
		this.coefficient = 1;
		this.radicand = (int) (Math.pow(radicand, index.getNumerator()));
		this.index = index.getDenominator();
	}

	public Radical(int coefficient, int radicand, Fraction index) {
		this.coefficient = coefficient;
		this.radicand = (int) (Math.pow(radicand, index.getNumerator()));
		this.index = index.getDenominator();
	}

	public int getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}

	public int getRadicand() {
		return radicand;
	}

	public void setRadicand(int radicand) {
		this.radicand = radicand;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	/* TODO i guess make it shorter? looks weird */
	@Override
	public String toString() {
		/*
		 * //Undefined, return if (isUndefined()) { return "undefined"; //if the
		 * radicand or coefficient == 0, return 0 } else if (getRadicand() == 0)
		 * { return "0"; } else if (getCoefficient() == 0) { return "0"; //if
		 * the radicand == 1, return the coefficient. //if the coefficient == 1,
		 * return the radicand. } else if (getRadicand() == 1) { return
		 * Integer.toString(getCoefficient()); } else if (getCoefficient() == 1)
		 * { //the index is 1 and the coefficient == 1, return the radicand. if
		 * (getDegree() == 1) { return Integer.toString(getRadicand()); //if the
		 * index is not 1, but the coefficient is 1, return the radicand and
		 * index. } else { return getRadicand() + "^(1/" + getDegree() + ")"; }
		 * } //if all above cases are not met, return the coefficient, radicand
		 * and index. else { return getCoefficient() + "*" + getRadicand() +
		 * "^(1/" + getDegree() + ")"; }
		 */

		// undefined, return undefined
		//if (isUndefined())
			//return "undefined";
		
		/*
		if (radicand == 1)
			return Integer.toString(coefficient);
		if (coefficient == 1) {
			if (index == 1)
				return Integer.toString(radicand);
			else
				return radicand + "^(1/" + index + ")";
		}

		return (coefficient == 0 || radicand == 0) ? "0" : coefficient + "*" + radicand + "^(1/" + index + ")";
		*/
		if (radicand == 0) {
			return "0";
		}
		if (radicand == 1) {
			return Integer.toString(coefficient);
		}
		
	}
	
	public double decimalValue() {
		return coefficient * Math.pow(radicand, 1.0 / index);
	}

	/*public void simplify() {
		// System.out.println("Radicand: " + getRadicand());
		if (!(MathUtil.isPrime(getRadicand()))) {
			if (getIndex() == 0) {
				//undefined = true;
			} else {
				// if n % nthRoot == 0; then coefficient *= i, radicand /= i
				// iterate from nthRoot to 2 (lowest possible prime factor)
				int nthRoot = (int) Math.pow(getRadicand(), (1.0 / getIndex()));
				for (int i = nthRoot; i >= 2; i--) {
					if (getRadicand() % i == 0) {
						setRadicand(getRadicand() / i);
						setCoefficient(getCoefficient() * i);
					}
				}
			}
		}*/
}
