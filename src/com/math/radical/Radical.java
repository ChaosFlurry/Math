package com.math.radical;

import com.math.helpers.MathUtil;

/***
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
	
	/**
	 * Returns a String representation of the Radical.
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
	 * When the index is negative:
	 * Radical is 1/n ^ index (while coefficient is not 0)
	 * 
	 * Normal cases:
	 * coefficient is 1, radical is not 0, index is non negative and not 0
	 * coefficient is -1, ...
	 * coefficient is > 1, ...
	 * coefficient is < -1, ...
	 * 	
	 * Radical formatting:
	 * coefficient + "*" + radicand + "^" + "(" + "1/" + index + ")"
	 * e.g. Radical(10, 5, 2) will be displayed as 10*5^(1/2)
	 * However, if a Radical can be simplified such that it no longer is a root (i.e. Radical(49, 2)), 
	 * it will be displayed as an integer (e.g. Radical(16, 4) becomes 4).
	 * 
	 * @result The Radical formatted as a String
	 */
	@Override
	public String toString() {
		Radical copyOf = MathUtil.simplify(this);
		int coefficient = copyOf.getCoefficient();
		int radicand = copyOf.getRadicand();
		int index = copyOf.getIndex();
		
		//TODO represent special cases as an enum
		//Special cases:
		//When Radical is undefined, 0, 1, -1, or when Radical(x, n) == x
		//Note: undefined * 0 = undefined
		
		if (isUndefined()) {
			return "Undefined";
		} else if (radicand == 0 && index > 0) {
			return "0";
		} else if (radicand == 1 && index != 0) {
			return Integer.toString(coefficient);
		} else if (radicand == -1 && index == 1) {
			return Integer.toString(coefficient * -1);
		} else if (radicand == -1 && index == -1) {
			return Integer.toString(coefficient * -1);
		} else if (radicand > 0 && radicand != 1 && index == 1) {
			return Integer.toString(radicand * coefficient);
		} else if (radicand < 0 && index == 1) {
			return Integer.toString(radicand * coefficient);
		}
		
		//default case: Radicand is not -1, 1, 0; index is non-negative, index is not 1, 0
		String result;
		result = (index < 0) ? radicand + "^(-1/" + Math.abs(index) + ")" : radicand + "^(1/" + index + ")";
		if (coefficient == -1) {
			result = "-" + result;
		} else if (coefficient != 1) {
			result = coefficient + "*" + result;
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
		Radical r = MathUtil.simplify(this);
		int radicand = r.getRadicand();
		int index = r.getIndex();
		
		if (index == 0) result = true;
		if (index < 0 && radicand == 0) result = true;
		if (radicand < 0 && index % 2 == 0) result = true;
		return result;
	}
	
	public boolean isZero() {
		boolean result = false;
		Radical r = MathUtil.simplify(this);
		int coefficient = r.getCoefficient();
		int radicand = r.getRadicand();
		int index = r.getIndex();
		
		if (radicand == 0 && index > 0) result = true;
		if (r.isUndefined() == false && coefficient == 0) result = true;
		return result;
	}
	
	public boolean isANumber() {
		boolean result = false;
		Radical r = MathUtil.simplify(this);
		int radicand = r.getRadicand();
		int index = r.getIndex();
		
		if (r.isZero()) result = true;
		if (radicand == 1 && index == 1) result = true;
		return result;
	}
}
