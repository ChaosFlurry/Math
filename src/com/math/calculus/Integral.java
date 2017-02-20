package com.math.calculus;

import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("unused")
public class Integral {
    public static Function integral(Function f) {
        ArrayList<Term> integralTerms = new ArrayList<>();
        // TODO Incomplete
        integralTerms = Function.groupLikeTerms(integralTerms);
        Collections.sort(integralTerms);
        return new Function(integralTerms);
    }
}
