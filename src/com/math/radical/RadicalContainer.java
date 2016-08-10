package com.math.radical;

import java.util.Arrays;
import java.util.List;

public class RadicalContainer extends Radical {
	List<RadicalObject> terms;
	
	public RadicalContainer(List<RadicalObject> terms) {
		this.terms = terms;
	}
	
	public RadicalContainer(RadicalObject... terms) {
		this.terms = Arrays.asList(terms);
	}
	
	@Override
	public String toString() {
		//temp
		return "RadicalContainer";
	}
	
	@Override
	public double decimalValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void simplify() {
		
	}
	
	
}
