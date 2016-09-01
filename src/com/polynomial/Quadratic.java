package com.polynomial;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.math.helpers.MathUtil;

public class Quadratic {
	int a;
	int b;
	int c;

	public Quadratic(int a, int b, int c) {
		// TODO allow a == 0?
		if (a < 0) {
			a *= -1;
			b *= -1;
			c *= -1;
		}
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		if (a < 0) {
			this.a = a * -1;
			this.b *= -1;
			this.c *= -1;
		} else {
			this.a = a;
		}
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public static Quadratic parseQuadratic(String quadratic) throws QuadraticFormatException {
		quadratic = quadratic.trim();
		Pattern quadraticPattern = Pattern.compile("^(\\d*|-\\d*)x\\^2((\\+\\d*|-\\d*)x)?(\\+\\d*|-\\d*)?$");
		Matcher quadraticMatcher = quadraticPattern.matcher(quadratic);

		if (quadraticMatcher.matches()) {
			// quadraticPattern capture groups:
			/*
			 * 1: sgnA + a 2: unused 3: sgnB + b 4: c
			 */
			String strA = quadraticMatcher.group(1);
			String strB = quadraticMatcher.group(3);
			String strC = quadraticMatcher.group(4);

			int a;
			int b;
			int c;

			// a cannot be 0
			if (strA.equals(""))
				a = 1;
			else if (strA.equals("-"))
				a = -1;
			else
				a = Integer.parseInt(strA);

			if (strB.equals(""))
				b = 0;
			else if (strB.equals("+"))
				b = 1;
			else if (strB.equals("-"))
				b = -1;
			else
				b = Integer.parseInt(strB);

			if (strC.equals(""))
				c = 0;
			else
				c = Integer.parseInt(strC);

			return new Quadratic(a, b, c);
		} else {
			throw new QuadraticFormatException("For input string: \"" + quadratic + "\"");
		}
	}

	@Override
	public String toString() {
		String result = "";

		if (a == 1)
			result += "x^2";
		else if (a != 0)
			result += a + "x^2";

		if (b == 1) {
			if (a != 0) {
				result += "+x";
			} else {
				result += "x";
			}
		} else if (b == -1) {
			result += "-x";
		} else if (b > 1) {
			if (a != 0) {
				result += "+" + b + "x";
			} else {
				result += b + "x";
			}
		} else if (b < -1) {
			result += "-" + b + "x";
		}

		if (a == 0 && b == 0)
			result += c;
		else if (c >= 1)
			result += "+" + c;
		else if (c <= -1)
			result += c;

		return result;
	}

	public boolean isFactorable() {
		boolean result = false;
		return result;
	}

	public List<String> factor() throws UnfactorableQuadraticException {
		// TODO return a List<String> of all steps

		// A quadratic is factorable if:
		// there exists a pair of numbers y, z where yz = ac and y+z = b

		int a = this.a;
		int b = this.b;
		int c = this.c;

		List<String> steps = new ArrayList<String>();
		String currentStep;
		// print quadratic
		currentStep = this.toString();
		steps.add(currentStep);

		// special cases: x^2, x^2+bx
		// note: ax^2 +- c is not a special case

		// ax^2+bx: x can be factored out, a coefficient may be facotred out
		// ax^2+c: a coefficient may be factored out

		// Factor out common factors, if possible
		// use varargs gcd function when it has been completed
		int coefficient = MathUtil.gcd(a, MathUtil.gcd(b, c));
		//if a, b, and c are 0
		if (coefficient == 0) {
			return steps;
		}
		a /= coefficient;
		b /= coefficient;
		c /= coefficient;

		// special case ax^2+0
		if (a != 0 && b == 0 && c == 0) {
			steps.add("x^2");
			steps.add("x = 0");
			return steps;
		}

		// special case ax^2+bx+0
		if (a != 0 && b != 0 && c == 0) {
			if (coefficient != 1) {
				steps.add(coefficient + "x(" + new Quadratic(a, b, c) + ")");
			} else {
				steps.add("x(" + new Quadratic(a, b, c) + ")");
			}
			return steps;
		}

		// print initially factored quadratic if quadratic can be factored
		// also factors polynomials in the form of bx+c TODO port to its own
		// function?
		if (coefficient != 1) {
			currentStep = coefficient + "(" + new Quadratic(a, b, c) + ")";
			steps.add(currentStep);
		}
		
		//quadratic cannot be further factored if ax^2 is 0
		if (a == 0) {
			return steps;
		}

		// Determine whether quadratic is factorable
		// when c is positive: y AND z are positive OR y AND z are negative
		// when c is negative: y OR z is negative
		// when b is positive: max(y, z) is positive, one or more of y, z is
		// positive
		// when b is negative: max(y, z) is negative, one or more of y, z is
		// negative
		List<Integer> divisors = MathUtil.divisors(a * c);
		int y = 0;
		int z = 0;

		for (int i = 0; i < divisors.size(); i++) {
			y = divisors.get(i);
			z = divisors.get(divisors.size() - 1 - i);

			if (y > z) {
				// repeated divisors, no factors found
				throw new UnfactorableQuadraticException(this + "cannot be factored.");
			}

			if (y + z == b) {
				break;
			} else if (-y - z == b) {
				y *= -1;
				z *= -1;
				break;
			} else if (-y + z == b) {
				y *= -1;
				break;
			} else if (y - z == b) {
				z *= -1;
				break;
			}
		}

		// print decomposed quadratic
		currentStep = "";
		if (coefficient != 1)
			currentStep = coefficient + "(";

		if (a == 1)
			currentStep += "x^2";
		else if (a != 0)
			currentStep += a + "x^2";

		if (y == 1)
			currentStep += "+x";
		else if (y == -1)
			currentStep += "-x";
		else if (y > 1)
			currentStep += "+" + y + "x";
		else if (y < -1)
			currentStep += y + "x";

		if (z == 1)
			currentStep += "+x";
		else if (z == -1)
			currentStep += "-x";
		else if (z > 1)
			currentStep += "+" + z + "x";
		else if (z < -1)
			currentStep += z + "x";

		if (c >= 1)
			currentStep += "+" + c;
		if (c <= -1)
			currentStep += c;
		if (coefficient != 1)
			currentStep += ")";
		steps.add(currentStep);

		// factor out terms
		int coefficientY = MathUtil.gcd(a, y);
		int coefficientZ = MathUtil.gcd(c, z);

		// x^2 + x + 6x + 6
		// x(x+1) + 6(x+1)
		a /= coefficientY;
		y /= coefficientY;
		z /= coefficientZ;
		c /= coefficientZ;

		if (a < 0 && y < 0) {
			a *= -1;
			y *= -1;
		}
		if (z < 0 && c < 0) {
			z *= -1;
			c *= -1;
		}

		currentStep = "";
		String factorY = "";
		if (coefficientY == -1) {
			currentStep += "-";
			factorY += "-";
		} else if (coefficientY != 1) {
			currentStep += coefficientY;
			factorY += coefficientY;
		}
		currentStep += "x";
		factorY += "x";

		currentStep += "(";
		if (a == 1) {
			currentStep += "x";
		} else if (a == -1) {
			currentStep += "-x";
		} else {
			currentStep += a + "x";
		}

		if (y > 0) {
			currentStep += "+" + y;
		} else if (y < 0) {
			currentStep += y;
		}
		currentStep += ")";

		if (coefficientZ > 0) {
			currentStep += "+" + coefficientZ;
			factorY += "+" + coefficientZ;
		} else {
			currentStep += coefficientZ;
			factorY += coefficientZ;
		}

		String factorZ = "";
		currentStep += "(";
		if (z == 1) {
			currentStep += "x";
			factorZ += "x";
		} else if (z == -1) {
			currentStep += "-x";
			factorZ += "-x";
		} else {
			currentStep += z + "x";
			factorZ += z + "x";
		}

		if (c > 0) {
			currentStep += "+" + c;
			factorZ += "+" + c;
		} else if (c < 0) {
			currentStep += c;
			factorZ += c;
		}
		currentStep += ")";
		steps.add(currentStep);

		String factoredQuadratic = "(" + factorY + ")" + "(" + factorZ + ")";
		steps.add(factoredQuadratic);
		return steps;
	}

	/**
	 * TODO update this javadoc Returns a normalized String of a term of a
	 * polynomial that is ready to be printed. Instead of ...+1x method will
	 * return ... +x -1x will return -x etc.
	 * 
	 * @param n
	 *            An integer
	 * @return A string that does not need to be further formatted to be printed
	 */
	public String signFormatted(int n) {
		// default case 0
		String result = "";
		if (n == 1)
			result = "+";
		else if (n > 1)
			result = "+" + Integer.toString(n);
		else if (n == -1)
			result = "-";
		else if (n < -1)
			result = Integer.toString(n);
		return result;
	}

	/**
	 * Takes a sign (with optional number) and returns a string of the
	 * represented value. "-" returns -1, "+" returns 1, "x" or "+x" returns x
	 * as an integer, "-x" returns -x as an integer. "0" or a blank string
	 * returns 0. Coincidentally, all other inputs except the above will also
	 * return a blank string. Assure that all inputs are checked before passed
	 * into the method.
	 * 
	 * @param s
	 *            A string
	 * @return An integer of the parsed string
	 */
	public int parseSign(String s) {
		Pattern number = Pattern.compile("^(\\+|-)?[1-9]\\d*$");
		Matcher matcher = number.matcher(s);
		s = s.trim();

		int result = 0;
		if (s.equals("+")) {
			result = 1;
		} else if (s.equals("-")) {
			result = -1;
		} else if (matcher.matches()) {
			result = Integer.parseInt(s);
		}
		return result;
	}

	/*
	 * public static String expand(String factoredQuadratic) {
	 * 
	 * }
	 */
}
