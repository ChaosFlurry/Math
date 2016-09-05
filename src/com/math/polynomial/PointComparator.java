package com.math.polynomial;

import java.util.Comparator;

public class PointComparator implements Comparator<Point> {
	@Override
	public int compare(Point a, Point b) {
		return a.getX().compareTo(b.getX()) < 0 ? -1 : a.getX().compareTo(b.getX()) == 0 ? 0 : 1;
	}
}
