package com.polynomial;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinearEquation {
	double slope;
	double xIntercept;
	double yIntercept;
	
	MathContext rounding = new MathContext(34, RoundingMode.HALF_UP);

	public LinearEquation(double slope, double yIntercept) {
		this.slope = slope;
		if (Double.valueOf(slope).isInfinite()) {
			this.yIntercept = Double.NaN;
			this.xIntercept = 0;
		} else if (slope == 0) {
			this.yIntercept = yIntercept;
			this.xIntercept = Double.NaN;
		} else {
			this.yIntercept = yIntercept;
			this.xIntercept = -yIntercept / slope;
		}
	}

	public LinearEquation(double slope, Point p) {
		if (Double.valueOf(slope).isInfinite()) {
			this.yIntercept = Double.NaN;
			this.xIntercept = p.getX();
		} else if (slope == 0) {
			this.yIntercept = p.getY();
			this.xIntercept = Double.NaN;
		} else {
			this.slope = slope;
			this.yIntercept = slope * -p.getX() + p.getY();
			this.xIntercept = -p.getY() / slope + p.getX();
		}
	}

	public LinearEquation(Point p1, Point p2) {
		this.slope = slopeOf(p1, p2);
		if (Double.valueOf(slope).isInfinite()) {
			this.yIntercept = Double.NaN;
			this.xIntercept = p1.getX();
		} else if (slope == 0) {
			this.yIntercept = p1.getY();
			this.xIntercept = Double.NaN;
		} else {
			this.yIntercept = slope * -p1.getX() + p1.getY();
			this.xIntercept = -p1.getY() / slope + p1.getX();
		}
	}

	public double getSlope() {
		return slope;
	}

	public double getXIntercept() {
		return xIntercept;
	}

	public double getYIntercept() {
		return yIntercept;
	}

	/**
	 * Returns a string representation of the linear equation in slope-intercept
	 * form.
	 * 
	 * @return A string of the linear equation converted to slope-intercept form
	 */
	@Override
	public String toString() {
		// TODO decimal formatting
		if (Double.valueOf(slope).isInfinite()) {
			return "x=" + xIntercept;
		}

		String result = "y=";
		if (slope == 1)
			result += "x";
		else if (slope == -1)
			result += "-x";
		else if (slope > 0)
			result += Double.toString(slope) + "x";
		else if (slope < 0)
			result += Double.toString(slope) + "x";

		if (slope != 0) {
			if (yIntercept == 1)
				result += "+1";
			else if (yIntercept == -1)
				result += yIntercept;
			else if (yIntercept > 0)
				result += "+" + Double.toString(yIntercept);
			else if (yIntercept < 0)
				result += Double.toString(yIntercept);
		} else {
			result += Double.toString(yIntercept);
		}
		return result;
	}

	public double f(double x) {
		BigDecimal d = BigDecimal.valueOf(x);
		BigDecimal slope = BigDecimal.valueOf(this.slope);
		BigDecimal yIntercept = BigDecimal.valueOf(this.yIntercept);
		return (slope.multiply(d).add(yIntercept)).doubleValue();
	}
	
	public double f(BigDecimal x) {
		BigDecimal slope = BigDecimal.valueOf(this.slope);
		BigDecimal yIntercept = BigDecimal.valueOf(this.yIntercept);
		return (slope.multiply(x).add(yIntercept)).doubleValue();
	}

	public TreeSet<Point> tableOfValues(double min, double max, double step) {
		BigDecimal a = BigDecimal.valueOf(Math.min(min, max));
		BigDecimal b = BigDecimal.valueOf(Math.max(min, max));
		BigDecimal s = BigDecimal.valueOf(step);
		TreeSet<Point> values = new TreeSet<Point>(new PointComparator());

		if (step == 0 || (min == max)) {
			values.add(new Point(min, f(min)));
		} else {
			for (BigDecimal x = a; x.compareTo(b) < 0; x = x.add(s)) {
				values.add(new Point(x.doubleValue(), f(x)));
			}
		}
		return values;
	}

	public LinearEquation parseLinearEquation(String linearEquation) throws LinearEquationFormatException {
		linearEquation = linearEquation.trim();
		// matches the form y=mx+b
		Pattern linearEquationPattern1 = Pattern.compile("^y=(-?\\d+(?:\\.\\d+)?)?x((?:\\+|-)\\d+(?:\\.\\d+)?)?$");
		// matches the form y=b
		Pattern linearEquationPattern2 = Pattern.compile("^y=(-?\\d+(?:\\.\\d+)?)?$");
		// matches the form x=b
		Pattern linearEquationPattern3 = Pattern.compile("^x=(-?\\d+(?:\\.\\d+)?)?$");
		Matcher linearEquationMatcher1 = linearEquationPattern1.matcher(linearEquation);
		Matcher linearEquationMatcher2 = linearEquationPattern2.matcher(linearEquation);
		Matcher linearEquationMatcher3 = linearEquationPattern3.matcher(linearEquation);

		if (linearEquationMatcher1.matches()) {
			double slope = Double.parseDouble(linearEquationMatcher1.group(1));
			double yIntercept = Double.parseDouble(linearEquationMatcher1.group(2));
			return new LinearEquation(slope, yIntercept);
		} else if (linearEquationMatcher2.matches()) {
			double slope = 0;
			double yIntercept = Double.parseDouble(linearEquationMatcher2.group(1));
			return new LinearEquation(slope, yIntercept);
		} else if (linearEquationMatcher3.matches()) {
			double slope = Double.POSITIVE_INFINITY;
			Point p = new Point(Double.parseDouble(linearEquationMatcher3.group(1)), 0);
			return new LinearEquation(slope, p);
		} else {
			throw new LinearEquationFormatException("For input string: \"" + linearEquation + "\"");
		}
	}

	/**
	 * Returns Double.NaN if both points are equal to each other. Returns +/-inf
	 * if x2-x1 is 0.
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double slopeOf(Point p1, Point p2) {
		double x1 = p1.getX();
		double y1 = p1.getY();
		double x2 = p2.getX();
		double y2 = p2.getY();
		return (y2 - y1) / (x2 - x1);
	}

	/**
	 * Returns Double.NaN if both points are equal to each other. Returns +/-inf
	 * if x2-x1 is 0.
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static double slopeOf(double x1, double y1, double x2, double y2) {
		return (y2 - y1) / (x2 - x1);
	}
}
