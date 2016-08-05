package com.math;

import com.math.fraction.Fraction;

/***
 * 
 * @author Archiving
 * Do all temporary backend tests here!
 * (This class is just for the devs to test)
 */
public class Main {
	
	public static void main(String[] args) {
		Fraction f0 = new Fraction(10, 4);
		
		System.out.printf("The Decimal of f0 is: " + f0.decimalValue() + "\nThe Value of f0 is: " + f0.toSimplify());
		
		Fraction f1 = new Fraction(98, 4);
		Fraction f2 = new Fraction(26, 2);
		System.out.println("\n98/4 + 26/2 = " + f1.add(f2));
	}
	
}