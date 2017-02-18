package com.math.calculus;

import java.util.ArrayList;
import java.util.Collections;

public class Derivative {
    public static Function derivative(Function f) {
        ArrayList<Term> derivativeTerms = new ArrayList<>();
        for (Term t : f.getTerms()) {
            if (t.getPower() != 0) {
                derivativeTerms.add(new Term(t.getPower() * t.getCoefficient(), t.getPower() - 1));
            }
        }
        derivativeTerms = Function.groupLikeTerms(derivativeTerms);
        Collections.sort(derivativeTerms);
        return new Function(derivativeTerms);
    }
}
