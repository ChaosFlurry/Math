package com.math.radical;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author ChaosFlurry
 *
 */

public class MultiRadical extends Radical {
	List<Radical> radicals;
	
	public MultiRadical(Radical... radicals) {
		this.radicals = Arrays.asList(radicals);
	}
	
	public MultiRadical(List<Radical> radicals) {
		this.radicals = radicals;
	}

	@Override
	public String toString() {
		String result = "";
		for (Radical r : radicals) {
			result += r.toString();
		}
		return result;
	}
	
	public void simplify() {
		for (Radical r : radicals) {
			int nthRoot = Math.pow(this.radicand, (double) 1 / r.)
		}

	}
	
	public double decimalValue() {
		double result;
		
		return result;
	}
	
	//Radical + Radical...
	//MultiRadical + Radical...
	//MultiRadical + MultiRadical...
}
