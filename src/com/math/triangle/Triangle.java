package com.math.triangle;

public class Triangle {
    Side a;
    Side b;
    Side c;
    Angle x;
    Angle y;
    Angle z;

    public Triangle(Side a, Side b, Side c, Angle x, Angle y, Angle z) {
        //check if triangle exists
        this.a = a;
        this.b = b;
        this.c = c;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Triangle(Side a, Side b, Side c) {

    }

    public Triangle(Side a, Side b, Angle z) {

    }

    public Triangle(Side a, Angle x, Side b) {

    }

    public Triangle(Side a, Angle x, Angle y) {

    }
}
