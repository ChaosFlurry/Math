package com.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.math.fraction.Fraction;
import com.math.fraction.FractionFormatException;
import com.math.helpers.MathOperation;
import com.math.helpers.MathUtil;
import com.math.matrix.Matrix;
import com.math.polynomial.LinearEquation;
import com.math.polynomial.Point;
import com.math.polynomial.PolySolver;
import com.math.polynomial.Quadratic;
import com.math.polynomial.QuadraticFormatException;
import com.math.polynomial.UnfactorableQuadraticException;
import com.math.radical.Radical;
import com.math.radical.RadicalOperation;
import com.math.radical.RadicalOperationException;
import com.math.radical.UndefinedRadicalException;

/***
 * 
 * @author Archiving Do all temporary backend tests here. (This class is just
 *         for the devs to test)
 */
@SuppressWarnings("all")
public class Main {
	public static void main(String[] args) {
		
		
		/*
		Fraction[] a = {Fraction.valueOf(2), Fraction.valueOf(3), Fraction.valueOf(4), Fraction.valueOf(5)};
		Fraction[] b = {Fraction.valueOf(1), Fraction.valueOf(2), Fraction.valueOf(3), Fraction.valueOf(8)};
		Fraction[] c = {Fraction.valueOf(6), Fraction.valueOf(5), Fraction.valueOf(4), Fraction.valueOf(1)};
		Fraction[] d = {Fraction.valueOf(1), Fraction.valueOf(3), Fraction.valueOf(2), Fraction.valueOf(3)};
		
		Fraction[][] x = {a, b, c, d};
		Matrix m = new Matrix(4, 4, x);
		Fraction det = m.determinant();
		System.out.println(det);
		*/
		
		//TODO List:
		//SYSTEMS***
		//conversions?
		//Triangle solver
		//sin/cos law
		
		//test parseFraction()
		
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
	}
}