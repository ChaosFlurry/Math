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
	// TODO Unit Tests for simplify()
	// TODO scientific notation?
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
		
		String fractionParseTest = "2/-4";
		try {
			Fraction parseTest = Fraction.parseFraction(fractionParseTest);
			System.out.println("parsed: " + parseTest.toString());
		} catch (FractionFormatException e) {
			System.out.println("Not a fraction.");
		}
		
		System.out.println("---");
		List<String> solutions;
		List<Double> decimalSolutions;
		PolySolver solver = new PolySolver(4, -40, 84);
		solutions = solver.getStringSolutions();
		decimalSolutions = solver.getDecimalSolutions();
		for (String s : solutions) {
			System.out.println(s);
		}
		for (Double d : decimalSolutions) {
			System.out.println(d);
		}
		
		Radical exTest1 = new Radical(1, 1, 1);
		Radical exTest2 = new Radical(0, 2, 2);
		try {
			Radical exResult = RadicalOperation.multiply(exTest1, exTest2);
			System.out.println(exResult);
		} catch (UndefinedRadicalException e) {
			System.out.println("Undefined");
		} catch (RadicalOperationException e) {
			System.out.println("cannot be multiplied");
		}
		System.out.println("---");
		
		PolySolver factorTest = new PolySolver(0, 0, 0);
		try {
			List<String> factored = factorTest.factor();
			for (String step : factored) {
				//System.out.println(step);
			}
		} catch (UnfactorableQuadraticException e) {
			System.out.println(factorTest + " cannot be factored.");
		}
		System.out.println("---");
		
		String qParse = "2x^2+2x+1";
		String qParse2 = "x^2+x+1";
		String qParse3 = "x^2+1";
		Quadratic parseTest;
		try {
			parseTest = Quadratic.parseQuadratic(qParse3);
			System.out.println(parseTest);
			System.out.println(parseTest.getA());
			System.out.println(parseTest.getB());
			System.out.println(parseTest.getC());
		} catch (QuadraticFormatException e) {
			e.printStackTrace();
		}
		System.out.println("---");
		
		Point px = new Point(1, 1);
		Point py = new Point(2, 2);
		LinearEquation xy = new LinearEquation(px, py);

		System.out.println(xy.getSlope());
		System.out.println(xy.getYIntercept());
		System.out.println(xy.getXIntercept());
		System.out.println(xy);
		
		TreeSet<Point> values = xy.tableOfValues(-0.001, 0.001, 0.0001);
		System.out.println(xy.f(-98));
		for (Point p : values) {
			System.out.println(p);
		}
		
		int[] array = new int[3];
		int[] a = {1, 2, 3, 4};
		array = a;
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
		
		int[][] mArray = new int[2][3];
		for (int i = 0; i < mArray.length; i++) {
			for (int j = 0; j < mArray[i].length; j++) {
				mArray[i][j] = (i + 1) * 10 + j + 1;
				System.out.println("i:" + i + ", j:" + j + " " + Integer.toString(mArray[i][j]));
			}
		}
		
		int[][] mArray2 = new int[2][3];
		for (int i = 0; i < mArray2.length; i++) {
			for (int j = 0; j < mArray2[i].length; j++) {
				mArray2[i][j] = (i + 1) * 20 + (j + 1) * 2;
				System.out.println("i:" + i + ", j:" + j + " " + Integer.toString(mArray2[i][j]));
			}
		}
		
		Matrix m = new Matrix(2, 3, mArray);
		System.out.println("rows: " + m.getNumberOfRows());
		System.out.println("columns: " + m.getNumberOfColumns());
		
		Fraction[][] initial = m.getElements();
		System.out.println("initial: " );
		for (int i = 0; i < initial.length; i++) {
			for (int j = 0; j < initial[i].length; j++) {
				System.out.println("i:" + i + ", j:" + j + " " + initial[i][j]);
			}
		}
		
		m.setMatrixDimensions(5, 5);
		m.setElements(mArray2);
		
		Fraction[][] returned = m.getElements();
		System.out.println("rows: " + m.getNumberOfRows());
		System.out.println("columns: " + m.getNumberOfColumns());
		
		System.out.println("returned: " );
		for (int i = 0; i < returned.length; i++) {
			for (int j = 0; j < returned[i].length; j++) {
				System.out.println("i:" + i + ", j:" + j + " " + returned[i][j]);
			}
		}
		
		for (int i = 0; i < 3; i++) {
			System.out.println(i);
		}
		
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

		// removed MultiRadical, RadicalContianer, RadicalObject
		// RadicalOperation changed from Interface to class (will be merged with
		// MathOperations in the future)
	}
}