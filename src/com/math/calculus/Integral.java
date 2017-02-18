package com.math.calculus;

import java.util.ArrayList;
import java.util.Collections;

public class Integral {
    public static Function integral(Function f) {
        ArrayList<Term> integralTerms = new ArrayList<>();
        
        integralTerms = Function.groupLikeTerms(integralTerms);
        Collections.sort(integralTerms);
        return new Function(integralTerms);
    }
}
