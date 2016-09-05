package com.math.polynomial;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Quadratic {
	BigDecimal a;
	BigDecimal b;
	BigDecimal c;
	MathContext rounding = new MathContext(34, RoundingMode.HALF_UP);

	public Quadratic(BigDecimal a, BigDecimal b, BigDecimal c) {
		if (a.compareTo(BigDecimal.ZERO) < 0) {
			// a, b, c *= -1
			a = a.multiply(BigDecimal.ONE.negate());
			b = b.multiply(BigDecimal.ONE.negate());
			c = c.multiply(BigDecimal.ONE.negate());
		}
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public Quadratic(double a, double b, double c) {
		BigDecimal x = BigDecimal.valueOf(a);
		BigDecimal y = BigDecimal.valueOf(b);
		BigDecimal z = BigDecimal.valueOf(c);

		if (x.compareTo(BigDecimal.ZERO) < 0) {
			// x, y, z *= -1
			x = x.multiply(BigDecimal.ONE.negate());
			y = y.multiply(BigDecimal.ONE.negate());
			z = z.multiply(BigDecimal.ONE.negate());
		}
		this.a = x;
		this.b = y;
		this.c = z;
	}

	public BigDecimal getA() {
		return a;
	}

	public double getDoubleA() {
		return a.doubleValue();
	}

	public void setA(BigDecimal a) {
		if (a.compareTo(BigDecimal.ZERO) < 0) {
			this.a = a.negate();
			b = b.negate();
			c = c.negate();
		} else {
			this.a = a;
		}
	}
	
	public void setA(double a) {
		this.a = BigDecimal.valueOf(a);
		if (this.a.compareTo(BigDecimal.ZERO) < 0) {
			// a, b, c *= -1
			this.a = this.a.multiply(BigDecimal.ONE.negate());
			b = b.multiply(BigDecimal.ONE.negate());
			c = c.multiply(BigDecimal.ONE.negate());
		}
	}

	public BigDecimal getB() {
		return b;
	}

	public double getDoubleB() {
		return b.doubleValue();
	}

	public void setB(BigDecimal b) {
		this.b = b;
	}
	
	public void setB(double b) {
		this.b = BigDecimal.valueOf(b);
	}

	public BigDecimal getC() {
		return c;
	}

	public double getDoubleC() {
		return c.doubleValue();
	}

	public void setC(BigDecimal c) {
		this.c = c;
	}
	
	public void setC(double c) {
		this.c = BigDecimal.valueOf(c);
	}

	public static Quadratic parseQuadratic(String quadratic) throws QuadraticFormatException {
		quadratic = quadratic.trim();

		// matches quadratics in the form of ax^2+bx+c
		Pattern ax2bxc = Pattern.compile("^(-?\\d*(?:\\.\\d+)?)x\\^2([+-]\\d*(?:\\.\\d+)?)x([+-]\\d+(?:\\.\\d+)?)");
		// matches quadratics in the form of ax^2+bx
		Pattern ax2bx = Pattern.compile("^(-?\\d*(?:\\.\\d+)?)x\\^2([+-]\\d*(?:\\.\\d+)?)x$");
		// matches quadratics in the form of ax^2+c
		Pattern ax2c = Pattern.compile("^(-?\\d*(?:\\.\\d+)?)x\\^2([+-]\\d+(?:\\.\\d+)?)$");
		// matches quadratics in the form of ax^2
		Pattern ax2 = Pattern.compile("^(-?\\d*(?:\\.\\d+)?)x\\^2$");

		Matcher ax2bxcMatcher = ax2bxc.matcher(quadratic);
		Matcher ax2bxMatcher = ax2bx.matcher(quadratic);
		Matcher ax2cMatcher = ax2c.matcher(quadratic);
		Matcher ax2Matcher = ax2.matcher(quadratic);

		BigDecimal a = BigDecimal.ZERO;
		BigDecimal b = BigDecimal.ZERO;
		BigDecimal c = BigDecimal.ZERO;
		if (ax2bxcMatcher.matches()) {
			if (ax2bxcMatcher.group(1).equals("-"))
				a = BigDecimal.ONE.negate();
			else if (ax2bxcMatcher.group(1).equals(""))
				a = BigDecimal.ONE;
			else
				a = new BigDecimal(ax2bxcMatcher.group(1));

			if (ax2bxcMatcher.group(2).equals("-"))
				b = BigDecimal.ONE.negate();
			else if (ax2bxcMatcher.group(2).equals(""))
				b = BigDecimal.ONE;
			else
				b = new BigDecimal(ax2bxcMatcher.group(2));

			c = new BigDecimal(ax2bxcMatcher.group(3));
		} else if (ax2bxMatcher.matches()) {
			if (ax2bxMatcher.group(1).equals("-"))
				a = BigDecimal.ONE.negate();
			else if (ax2bxMatcher.group(1).equals(""))
				a = BigDecimal.ONE;
			else
				a = new BigDecimal(ax2bxMatcher.group(1));

			if (ax2bxMatcher.group(2).equals("-"))
				b = BigDecimal.ONE.negate();
			else if (ax2bxMatcher.group(2).equals(""))
				b = BigDecimal.ONE;
			else
				b = new BigDecimal(ax2bxMatcher.group(2));
		} else if (ax2cMatcher.matches()) {
			if (ax2cMatcher.group(1).equals("-"))
				a = BigDecimal.ONE.negate();
			else if (ax2cMatcher.group(1).equals(""))
				a = BigDecimal.ONE;
			else
				a = new BigDecimal(ax2cMatcher.group(1));

			c = new BigDecimal(ax2cMatcher.group(2));
		} else if (ax2Matcher.matches()) {
			if (ax2Matcher.group(1).equals("-"))
				a = BigDecimal.ONE.negate();
			else if (ax2Matcher.group(1).equals(""))
				a = BigDecimal.ONE;
			else
				a = new BigDecimal(ax2Matcher.group(1));
		} else {
			throw new QuadraticFormatException("For input string \"" + quadratic + "\"");
		}
		return new Quadratic(a, b, c);
	}

	@Override
	public String toString() {
		String result = "";

		if (a.compareTo(BigDecimal.ONE) == 0)
			result += "x^2";
		else if (a.compareTo(BigDecimal.ZERO) != 0)
			result += a + "x^2";

		if (b.compareTo(BigDecimal.ONE) == 0) {
			if (a.compareTo(BigDecimal.ZERO) != 0) {
				result += "+x";
			} else {
				result += "x";
			}
		} else if (b.compareTo(BigDecimal.ONE.negate()) == 0) {
			result += "-x";
		} else if (b.compareTo(BigDecimal.ONE) > 0) {
			if (a.compareTo(BigDecimal.ZERO) != 0) {
				result += "+" + b + "x";
			} else {
				result += b + "x";
			}
		} else if (b.compareTo(BigDecimal.ONE.negate()) < 0) {
			result += "-" + b + "x";
		}

		if (a.compareTo(BigDecimal.ZERO) == 0 && b.compareTo(BigDecimal.ZERO) == 0)
			result += c;
		else if (c.compareTo(BigDecimal.ONE) >= 0)
			result += "+" + c;
		else if (c.compareTo(BigDecimal.ONE) <= 0)
			result += c;

		return result;
	}

	public BigDecimal f(BigDecimal x) {
		return a.multiply(x.pow(2)).add(b.multiply(x)).add(c);
	}
	
	public BigDecimal f(double x) {
		BigDecimal d = BigDecimal.valueOf(x);
		return a.multiply(d.pow(2)).add(b.multiply(d)).add(c);
	}

	public TreeSet<Point> tableOfValues(BigDecimal min, BigDecimal max, BigDecimal step) {
		min = min.min(max);
		max = max.max(min);
		TreeSet<Point> values = new TreeSet<Point>(new PointComparator());

		if (step.compareTo(BigDecimal.ZERO) == 0 || min.compareTo(max) == 0) {
			values.add(new Point(min, f(min)));
		} else {
			for (BigDecimal x = min; x.compareTo(max) <= 0; x = x.add(step)) {
				values.add(new Point(x, f(x)));
			}
		}
		return values;
	}
	
	public TreeSet<Point> tableOfValues(double min, double max, double step) {
		BigDecimal a = BigDecimal.valueOf(Math.min(min, max));
		BigDecimal b = BigDecimal.valueOf(Math.max(min, max));
		BigDecimal s = BigDecimal.valueOf(step);
		TreeSet<Point> values = new TreeSet<Point>(new PointComparator());

		if (step == 0 || (min == max)) {
			values.add(new Point(a, f(a)));
		} else {
			for (BigDecimal x = a; x.compareTo(b) <= 0; x = x.add(s)) {
				values.add(new Point(x, f(x)));
			}
		}
		return values;
	}
}
