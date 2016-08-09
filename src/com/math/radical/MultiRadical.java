package com.math.radical;

import java.util.Arrays;
import java.util.List;

public class MultiRadical {
	List<Radical> radicals;
	
	public MultiRadical(Radical... radicals) {
		this.radicals = Arrays.asList(radicals);
	}
	
	//Radical + Radical...
	//MultiRadical + Radical...
	//MultiRadical + MultiRadical...
}
