package com.math.calculus;


@SuppressWarnings("unused")
public class Term implements Comparable<Term>{
    private int coefficient;
    private int power;

    public Term(int coefficient, int power) {
        this.coefficient = coefficient;
        this.power = power;
    }
    
    public Term(String term) {
        this.coefficient = parseTerm(term).getCoefficient();
        this.power = parseTerm(term).getPower();
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
    
    public static Term parseTerm(String term) {
        int coefficient;
        int power;
        if (term.contains("x")) {
            String coefficientPart = term.substring(0, term.indexOf("x"));
            // Check if string is empty or string contains only a sign and no digits
            if (coefficientPart.isEmpty() || coefficientPart.equals("+")) {
                coefficient = 1;
            } else if (coefficientPart.equals("-")) {
                coefficient = -1;
            } else {
                coefficient = Integer.parseInt(coefficientPart);
            }
            
            if (term.contains("^")) {
                power = Integer.parseInt(term.substring(term.indexOf("^") + 1));
            } else {
                power = 1;
            }
        } else {
            coefficient = Integer.parseInt(term);
            power = 0;
        }
        return new Term(coefficient, power);
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
    
    @Override
    public int compareTo(Term term) {
        return Integer.valueOf(power).compareTo(term.power);
    }
}
