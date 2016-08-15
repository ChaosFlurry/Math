package com.math;

import java.util.ArrayList;
import java.util.List;

import com.math.fraction.Fraction;
import com.math.helpers.MathOperation;
import com.math.helpers.MathUtil;
import com.math.radical.Radical;
import com.math.radical.RadicalOperation;

/***
 * 
 * @author Archiving Do all temporary backend tests here. (This class is just
 *         for the devs to test)
 */
@SuppressWarnings("all")
public class Main {
	// TODO Unit Tests for simplify()
	public static void main(String[] args) {
		Fraction f0 = new Fraction(10, 4);

		// System.out.printf("The Decimal of f0 is: " + f0.decimalValue() +
		// "\nThe Value of f0 is: " + f0.toSimplify());

		/*
		Fraction f1 = new Fraction(98, 4);
		Fraction f2 = new Fraction(26, 2);
		System.out.println("\n98/4 + 26/2 = " + MathUtil.simplify(MathOperation.add(f1, f2)).toString());
		*/
		
		Fraction f1 = new Fraction(20, 2);
		Fraction f2 = new Fraction(65, 3);
		System.out.println("(20/2)/(65/3) = " + MathUtil.simplify(MathOperation.divide(f1, f2)).toString());
			
		
		Fraction f3 = new Fraction(2, 4);
		System.out.println("2/4 --> " + f3);

		Radical r = new Radical(2, 40, 2);
		// System.out.println("Unsimplified: " + r.toString());
		// System.out.println("Simplified: " + MathUtil.simplify(r).toString());

		Double nan = Double.NaN;
		int x = (int) Double.NaN;
		System.out.println(nan);
		System.out.println(x);
		if (x == nan.intValue()) {
			System.out.println("NAN");
		} else {
			System.out.println("Not NAN");
		}
		Fraction batchAddition = MathOperation.add();
		System.out.println(batchAddition);

		int root = MathUtil.nthRoot(-8, 3);
		System.out.println(root);

		Radical simplify = new Radical(2, -64, 3);
		MathUtil.simplify(simplify);
		
		Radical rt2 = new Radical(1, -8, -3);
		rt2 = MathUtil.simplify(rt2);
		
		System.out.println("String: " + rt2.toString());
		System.out.println("coefficient: " + rt2.getCoefficient());
		System.out.println("radicand: " + rt2.getRadicand());
		System.out.println("index: " + rt2.getIndex());
		System.out.println(rt2.decimalValue());
		System.out.println(rt2.isUndefined());
		System.out.println(rt2.isZero());
		System.out.println(rt2.isANumber());
		
		Radical add1 = new Radical(10000, 4);
		Radical add2 = new Radical(100, 2);
		List<Radical> sum = RadicalOperation.add(add1, add2);
		for (Radical radical : sum) {
			System.out.println(radical);
		}
		
		//System.out.println(MathUtil.nthRoot(-8, 3));
		//System.out.println(Math.pow(-8.0, (1.0/3.0)));
		
		/*
		Radical stringTest0 = new Radical(0, 0, 0);
		Radical stringTest1 = new Radical(1, 1, 1);
		Radical stringTest2 = new Radical(2, 2, 2);
		Radical stringTest3 = new Radical(3, 3, 3);
		Radical stringTest4 = new Radical(-8, 3);
		Radical stringTest5 = new Radical(8, -3);
		Radical stringTest6 = new Radical(8, -2);
		Radical stringTest7 = new Radical(7, -3);
		List<Radical> stringTests = new ArrayList<Radical>();
		stringTests.add(stringTest0);
		stringTests.add(stringTest1);
		stringTests.add(stringTest2);
		stringTests.add(stringTest3);
		stringTests.add(stringTest4);
		stringTests.add(stringTest5);
		stringTests.add(stringTest6);
		stringTests.add(stringTest7);
		
		for (Radical stringTest : stringTests) {
			System.out.println(stringTest.toString());
		}
		
		System.out.println("coefficient: " + stringTest5.getCoefficient());
		System.out.println("radicand: " + stringTest5.getRadicand());
		System.out.println("index: " + stringTest5.getIndex());
		*/

		/*
		 * - MathUtil.factorial() returns 0 instead of UNDEFINED - MathOperation
		 * renamed to MathFunctions - moved methods related to manipulation of
		 * Term classes into MathOperation in com.math.helpers (includes
		 * addition, subtraction, multiplication, division, and power methods of
		 * Fractions and Radicals) - Overloaded add, subtract, multiply, divide
		 * methods to allow for easier batch method calls - renamed "degree"
		 * field in Fraction to "index" similarly named methods have also been
		 * renamed (getDegree(), setDegree() are now called getIndex(),
		 * setIndex()) - Added a class called MultiRadical for (future) support
		 * of representation of multiple Radicals as exact values
		 */

		/*
		 * - Adjusted wording in javadocs - Added support for undefined in
		 * Fraction
		 */

		// use 1 form of Radical?
		// Since Radicals with more than 1 term are just multiple Radicals
		// chained together
		// return List<Radical> for add?

		// add average, mean, max, min etc to MathUtil
		// add Fraction comparator

		// simplify Radical methods (just return a List of single Radical
		// elements)

		// removed MultiRadical, RadicalContianer, RadicalObject
		// RadicalOperation changed from Interface to class (will be merged with
		// MathOperations in the future)
		
		//last test commit
	}
}