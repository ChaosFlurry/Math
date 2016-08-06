package com.math.radical;

import com.math.fraction.Fraction;
import com.math.helpers.MathUtil;

/****
 * 
 * @author ChaosFlurry
 * TODO important to implement simplify() in MathUtil.
 */

public class Radical {
	int coefficient = 1;
	int radicand;
	int degree;
	boolean undefined;
	
	//let a radical be more than 1 radicand
	//i.e. Radical r can be represented as multiple terms as e.g. 2sqrt5 + sqrt3
	//let there be a list of Integers denoting coefficients, radicands, and degrees for each term
	
	//TODO fractional coefficients/radicands?
	public Radical(int radicand, int degree) {
		//check if radicand is negative
		//check if degree is 0
		this.radicand = radicand;
		this.degree = degree;
		//simplify();
	}
	
	public Radical(int coefficient, int radicand, int degree) {
		this.coefficient = coefficient;
		this.radicand = radicand;
		this.degree = degree;
		//simplify();
	}
	
	public Radical(int radicand, Fraction degree) {
		this.radicand = (int)(Math.pow(radicand, degree.getNumerator()));
		this.degree = degree.getDenominator();
		//simplify();
	}
	
	public Radical(int coefficient, int radicand, Fraction degree) {
		this.coefficient = coefficient;
		this.radicand = (int)(Math.pow(radicand, degree.getNumerator()));
		this.degree = degree.getDenominator();
		//simplify();
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

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}
	
	public void setDegree(Fraction degree) {
		this.radicand = (int)(Math.pow(getRadicand(), degree.getNumerator()));
		this.degree = degree.getDenominator();
	}
	
	public boolean isUndefined() {
		return undefined;
	}
	
	/* TODO i guess make it shorter? looks weird */
	@Override
	public String toString() {
		/*
		//Undefined, return
		if (isUndefined()) {
			return "undefined";
		//if the radicand or coefficient == 0, return 0
		} else if (getRadicand() == 0) {
			return "0";
		} else if (getCoefficient() == 0) {
			return "0";
		//if the radicand == 1, return the coefficient.
		//if the coefficient == 1, return the radicand.
		} else if (getRadicand() == 1) {
			return Integer.toString(getCoefficient());
		} else if (getCoefficient() == 1) {
			//the degree is 1 and the coefficient == 1, return the radicand.
			if (getDegree() == 1) {
				return Integer.toString(getRadicand());
			//if the degree is not 1, but the coefficient is 1, return the radicand and degree.
			} else {
				return getRadicand() + "^(1/" + getDegree() + ")";
			}
		}
		//if all above cases are not met, return the coefficient, radicand and degree.
		else {
			return getCoefficient() + "*" + getRadicand() + "^(1/" + getDegree() + ")";
		}
		*/
		
		//undefined, return undefined
		if(isUndefined()) return "undefined";
		if(radicand == 1) return Integer.toString(coefficient);
		if(coefficient == 1) {
			if(degree == 1) return Integer.toString(radicand);
			else return radicand + "^(1/" + degree + ")";
		}
		
		return (coefficient == 0 || radicand == 0) ? "0" : coefficient + "*" + radicand + "^(1/" + degree + ")";
	}
	
	public void setUndefined(boolean undefined) {
		this.undefined = undefined;
	}
	
	
	public void simplify() {
		//System.out.println("Radicand: " + getRadicand());
		if (!(MathUtil.isPrime(getRadicand()))) {
			if (getDegree() == 0) {
				undefined = true;
			} else {
				//if n % nthRoot == 0; then coefficient *= i, radicand /= i
				//iterate from nthRoot to 2 (lowest possible prime factor)
				int nthRoot = (int) Math.pow(getRadicand(), (1.0 / getDegree()));
				for (int i = nthRoot; i >= 2; i--) {
					if (getRadicand() % i == 0) {
						setRadicand(getRadicand() / i);
						setCoefficient(getCoefficient() * i);
					}
				}
			}
		}
	}
	
}
	
