package com.polynomial;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Point {
	BigDecimal x;
	BigDecimal y;

	public Point(BigDecimal x, BigDecimal y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(double x, double y) {
		this.x = BigDecimal.valueOf(x);
		this.y = BigDecimal.valueOf(y);
	}

	public BigDecimal getX() {
		return x;
	}
	
	public double getDoubleX() {
		return x.doubleValue();
	}

	public void setX(BigDecimal x) {
		this.x = x;
	}
	
	public void setX(double x) {
		this.x = BigDecimal.valueOf(x);
	}

	public BigDecimal getY() {
		return y;
	}
	
	public double getDoubleY() {
		return y.doubleValue();
	}

	public void setY(BigDecimal y) {
		this.y = y;
	}
	
	public void setY(double y) {
		this.y = BigDecimal.valueOf(y);
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public static Point parsePoint(String point) throws PointFormatException {
		Pattern pointPattern = Pattern.compile("^\\((\\d+(?:\\.\\d+)?),\\s(\\d+(?:\\.\\d+)?)\\)$");
		Matcher pointMatcher = pointPattern.matcher(point);
		if (pointMatcher.matches()) {
			BigDecimal x = new BigDecimal(pointMatcher.group(1));
			BigDecimal y = new BigDecimal(pointMatcher.group(2));
			return new Point(x, y);
		} else {
			throw new PointFormatException("For input string: \"" + point + "\"");
		}
	}
}
