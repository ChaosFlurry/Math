package com.math.radical;

/***
 * 
 * @author ChaosFlurry
 *
 */
public class RadicalOperation {
	// TODO batch addition and subtraction

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
	 * Subtracts a Radical from another Radical. Method will only return a result
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
			int radicand = r1.simplify().getRadicand();
			int index = r1.simplify().getIndex();
			return new Radical(coefficient, radicand, index);
		} else {
			throw new RadicalOperationException("Radicals have different indexes");
		}
	}
}
