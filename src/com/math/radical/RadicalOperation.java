package com.math.radical;

import java.util.ArrayList;
import java.util.List;

import com.math.helpers.MathUtil;

/***
 * 
 * @author ChaosFlurry
 *
 */
public class RadicalOperation {
	// TODO Move to MathOperation
	// TODO fix everything
	
	public static List<Radical> add(Radical r, int n) {
		List<Radical> result = new ArrayList<Radical>();
		int coefficient = MathUtil.simplify(r).getCoefficient();
		int radicand = MathUtil.simplify(r).getRadicand();
		int index = MathUtil.simplify(r).getIndex();
		
		//Special cases:
		//When Radical is undefined, 0, 1, -1, or when Radical(x, n) == x
		//Note: undefined * 0 = undefined
		
		if (r.isUndefined()) {
			//where r is undefined: result = undefined
			result.add(new Radical(0, 0, 0));
		} else if (radicand == 0 && index > 0) {
			//where n is 0: result = n
			result.add(new Radical(n, 1, 1));
		} else if (radicand == 1 && index != 0) {
			//where r is 1: result = n + 1
			result.add(new Radical(coefficient + n, 1, 1));
		} else if (radicand == -1 && index == 1) {
			//where r is -1: result = -coefficient + n
			result.add(new Radical(coefficient * -1 + n, 1, 1));
		} else if (radicand == -1 && index == -1) {
			//where r is -1: result = -coefficient + n
			result.add(new Radical(coefficient * -1 + n, 1, 1));
		} else if (radicand > 0 && radicand != 1 && index == 1) {
			//where Radical r == int r: result = radicand * coefficient + n
			result.add(new Radical(radicand * coefficient + n, 1, 1));
		} else if (radicand < 0 && index == 1) {
			//where Radical r == int r: result = radicand * coefficient + n
			result.add(new Radical(radicand * coefficient + n, 1, 1));
		} else {
			//default case: where r cannot be simplified to an integer, n is not 0: result = r, n
			result.add(r);
			result.add(new Radical(n, 1, 1));
		}
		return result;
	}

	public static List<Radical> add(Radical r1, Radical r2) {
		List<Radical> result = new ArrayList<Radical>();
		int r1c = MathUtil.simplify(r1).getCoefficient();
		int r1r = MathUtil.simplify(r1).getRadicand();
		int r1i = MathUtil.simplify(r1).getIndex();
		int r2c = MathUtil.simplify(r2).getCoefficient();
		int r2r = MathUtil.simplify(r2).getRadicand();
		int r2i = MathUtil.simplify(r2).getIndex();
		
		/*
		 * Special cases:
		 * When Radical is undefined or 0
		 * Note: undefined * 0 = undefined
		 * 
		 * Radicand case table
		 * 0 0		0
		 * 0 1		r2c
		 * 0 -1		-r2c
		 * 1 0		r1c
		 * 1 1		r1c + r2c
		 * 1 -1		r1c - r2c
		 * -1 0		-r1c
		 * -1 1		-r1c + r2c
		 * -1 -1	-r1c - r2c
		 * 
		 * n n		n + n
		 * n -n		n - n
		 * -n n		-n + n
		 * 
		 * 1 and n are the same case
		 * -1 does not exist as it is turned into case 1
		 * 
		 * new cases (0, n, r)
		 * 0 0
		 * n n
		 * n 0
		 * n r
		 * r 0
		 * r r
		 * 
		 * 0 0
		 * n 0
		 * r 0
		 * n n
		 * n r
		 * r r
		 */
		
		
		if (r1.isUndefined() || r2.isUndefined()) {
			//r is undefined: result = undefined
			result.add(new Radical(0, 0, 0));
		} else if (r1.isZero()) {
			if (r2.isZero()) {
				result.add(new Radical(0, 0, 1));
			} else if (r2.isANumber()) {
				result.add(r2);
			}
		} else if (r2.isZero()) {
			if (r1.isANumber()) {
				result.add(r1);
			}
		} else if (r1r == r2r && r1i == r2i) {
			//where both radicals can be added together (radicand and index are the same)
			result.add(new Radical(r1c + r2c, r1r, r2i));
		} else {
			result.add(r1);
			result.add(r2);
		}
		return result;
	}

	public static List<Radical> add(Radical... radicals) {
		List<Radical> result = new ArrayList<Radical>();
		
		return result;
	}

	public static List<Radical> add(List<Radical> radicals) {
		List<Radical> result = new ArrayList<Radical>();

		return result;
	}

	public static List<Radical> subtract(Radical r, int n) {
		List<Radical> result = new ArrayList<Radical>();

		return result;
	}

	public static List<Radical> subtract(int n, Radical r) {
		List<Radical> result = new ArrayList<Radical>();

		return result;
	}

	public static List<Radical> subtract(Radical... radicals) {
		List<Radical> result = new ArrayList<Radical>();

		return result;
	}

	public static List<Radical> subtract(List<Radical> radicals) {
		List<Radical> result = new ArrayList<Radical>();

		return result;
	}
	
	public static Radical multiply(Radical r, int n) {
		
	}

	public static Radical multiply(Radical r1, Radical r2) {

	}

	public static Radical multiply(Radical... radicals) {

	}

	public static Radical multiply(List<Radical> radicals) {

	}

	public static Radical divide(Radical r, int n) {

	}

	public static Radical divide(Radical r1, Radical r2) {

	}

	public static Radical divide(Radical... radicals) {

	}

	public static Radical divide(List<Radical> radicals) {

	}
}
