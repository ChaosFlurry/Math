package com.math.trigonometry;

import java.util.HashMap;
import java.util.Map.Entry;

import com.math.fraction.Fraction;

public class Triangle {

	public HashMap<String, Side> sides;
	public boolean rightAngle = false;

	public Triangle(HashMap<String, Side> sides) {
		
		try {
			createTriangle(sides);
		}
		catch(TriangleCreationException e) {
			e.printStackTrace();
		}
		
		for (Entry<String, Side> side : sides.entrySet()) {
			if ((((Side) side).getAngle() == 90))
				rightAngle = true;
		}
	}

	public void createTriangle(HashMap<String, Side> sides) throws TriangleCreationException {
		if(sides.size() > 3) throw new TriangleCreationException("Cannot create a Triangle! Your triangle's sides exceed 3");
		
		double angleSum = 0;
		for(Entry<String, Side> side : sides.entrySet()) {
			angleSum += ((Side)side).getAngle();
		}
		
		if(angleSum < 180) throw new TriangleCreationException("Cannot create a Triangle! Your angles add up to more than 180!"); 
		
		this.sides = sides;
	}

	public HashMap<String, Side> getSides() {
		return sides;
	}

	public boolean isRightAngle() {
		return rightAngle;
	}
	
	/**
	 * 
	 * @return right triangles - b*c
	 * @return non-right triangles - 0.5bc*(sin(A))
	 * @throws TriangleCreationException 
	 * 
	 */
	public double getArea() throws TriangleCreationException {
		if(this.getSide("a").getAngle() != 0 && this.getSide("b").getLength() != 0 && this.getSide("c").getLength() == 0) throw new TriangleCreationException("You need 2 lengths (b,c) and 1 angle (a) to not be null!");
		return (rightAngle) ?
				this.getSide("b").getLength() * this.getSide("c").getLength() :
					(0.5D * this.getSide("b").getLength() * this.getSide("c").getLength() * Math.sin(this.getSide("a").getAngle()));
	}
	
	public Side getSide(String key) {
		return sides.get(key);
	}
	
	public Fraction getSinRatio(String angleTag) {
		if(!rightAngle) return null;
		
		
		Fraction ratio = null;
		switch(angleTag) {
		case "a":

			break;
		case "b":
			
			break;
		case "c":
			
			break;
		}
		return ratio;
	}
	
	public int getDoubleToInt(double n) {
		String text = Double.toString(Math.abs(n));
		int integerPlaces = text.indexOf('.');
		int decimalplaces = text.length() - integerPlaces - 1;
		
		if(decimalplaces > 0) {
			int base = 10;
			n =	Math.pow(base, decimalplaces);
		}
		
		return (int)n;
	}
}
