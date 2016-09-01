package com.math.radical;

import com.math.fraction.Fraction;
import com.math.helpers.MathUtil;

/***
 * 
 * @author ChaosFlurry
 */
public class Radical {
	int coefficient;
	int radicand;
	int index;
	
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
	 * Simplifies a Radical.
	 * 
	 * @param r
	 *            The Radical to be simplified
	 * @return A Radical equivalent to r that cannot be further simplified
	 */
	public Radical simplify() {
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
			if (MathUtil.hasExactNthRoot(radicand, i)) {
				int nthRoot = MathUtil.nthRoot(radicand, i);
				Fraction fractionalExponent = MathUtil.simplify(new Fraction(i, index));
				radicand = MathUtil.pow(nthRoot, fractionalExponent.getNumerator());
				index = fractionalExponent.getDenominator();
			}
		}

		int nthRoot = MathUtil.nthRoot(radicand, index);
		for (int i = nthRoot; i >= 2; i--) {
			if (radicand % MathUtil.pow(i, index) == 0) {
				radicand /= MathUtil.pow(i, index);
				coefficient *= i;
			}
		}
		return new Radical(coefficient, radicand, index);
	}

	/**
	 * Simplifies a Radical.
	 * 
	 * @param r
	 *            A Radical
	 * @return A Radical equivalent to r that cannot be further simplified
	 */
	public static Radical simplify(Radical r) {
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
			if (MathUtil.hasExactNthRoot(radicand, i)) {
				int nthRoot = MathUtil.nthRoot(radicand, i);
				Fraction fractionalExponent = MathUtil.simplify(new Fraction(i, index));
				radicand = MathUtil.pow(nthRoot, fractionalExponent.getNumerator());
				index = fractionalExponent.getDenominator();
			}
		}

		int nthRoot = MathUtil.nthRoot(radicand, index);
		for (int i = nthRoot; i >= 2; i--) {
			if (radicand % MathUtil.pow(i, index) == 0) {
				radicand /= MathUtil.pow(i, index);
				coefficient *= i;
			}
		}
		return new Radical(coefficient, radicand, index);
	}

	/**
	 * Returns a String representation of the Radical. Radical is undefined
	 * when: 0th root even roots of negative numbers
	 * 
	 * Radical is 0 when: any root of 0 while Radical is not undefined
	 * coefficient is 0 and Radical is not undefined
	 * 
	 * Radical is 1 when: coefficient is 1 and radicand is 1
	 * 
	 * Radical is n when: index is 1 When the index is negative: Radical is 1/n
	 * ^ index (while coefficient is not 0)
	 * 
	 * Normal cases: coefficient is 1, radical is not 0, index is non negative
	 * and not 0 coefficient is -1, ... coefficient is > 1, ... coefficient is <
	 * -1, ...
	 * 
	 * Radical formatting: coefficient + "*" + radicand + "^" + "(" + "1/" +
	 * index + ")" e.g. Radical(10, 5, 2) will be displayed as 10*5^(1/2)
	 * However, if a Radical can be simplified such that it no longer is a root
	 * (i.e. Radical(49, 2)), it will be displayed as an integer (e.g.
	 * Radical(16, 4) becomes 4).
	 * 
	 * @result The Radical formatted as a String
	 */
	@Override
	public String toString() {
		Radical copyOf = Radical.simplify(this);
		int coefficient = copyOf.getCoefficient();
		int radicand = copyOf.getRadicand();
		int index = copyOf.getIndex();

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

		// default case: Radicand is not -1, 1, 0; index is non-negative, index
		// is not 1, 0
		String result;
		result = (index < 0) ? radicand + "^(-1/" + Math.abs(index) + ")" : radicand + "^(1/" + index + ")";
		if (coefficient == -1) {
			result = "-" + result;
		}  else if (coefficient == 0) {
			result = "0";
		} else if (coefficient != 1) {
			result = coefficient + "*" + result;
		}
		return result;
	}

	public double decimalValue() {
		return coefficient * Math.pow(radicand, 1.0 / index);
	}

	public boolean isUndefined() {
		boolean result = false;
		Radical r = Radical.simplify(this);
		int radicand = r.getRadicand();
		int index = r.getIndex();

		if (index == 0)
			result = true;
		if (index < 0 && radicand == 0)
			result = true;
		if (radicand < 0 && index % 2 == 0)
			result = true;
		return result;
	}

	public boolean isZero() {
		boolean result = false;
		Radical r = Radical.simplify(this);
		int coefficient = r.getCoefficient();
		int radicand = r.getRadicand();
		int index = r.getIndex();

		if (radicand == 0 && index > 0)
			result = true;
		if (r.isUndefined() == false && coefficient == 0)
			result = true;
		return result;
	}

	public boolean isANumber() {
		boolean result = false;
		Radical r = Radical.simplify(this);
		int radicand = r.getRadicand();
		int index = r.getIndex();

		if (r.isZero())
			result = true;
		if (radicand == 1 && index == 1)
			result = true;
		return result;
	}

	/**
	 * Checks if two Radicals can be added together and simplified into a single
	 * Radical. Radicals must have the same radicand and index to be able to be
	 * added together.
	 * 
	 * @param r1
	 *            A Radical
	 * @param r2
	 *            A Radical
	 * @return true if r1 and r2 can be added together, false otherwise
	 */
	public static boolean isAddable(Radical r1, Radical r2) {
		boolean result = false;
		int r1r = r1.simplify().getRadicand();
		int r1i = r1.simplify().getIndex();
		int r2r = r2.simplify().getRadicand();
		int r2i = r2.simplify().getIndex();
		
		if (r1r == r2r && r1i == r2i) {
			//if the radicands and indexes are equivalent
			result = true;
		}
		return result;
	}
}
