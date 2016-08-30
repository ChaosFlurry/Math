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
	//TODO batch addition and subtraction
	
	/**
	 * Adds a number to a Radical. Method will only return a result when the
	 * result of a number added to a Radical can be expressed as an exact
	 * integer.
	 * 
	 * @param r
	 *            A Radical
	 * @param n
	 *            An integer
	 * @return An integer representation of n + r, only if n can be added to r
	 * @throws UndefinedRadicalException
	 *             when r is undefined
	 * @throws RadicalOperationException
	 *             when n cannot be added to r
	 */
	public static int add(Radical r, int n) throws UndefinedRadicalException, RadicalOperationException {
		if (r.isUndefined()) {
			throw new UndefinedRadicalException("Undefined Radical");
		}
		if (r.isANumber()) {
			// A number can be added to a Radical only if the Radical can be
			// simplified to a number itself
			return r.simplify().getCoefficient() + n;
		} else {
			throw new RadicalOperationException("Number cannot be added to Radical");
		}
	}

	/**
	 * Adds a Radical to another Radical. Method will only return a result when
	 * the result of the two Radicals added can be expressed as a single
	 * Radical.
	 * 
	 * @param r1
	 *            A Radical
	 * @param r2
	 *            A Radical
	 * @return A single Radical representation of r1 + r2, only if r2 can be
	 *         added to r1
	 * @throws UndefinedRadicalException
	 *             when r1 or r2 is undefined
	 * @throws RadicalOperationException
	 *             when r1 + r2 cannot be represented as a single Radical
	 */
	public static Radical add(Radical r1, Radical r2) throws UndefinedRadicalException, RadicalOperationException {
		if (r1.isUndefined() || r2.isUndefined()) {
			throw new UndefinedRadicalException("Radical is undefined");
		}
		if (Radical.isAddable(r1, r2)) {
			int coefficient = r1.simplify().getCoefficient() + r2.simplify().getCoefficient();
			int radicand = r1.simplify().getRadicand();
			int index = r1.simplify().getIndex();
			return new Radical(coefficient, radicand, index);
		} else {
			throw new RadicalOperationException("Radicals have different radicands and indexes");
		}
	}

	/**
	 * Adds a number to a Radical. Method will only return a result when the
	 * result of a number added to a Radical can be expressed as an exact
	 * integer.
	 * 
	 * @param r
	 *            A Radical
	 * @param n
	 *            An integer
	 * @return An integer representation of r - n, only if n can be subtracted
	 *         from r
	 * @throws UndefinedRadicalException
	 *             when r is undefined
	 * @throws RadicalOperationException
	 *             when n cannot be subtracted from r
	 */
	public static int subtract(Radical r, int n) throws UndefinedRadicalException, RadicalOperationException {
		if (r.isUndefined()) {
			throw new UndefinedRadicalException("Undefined Radical");
		}
		if (r.isANumber()) {
			// A number can be subtracted from a Radical only if the Radical can
			// be simplified to a number itself
			return r.simplify().getCoefficient() - n;
		} else {
			throw new RadicalOperationException("Number cannot be subtracted to Radical");
		}
	}

	/**
	 * Subtracts a Radical to another Radical. Method will only return a result
	 * when the result of the two Radicals subtracted can be expressed as a
	 * single Radical.
	 * 
	 * @param r1
	 *            A Radical
	 * @param r2
	 *            A Radical
	 * @return A single Radical representation of r1 - r2, only if r2 can be
	 *         subtracted to r1
	 * @throws UndefinedRadicalException
	 *             when r1 or r2 is undefined
	 * @throws RadicalOperationException
	 *             when r1 - r2 cannot be represented as a single Radical
	 */
	public static Radical subtract(Radical r1, Radical r2) throws UndefinedRadicalException, RadicalOperationException {
		if (r1.isUndefined() || r2.isUndefined()) {
			throw new UndefinedRadicalException("Radical is undefined");
		}
		if (Radical.isAddable(r1, r2)) {
			int coefficient = r1.simplify().getCoefficient() - r2.simplify().getCoefficient();
			int radicand = r1.simplify().getRadicand();
			int index = r1.simplify().getIndex();
			return new Radical(coefficient, radicand, index);
		} else {
			throw new RadicalOperationException("Radicals have different radicands and indexes");
		}
	}

	/**
	 * Multiplies a Radical by a number.
	 * 
	 * @param r
	 *            A Radical
	 * @param n
	 *            An integer
	 * @return A Radical representation of r * n
	 * @throws UndefinedRadicalException
	 *             when r is undefined
	 */
	public static Radical multiply(Radical r, int n) throws UndefinedRadicalException {
		if (r.isUndefined()) {
			throw new UndefinedRadicalException("Undefined Radical");
		}
		int coefficient = r.simplify().getCoefficient() * n;
		int radicand = r.simplify().getRadicand();
		int index = r.simplify().getIndex();
		return new Radical(coefficient, radicand, index);
	}

	/**
	 * Multiplies a Radical by a Radical. Method will only return a result when
	 * the result of the Radicals multiplied can be expressed as a single
	 * Radical.
	 * 
	 * @param r1
	 *            A Radical
	 * @param r2
	 *            A Radical
	 * @return A Radical representation of r1 * r2, only if r2 can be multiplied
	 *         by r1
	 * @throws UndefinedRadicalException
	 *             when r1 or r2 is undefined
	 * @throws RadicalOperationException
	 *             when r1 * r2 cannot be expressed as a single Radical
	 */
	public static Radical multiply(Radical r1, Radical r2) throws UndefinedRadicalException, RadicalOperationException {
		if (r1.isUndefined() || r2.isUndefined()) {
			throw new UndefinedRadicalException("Undefined Radical");
		}

		if (r1.isANumber()) {
			return multiply(r2, r1.simplify().getCoefficient());
		}
		if (r2.isANumber()) {
			return multiply(r1, r2.simplify().getCoefficient());
		}

		if (r1.simplify().getIndex() == r2.simplify().getIndex()) {
			int coefficient = r1.simplify().getCoefficient() * r2.simplify().getCoefficient();
			int radicand = r1.simplify().getRadicand() * r2.simplify().getRadicand();
			int index = r1.simplify().getIndex();
			return new Radical(coefficient, radicand, index);
		} else {
			throw new RadicalOperationException("Radicals have different indexes");
		}
	}

	/**
	 * Divides a Radical by an integer. Method will only return a Radical result
	 * when a Radical can be divided into a number without remainder.
	 * Division-by-zero rules apply.
	 * 
	 * @param r
	 *            A Radical
	 * @param n
	 *            An integer
	 * @return A Radical representation of r / n, provided that n can be divided
	 *         into r without remainder
	 * @throws UndefinedRadicalException
	 *             when r is undefined
	 * @throws RadicalOperationException
	 *             when r cannot be divided evenly into n
	 */
	public static Radical divide(Radical r, int n) throws UndefinedRadicalException, RadicalOperationException {
		if (r.isUndefined()) {
			throw new UndefinedRadicalException("Undefined Radical");
		}
		if (r.simplify().getCoefficient() % n != 0) {
			// Radical can be divided only if r divides n without remainder
			throw new RadicalOperationException("Radical cannot be divided by number");
		}
		int coefficient = r.simplify().getCoefficient() / n;
		int radicand = r.simplify().getRadicand();
		int index = r.simplify().getIndex();
		return new Radical(coefficient, radicand, index);
	}

	/**
	 * Divides a Radical by another Radical. Method will only return a Radical
	 * result the first Radical argument can divide the second Radical argument
	 * without remainder. Division-by-zero rules apply.
	 * 
	 * @param r1
	 *            A Radical
	 * @param r2
	 *            A Radical
	 * @return A Radical representation of r1 / r2, provided that r2 can be
	 *         divided into r1 without remainder
	 * @throws UndefinedRadicalException
	 *             when either r1 or r2 is undefined
	 * @throws RadicalOperationException
	 *             when r2 cannot be divided evenly into r1
	 */
	public static Radical divide(Radical r1, Radical r2) throws UndefinedRadicalException, RadicalOperationException {
		if (r1.isUndefined() || r2.isUndefined()) {
			throw new UndefinedRadicalException("Undefined Radical");
		}

		if (r1.isANumber()) {
			return divide(r2, r1.simplify().getCoefficient());
		}
		if (r2.isANumber()) {
			return divide(r1, r2.simplify().getCoefficient());
		}
		if (r1.simplify().getIndex() == r2.simplify().getIndex()) {
			int coefficient = r1.simplify().getCoefficient() / r2.simplify().getCoefficient();
			int radicand = r1.getRadicand();
			int index = r1.getIndex();
			return new Radical(coefficient, radicand, index);
		} else {
			throw new RadicalOperationException("Radicals have different indexes");
		}
	}

	/*
	 * public static List<Radical> add(Radical r, int n) { List<Radical> result
	 * = new ArrayList<Radical>(); int coefficient =
	 * MathUtil.simplify(r).getCoefficient(); int radicand =
	 * MathUtil.simplify(r).getRadicand(); int index =
	 * MathUtil.simplify(r).getIndex();
	 * 
	 * // Special cases: // When Radical is undefined, 0, 1, -1, or when
	 * Radical(x, n) == x // Note: undefined * 0 = undefined
	 * 
	 * if (r.isUndefined()) { // where r is undefined: result = undefined
	 * result.add(new Radical(1, 0, 0)); } else if (radicand == 0 && index > 0)
	 * { // where n is 0: result = n result.add(new Radical(n, 1, 1)); } else if
	 * (radicand == 1 && index != 0) { // where r is 1: result = n + 1
	 * result.add(new Radical(coefficient + n, 1, 1)); } else if (radicand == -1
	 * && index == 1) { // where r is -1: result = -coefficient + n
	 * result.add(new Radical(coefficient * -1 + n, 1, 1)); } else if (radicand
	 * == -1 && index == -1) { // where r is -1: result = -coefficient + n
	 * result.add(new Radical(coefficient * -1 + n, 1, 1)); } else if (radicand
	 * > 0 && radicand != 1 && index == 1) { // where Radical r == int r: result
	 * = radicand * coefficient + n result.add(new Radical(radicand *
	 * coefficient + n, 1, 1)); } else if (radicand < 0 && index == 1) { //
	 * where Radical r == int r: result = radicand * coefficient + n
	 * result.add(new Radical(radicand * coefficient + n, 1, 1)); } else { //
	 * default case: where r cannot be simplified to an integer, n is // not 0:
	 * result = r, n result.add(r); result.add(new Radical(n, 1, 1)); } return
	 * result; }
	 */

	/*
	 * public static List<Radical> add(Radical r1, Radical r2) { List<Radical>
	 * result = new ArrayList<Radical>(); int r1c =
	 * MathUtil.simplify(r1).getCoefficient(); int r1r =
	 * MathUtil.simplify(r1).getRadicand(); int r1i =
	 * MathUtil.simplify(r1).getIndex(); int r2c =
	 * MathUtil.simplify(r2).getCoefficient(); int r2r =
	 * MathUtil.simplify(r2).getRadicand(); int r2i =
	 * MathUtil.simplify(r2).getIndex();
	 * 
	 * /* Special cases: When Radical is undefined or 0 Note: undefined * 0 =
	 * undefined
	 * 
	 * Radicand case table 0 0 0 0 1 r2c 0 -1 -r2c 1 0 r1c 1 1 r1c + r2c 1 -1
	 * r1c - r2c -1 0 -r1c -1 1 -r1c + r2c -1 -1 -r1c - r2c
	 * 
	 * n n n + n n -n n - n -n n -n + n
	 * 
	 * 1 and n are the same case -1 does not exist as it is turned into case 1
	 * 
	 * new cases (0, n, r) 0 0 n n n 0 n r r 0 r r
	 * 
	 * 0 0 n 0 r 0 n n n r r r
	 * 
	 * if (r1.isUndefined() || r2.isUndefined()) { // r is undefined: result =
	 * undefined result.add(new Radical(0, 0, 0)); } else if (r1.isZero()) { if
	 * (r2.isZero()) { result.add(new Radical(0, 0, 1)); } else if
	 * (r2.isANumber()) { result.add(r2); } } else if (r2.isZero()) { if
	 * (r1.isANumber()) { result.add(r1); } } else if (r1r == r2r && r1i == r2i)
	 * { // where both radicals can be added together (radicand and index are //
	 * the same) result.add(new Radical(r1c + r2c, r1r, r2i)); } else {
	 * result.add(r1); result.add(r2); } return result; }
	 */

	public static List<Radical> add(Radical... radicals) {
		List<Radical> result = new ArrayList<Radical>();

		return result;
	}

	public static List<Radical> add(List<Radical> radicals) {
		List<Radical> simplified = new ArrayList<Radical>();
		List<Radical> filtered = new ArrayList<Radical>();
		List<Radical> combined = new ArrayList<Radical>();
		List<Radical> sorted = new ArrayList<Radical>();
		List<Radical> result = new ArrayList<Radical>();

		for (Radical r : radicals) {
			simplified.add(MathUtil.simplify(r));
		}

		// Represents Radicals that can be simplified to a number
		int number = 0;
		// Removes Radicals that have a value of 0, separates Radicals that can
		// be represented as a number
		for (Radical r : simplified) {
			if (r.isUndefined()) {
				result.add(new Radical(0, 0, 0));
				return result;
			} else if (r.isANumber() && r.isZero() == false) {
				number += r.getCoefficient();
			} else {
				filtered.add(r);
			}
		}

		// Combines like Radicals
		for (Radical r : filtered) {
			int radicand = r.getRadicand();
			int index = r.getIndex();
			for (int i = 0; i < filtered.size(); i++) {

			}
		}

		// Sorts Radicals according to index? radicand?
		for (Radical r : combined) {

		}
		if (number != 0) {
			combined.add(new Radical(number, 1, 1));
		}

		result = combined;
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

	/*
	 * public static Radical multiply(Radical r, int n) { // TODO use fractional
	 * exponents Radical result; int coefficient =
	 * MathUtil.simplify(r).getCoefficient(); int radicand =
	 * MathUtil.simplify(r).getRadicand(); int index =
	 * MathUtil.simplify(r).getIndex();
	 * 
	 * if (r.isUndefined()) { result = new Radical(0, 0); } else if (r.isZero())
	 * { result = new Radical(0, 1); } else if (r.isANumber()) { result = new
	 * Radical(coefficient * n, 1, 1); } else { result = new Radical(coefficient
	 * * n, radicand, index); } }
	 * 
	 * public static Radical multiply(Radical r1, Radical r2) {
	 * 
	 * }
	 * 
	 * public static Radical multiply(Radical... radicals) {
	 * 
	 * }
	 * 
	 * public static Radical multiply(List<Radical> radicals) {
	 * 
	 * }
	 * 
	 * public static Radical divide(Radical r, int n) {
	 * 
	 * }
	 * 
	 * public static Radical divide(Radical r1, Radical r2) {
	 * 
	 * }
	 * 
	 * public static Radical divide(Radical... radicals) {
	 * 
	 * }
	 * 
	 * public static Radical divide(List<Radical> radicals) {
	 * 
	 * }
	 */
}
