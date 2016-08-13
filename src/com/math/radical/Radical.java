package com.math.radical;

import com.math.fraction.Fraction;
import com.math.helpers.MathUtil;

/****
 * 
 * @author ChaosFlurry
 */

public class Radical {
	int coefficient;
	int radicand;
	int index;

	// TODO fractional coefficients/radicands?
	public Radical(int radicand, int index) {
		this.coefficient = 1;
		this.radicand = radicand;
		this.index = index;
	}

	public Radical(int coefficient, int radicand, int index) {
		this.coefficient = coefficient;
		this.radicand = radicand;
		this.index = index;
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
		
		/*
		 * Radical is undefined when:
		 * 0th root
		 * even roots of negative numbers
		 * 
		 * Radical is 0 when:
		 * any root of 0 while Radical is not undefined
		 * coefficient is 0 and Radical is not undefined
		 * 
		 * Radical is 1 when:
		 * coefficient is 1 and radicand is 1
		 * 
		 * Radical is n when:
		 * index is 1
		 * 
		 * When the index is negative:
		 * Radical is 1/n ^ index (while coefficient is not 0)
		 * 
		 * normal cases:
		 * coefficient is 1, radical is not 0, index is non negative and not 0
		 * coefficient is -1, ...
		 * coefficient is > 1, ...
		 * coefficient is < -1, ...
		 */
		
		//Radical formatting:
		//coefficient + "*" + radicand + "^" + "(" + "1/" + index
		//e.g. Radical(10, 5, 2) will be displayed as 10*5^(1/2)
		//However, if a Radical can be simplified such that it no longer is a root (i.e. Radical(49, 2)), 
		//it will be displayed as an integer (e.g. Radical(16, 4) becomes 4)
		
		Radical copyOf = MathUtil.simplify(this);
		int coefficient = copyOf.getCoefficient();
		int radicand = copyOf.getRadicand();
		int index = copyOf.getIndex();
		
		//Special cases:
		//Radical is undefined or Radical == 0:
		//Note: undefined * 0 = undefined
		
		if (isUndefined() == true) {
			return "Undefined";
		}
		if (radicand == 0 && index > 0) {
			return "0";
		}
		if (radicand == 1 && index != 0) {
			return Integer.toString(coefficient);
		}
		if (radicand == -1 && index == 1) {
			return Integer.toString(coefficient * -1);
		}
		if (radicand == -1 && index == -1) {
			return Integer.toString(coefficient * -1);
		}
		if (radicand > 0 && radicand != 1 && index == 1) {
			return Integer.toString(radicand * coefficient);
		}
		if (radicand < 0 && index == 1) {
			return Integer.toString(radicand * coefficient);
		}
		
		//default case: radicand is not -1, 1, 0; index is non-negative, index is not 1, 0
		String result;
		result = (index < 0) ? radicand + "^(-1/" + Math.abs(index) + ")" : radicand + "^" + index;
		if (coefficient == -1) {
			result += "-";
		} else if (coefficient == 0) {
			result = "0";
		}
		return result;
	}
	
	public double decimalValue() {
		return coefficient * Math.pow(radicand, 1.0 / index);
	}
	
	public boolean isUndefined() {
		boolean result = false;
		if (index == 0) result = true;
		if (index < 0 && radicand == 0) result = true;
		if (radicand < 0 && index % 2 == 0) result = true;
		return result;
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
