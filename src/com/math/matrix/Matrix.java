package com.math.matrix;

import java.util.Arrays;

import com.math.fraction.Fraction;

public class Matrix {
	// add and multiply matrix
	// https://en.wikipedia.org/wiki/Matrix_addition
	// https://en.wikipedia.org/wiki/Scalar_multiplication
	//
	// transpositions of matrices
	// https://en.wikipedia.org/wiki/Transpose
	//
	// create blank (all zero) matrix of x by y collumns and r-ows?
	// converting an int matrix to fraction matrix
	// parsing a matrix
	// printing a matrix

	// modifications of individual rows/columns
	// division (multiply by inverse)
	// comparison of matrices
	// inverse of matrices
	// determinants of matrices

	int rows;
	int columns;
	Fraction[][] elements;

	public Matrix(int rows, int columns, Fraction[][] elements) {
		if (rows <= 0 || columns <= 0) {
			throw new IllegalArgumentException("Invalid matrix dimensions");
		}
		this.rows = rows;
		this.columns = columns;
		setElements(elements);
	}

	public Matrix(int rows, int columns, int[][] elements) {
		if (rows <= 0 || columns <= 0) {
			throw new IllegalArgumentException("Invalid matrix dimensions");
		}
		this.rows = rows;
		this.columns = columns;
		setElements(elements);
	}

	public int getNumberOfRows() {
		return rows;
	}

	public void setNumberOfRows(int rows) {
		if (rows <= 0) {
			throw new IllegalArgumentException("Invalid matrix dimensions");
		}
		this.rows = rows;
		setElements(elements);
	}

	public int getNumberOfColumns() {
		return columns;
	}

	public void setNumberOfColumns(int columns) {
		if (columns <= 0) {
			throw new IllegalArgumentException("Invalid matrix dimensions");
		}
		this.columns = columns;
		setElements(elements);
	}

	public void setMatrixDimensions(int rows, int columns) {
		if (columns <= 0 || rows <= 0) {
			throw new IllegalArgumentException("Invalid matrix dimensions");
		}
		this.rows = rows;
		this.columns = columns;
	}

	public Fraction[][] getElements() {
		return elements;
	}

	public void setElements(Fraction[][] elements) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				try {
					this.elements[i][j] = elements[i][j].simplify();
				} catch (ArrayIndexOutOfBoundsException ex) {
					this.elements[i][j] = new Fraction(0, 1);
				}
			}
		}
	}

	public void setElements(int[][] elements) {
		// initialize elements with blank values
		this.elements = new Fraction[rows][columns];

		// match dimensions of matrix
		int[] blank = new int[columns];
		for (int i = 0; i < columns; i++) {
			blank[i] = 0;
		}

		int[][] intElements = new int[rows][columns];
		for (int i = 0; i < rows; i++) {
			intElements[i] = blank;
		}

		// add int elements to a temporary array
		for (int i = 0; i < rows; i++) {
			intElements[i] = Arrays.copyOf(elements[i], columns);
		}

		// convert ints to fractions, transfer values to fraction array
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				this.elements[i][j] = Fraction.valueOf(intElements[i][j]).simplify();
			}
		}
	}

	public Fraction get(int row, int column) {
		if (row <= 0 || column <= 0) {
			throw new IllegalArgumentException("Invalid matrix dimensions");
		}

		if (row > this.rows || column > this.columns) {
			throw new IllegalArgumentException("Invalid matrix dimensions");
		}
		return elements[row][column];
	}

	public void set(int row, int column, Fraction element) {
		if (row <= 0 || column <= 0) {
			throw new IllegalArgumentException("Invalid matrix dimensions");
		}

		if (row > this.rows || column > this.columns) {
			throw new IllegalArgumentException("Invalid matrix dimensions");
		}
		elements[row][column] = element.simplify();
	}

	public void set(int row, int column, int element) {
		if (row <= 0 || column <= 0) {
			throw new IllegalArgumentException("Invalid matrix dimensions");
		}

		if (row > this.rows || column > this.columns) {
			throw new IllegalArgumentException("Invalid matrix dimensions");
		}
		elements[row][column] = Fraction.valueOf(element).simplify();
	}

	public Matrix add(int n) {
		return add(this, n);
	}

	public static Matrix add(Matrix m, int n) {
		int rows = m.getNumberOfRows();
		int columns = m.getNumberOfColumns();
		Fraction[][] elements = m.getElements();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; i < columns; j++) {
				elements[i][j] = Fraction.add(elements[i][j], n).simplify();
			}
		}
		return new Matrix(rows, columns, elements);
	}

	public Matrix add(Fraction f) {
		return add(this, f);
	}

	public static Matrix add(Matrix m, Fraction f) {
		int rows = m.getNumberOfRows();
		int columns = m.getNumberOfColumns();
		Fraction[][] elements = m.getElements();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; i < columns; j++) {
				elements[i][j] = Fraction.add(elements[i][j], f).simplify();
			}
		}
		return new Matrix(rows, columns, elements);
	}

	public Matrix add(Matrix m) {

	}

	public static Matrix add(Matrix m1, Matrix m2) {

	}

	public Matrix subtract(int n) {
		return subtract(this, n);
	}

	public static Matrix subtract(Matrix m, int n) {
		int rows = m.getNumberOfRows();
		int columns = m.getNumberOfColumns();
		Fraction[][] elements = m.getElements();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; i < columns; j++) {
				elements[i][j] = Fraction.subtract(elements[i][j], n).simplify();
			}
		}
		return new Matrix(rows, columns, elements);
	}

	public Matrix subtract(Fraction f) {
		return subtract(this, f);
	}

	public static Matrix subtract(Matrix m, Fraction f) {
		int rows = m.getNumberOfRows();
		int columns = m.getNumberOfColumns();
		Fraction[][] elements = m.getElements();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; i < columns; j++) {
				elements[i][j] = Fraction.add(elements[i][j], f).simplify();
			}
		}
		return new Matrix(rows, columns, elements);
	}

	public Matrix subtract(Matrix m) {

	}

	public static Matrix subtract(Matrix m1, Matrix m2) {

	}

	public Matrix multiply(int n) {
		return multiply(this, n);
	}

	public static Matrix multiply(Matrix m, int n) {
		int rows = m.getNumberOfRows();
		int columns = m.getNumberOfColumns();
		Fraction[][] elements = m.getElements();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; i < columns; j++) {
				elements[i][j] = Fraction.multiply(elements[i][j], n).simplify();
			}
		}
		return new Matrix(rows, columns, elements);
	}

	public Matrix multiply(Fraction f) {
		return multiply(this, f);
	}

	public static Matrix multiply(Matrix m, Fraction f) {
		int rows = m.getNumberOfRows();
		int columns = m.getNumberOfColumns();
		Fraction[][] elements = m.getElements();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; i < columns; j++) {
				elements[i][j] = Fraction.add(elements[i][j], f).simplify();
			}
		}
		return new Matrix(rows, columns, elements);
	}

	public Matrix multiply(Matrix m) {

	}

	public static Matrix multiply(Matrix m1, Matrix m2) {

	}

	public Matrix divide(int n) {
		return divide(this, n);
	}

	public static Matrix divide(Matrix m, int n) {
		int rows = m.getNumberOfRows();
		int columns = m.getNumberOfColumns();
		Fraction[][] elements = m.getElements();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; i < columns; j++) {
				elements[i][j] = Fraction.divide(elements[i][j], n).simplify();
			}
		}
		return new Matrix(rows, columns, elements);
	}

	public Matrix divide(Fraction f) {
		return divide(this, f);
	}

	public static Matrix divide(Matrix m, Fraction f) {
		int rows = m.getNumberOfRows();
		int columns = m.getNumberOfColumns();
		Fraction[][] elements = m.getElements();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; i < columns; j++) {
				elements[i][j] = Fraction.add(elements[i][j], f).simplify();
			}
		}
		return new Matrix(rows, columns, elements);
	}

	public Matrix divide(Matrix m) {

	}

	public static Matrix divide(Matrix m1, Matrix m2) {

	}

	public Matrix inverse() {

	}

	public static Matrix inverse(Matrix m) {

	}
}
