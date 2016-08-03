package radical;

import fraction.Fraction;

public class Radical {
	int coefficient;
	int radicand;
	int degree;
	boolean undefined;
	
	//let a radical be more than 1 radicand
	//i.e. Radical r can be represented as multiple terms as e.g. 2sqrt5 + sqrt3
	//let there be a list of Integers denoting coefficients, radicands, and degrees for each term
	
	public Radical(int radicand, int degree) {
		//check if radicand is negative
		//check if degree is 0
		setCoefficient(1);
		setRadicand(radicand);
		setDegree(degree);
		simplify();
	}
	
	public Radical(int coefficient, int radicand, int degree) {
		setCoefficient(coefficient);
		setRadicand(radicand);
		setDegree(degree);
		simplify();
	}
	
	public Radical(int radicand, Fraction degree) {
		setCoefficient(1);
		setRadicand((int) Math.pow(radicand, degree.getNumerator()));
		setDegree(degree.getDenominator());
		simplify();
	}
	
	public Radical(int coefficient, int radicand, Fraction degree) {
		setCoefficient(coefficient);
		setRadicand((int) Math.pow(radicand, degree.getNumerator()));
		setDegree(degree.getDenominator());
		simplify();
	}

	public int getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}

	public int getRadicand() {
		return radicand;
	}

	public void setRadicand(int radicand) {
		this.radicand = radicand;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}
	
	public void setDegree(Fraction degree) {
		setRadicand((int) Math.pow(getRadicand(), degree.getNumerator()));
		this.degree = degree.getDenominator();
	}
	
	public boolean isUndefined() {
		return undefined;
	}
	
	@Override
	public String toString() {
		if (isUndefined()) {
			return "undefined";
		} else if (getRadicand() == 0) {
			return "0";
		} else if (getCoefficient() == 0) {
			return "0";
		} else if (getRadicand() == 1) {
			return Integer.toString(getCoefficient());
		} else if (getCoefficient() == 1) {
			if (getDegree() == 1) {
				return Integer.toString(getRadicand());
			} else {
				return getRadicand() + "^(1/" + getDegree() + ")";
			}
		}
		else {
			return getCoefficient() + "*" + getRadicand() + "^(1/" + getDegree() + ")";
		}
	}
	
	public void simplify() {
		System.out.println("Radicand: " + getRadicand());
		if (Fraction.isPrime(getRadicand()) == false) {
			if (getDegree() == 0) {
				undefined = true;
			} else {
				//if n % nthRoot == 0; then coefficient *= i, radicand /= i
				//iterate from nthRoot to 2 (lowest possible prime factor)
				int nthRoot = (int) Math.pow(getRadicand(), (1.0 / getDegree()));
				for (int i = nthRoot; i >= 2; i--) {
					if (getRadicand() % i == 0) {
						setRadicand(getRadicand() / i);
						setCoefficient(getCoefficient() * i);
					}
				}
			}
		}

	}
	
}
