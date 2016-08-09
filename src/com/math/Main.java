package com.math;

import com.math.fraction.Fraction;
import com.math.helpers.MathOperation;
import com.math.helpers.MathUtil;
import com.math.radical.Radical;

/***
 * 
 * @author Archiving
 * Do all temporary backend tests here!
 * (This class is just for the devs to test)
 */

@SuppressWarnings("all")
public class Main {
	
	public static void main(String[] args) {
		Fraction f0 = new Fraction(10, 4);
		
		//System.out.printf("The Decimal of f0 is: " + f0.decimalValue() + "\nThe Value of f0 is: " + f0.toSimplify());
		
		Fraction f1 = new Fraction(98, 4);
		Fraction f2 = new Fraction(26, 2);
		System.out.println("\n98/4 + 26/2 = " + MathOperation.add(f1,f2));
		
		Fraction f3 = new Fraction (2, 4);
		System.out.println("2/4 --> " + f3);
		
		Radical r = new Radical(2, 40, 2);
		System.out.println("Unsimplified: " + r.toString());
		System.out.println("Simplified: " + MathUtil.simplify(r).toString());
		
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
		
		/*
		- MathUtil.factorial() returns 0 instead of UNDEFINED
		- MathOperation renamed to MathFunctions
		- moved methods related to manipulation of Term classes into MathOperation in com.math.helpers
			(includes addition, subtraction, multiplication, division, and power methods of Fractions and Radicals)
		- Overloaded add, subtract, multiply, divide methods to allow for easier batch method calls
		- renamed "degree" field in Fraction to "index"
			similarly named methods have also been renamed
			(getDegree(), setDegree() are now called getIndex(), setIndex())
		- Added a class called MultiRadical for (future) support of representation of multiple Radicals as exact values
		 */
	}
}