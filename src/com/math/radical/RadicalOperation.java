package com.math.radical;

import java.util.ArrayList;
import java.util.List;

public class RadicalOperation {
	// TODO Move to MathOperation
	// TODO fix everything
	
	public static List<Radical> add(Radical r, int n) {
		// represent an int as int * sqrt(1)
		List<Radical> result = new ArrayList<Radical>();
		if (n == 0) {
			result.add(r);
		} else if (r.getCoefficient() == 0 || r.getRadicand() == 0 || r.getIndex() == 0) {
			result.add(new Radical(n, 1, 1));
		} else if (r.getRadicand() == 1 || r.getIndex() == 1) {
			result.add(new Radical(r.getRadicand() * r.getCoefficient() + n, 1, 1));
		} else {
			
		}
		return result;
	}

	public static List<Radical> add(Radical r1, Radical r2) {
		List<Radical> result = new ArrayList<Radical>();
	}

	public static List<Radical> add(Radical... radicals) {
		List<Radical> result = new ArrayList<Radical>();
	}

	public static List<Radical> add(List<Radical> radicals) {
		List<Radical> result = new ArrayList<Radical>();
	}

	public static List<Radical> subtract(Radical r, int n) {
		List<Radical> result = new ArrayList<Radical>();
	}

	public static List<Radical> subtract(int n, Radical r) {
		List<Radical> result = new ArrayList<Radical>();
	}

	public static List<Radical> subtract(Radical... radicals) {
		List<Radical> result = new ArrayList<Radical>();
	}

	public static List<Radical> subtract(List<Radical> radicals) {
		List<Radical> result = new ArrayList<Radical>();
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
