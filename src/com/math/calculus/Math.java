package com.math.calculus;

public class Math {
    public static int pow(int n, int degree) {
        int result;
        if (degree < 0) {
            throw new IllegalArgumentException("Negative powers are not supported");
        } else if (degree == 0) {
            if (n == 0) {
                throw new ArithmeticException("Zero cannot be raised to the zeroth power");
            }
            result = 1;
        } else if (degree == 1) {
            result = n;
        } else {
            result = 1;
            for (int i = 0; i < degree; i++) {
                result *= n;
            }
        }
        return result;
    }
}
