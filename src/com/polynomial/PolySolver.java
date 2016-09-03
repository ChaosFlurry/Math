package com.polynomial;

import java.util.ArrayList;
import java.util.List;

import com.math.fraction.Fraction;
import com.math.helpers.MathUtil;
import com.math.radical.Radical;

public class PolySolver {
	int a;
	int b;
	int c;

	List<String> stringSolutions;
	List<Double> decimalSolutions;

	public PolySolver(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
		stringSolutions = new ArrayList<String>();
		decimalSolutions = new ArrayList<Double>();
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
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

	public List<String> getStringSolutions() {
		return stringSolutions;
	}

	public List<Double> getDecimalSolutions() {
		return decimalSolutions;
	}

	// -b +- sqrt(b^2 - 4ac) / 2a
	// simplify

	public void solve() {
		Radical discriminant;
		int denominator;

		discriminant = new Radical(MathUtil.pow(b, 2) - 4 * a * c, 2);
		denominator = 2 * a;

		int numerator;
		int numeratorGCD;
		int combinedGCD;

		if (discriminant.isUndefined() || denominator == 0) {
			stringSolutions.add("No real solutions.");
			decimalSolutions.add(Double.NaN);
		} else if (discriminant.isANumber()) {
			// positive case
			numerator = -b + MathUtil.nthRoot(discriminant.getRadicand(), 2);
			denominator = 2 * a;
			numeratorGCD = MathUtil.gcd(denominator, numerator);
			combinedGCD = MathUtil.gcd(numeratorGCD, denominator);
			numerator /= combinedGCD;
			denominator /= combinedGCD;

			stringSolutions.add(new Fraction(numerator, denominator).simplify().toString());
			decimalSolutions.add(new Fraction(numerator, denominator).decimalValue());

			// negative case
			if (discriminant.isZero() == false) {
				numerator = -b - MathUtil.nthRoot(discriminant.getRadicand(), 2);
				denominator = 2 * a;
				numeratorGCD = MathUtil.gcd(denominator, numerator);
				combinedGCD = MathUtil.gcd(numeratorGCD, denominator);
				numerator /= combinedGCD;
				denominator /= combinedGCD;
				stringSolutions.add(new Fraction(numerator, denominator).simplify().toString());
				decimalSolutions.add(new Fraction(numerator, denominator).decimalValue());
			}
		} else if (discriminant.isUndefined() == false) {
			discriminant = discriminant.simplify();
			numeratorGCD = MathUtil.gcd(b, discriminant.getCoefficient());
			combinedGCD = MathUtil.gcd(denominator, numeratorGCD);
			discriminant.setCoefficient(discriminant.getCoefficient() / combinedGCD);
			denominator /= combinedGCD;

			// formatting
			String solution = "";
			if (-b / combinedGCD != 0) {
				// 0 does not need to be displayed as n + 0 == n
				solution += "(";
				solution += Integer.toString(-b / combinedGCD);
			}
			solution += "+/-";
			solution += discriminant;
			if (-b / combinedGCD != 0) {
				// close bracket opened only if -b / combinedGCD is not 0
				solution += ")";
			}
			if (denominator != 1) {
				solution += "/";
				solution += denominator;
			}
			if (denominator == 0) {
				solution = "Undefined";
			}
			stringSolutions.add(solution);
			decimalSolutions.add((-b + discriminant.decimalValue()) / denominator);
			decimalSolutions.add((-b - discriminant.decimalValue()) / denominator);
		}
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
		// if a, b, and c are 0
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

		// quadratic cannot be further factored if ax^2 is 0
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

	//uses csq
	public String vertexForm() throws QuadraticFormatException {
		if (a == 0)
			throw new QuadraticFormatException("\"" + this + "\" cannot be converted into vertex form");
		String vertexForm = "";
		
		return vertexForm;
	}
	
	// TODO complete isFactorable()
		public boolean isFactorable() {
			boolean result = false;
			return result;
		}
}
