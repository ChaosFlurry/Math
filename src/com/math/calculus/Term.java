package com.math.calculus;

@SuppressWarnings("unused")
public class Term {
    private int coefficient;
    private int power;

    public Term(int coefficient, int power) {
        this.coefficient = coefficient;
        this.power = power;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public int getPower() {
        return power;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        if (power == 0) {
            return Integer.toString(coefficient);
        } else if (power == 1) {
            if (coefficient == 1) {
                return "x";
            } else if (coefficient == -1) {
                return "-x";
            } else {
                return "" + coefficient + "x";
            }
        } else {
            if (coefficient == 1) {
                return "x^" + power;
            } else if (coefficient == -1) {
                return "-x^" + power;
            } else {
                return "" + coefficient + "x^" + power;
            }
        }
    }
}
