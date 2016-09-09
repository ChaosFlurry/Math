package com.math.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.math.fraction.Fraction;

public class Matrix {
	// test
	// transposition of matrices
	// create blank (all zero) matrix of x by y columns and rows?
	// parsing a matrix
	// printing a matrix
	// comparison of matrices
	// inverse of matrices
	// determinants of matrices

	// TODO have more descriptive IllegalArgumentException messages
	// TODO find some way to implement Laplace expansions

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
		this.elements = new Fraction[rows][columns];
		
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
			try {
				intElements[i] = Arrays.copyOf(elements[i], columns);
			} catch (IndexOutOfBoundsException ex) {
				intElements[i] = blank;
			}
		}

		// convert ints to fractions, transfer values to fraction array
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				this.elements[i][j] = Fraction.valueOf(intElements[i][j]).simplify();
			}
		}
	}

	public Fraction get(int row, int column) {
		if (row < 1 || column < 1) {
			throw new IllegalArgumentException("Invalid matrix dimensions");
		}

		if (row > this.rows || column > this.columns) {
			throw new IllegalArgumentException("Invalid matrix dimensions");
		}
		// arrays are zero-indexed
		return elements[row - 1][column - 1];
	}

	public void set(int row, int column, Fraction element) {
		if (row < 1 || column < 1) {
			throw new IllegalArgumentException("Invalid matrix dimensions");
		}

		if (row > this.rows || column > this.columns) {
			throw new IllegalArgumentException("Invalid matrix dimensions");
		}
		// arrays are zero-indexed
		elements[row - 1][column - 1] = element.simplify();
	}

	public void set(int row, int column, int element) {
		if (row < 1 || column < 1) {
			throw new IllegalArgumentException("Invalid matrix dimensions");
		}

		if (row > this.rows || column > this.columns) {
			throw new IllegalArgumentException("Invalid matrix dimensions");
		}
		// arrays are zero-indexed
		elements[row - 1][column - 1] = Fraction.valueOf(element).simplify();
	}

	public Fraction[] getRow(int row) {
		if (row < 1 || row > rows) {
			throw new IllegalArgumentException("Invalid matrix dimensions");
		}
		// arrays are zero-indexed
		return elements[row - 1];
	}

	public void setRow(int row, Fraction[] elements) {
		if (row < 1 || row > rows) {
			throw new IllegalArgumentException("Invalid matrix dimensions");
		}

		if (elements.length != columns) {
			throw new IllegalArgumentException("Invalid matrix dimensions");
		}
		// arrays are zero-indexed
		this.elements[row - 1] = elements;
	}

	public Fraction[] getColumn(int column) {
		if (column < 1 || column > columns) {
			throw new IllegalArgumentException("Invalid matrix Dimensions");
		}

		// number of elements in a column == number of rows
		Fraction[] result = new Fraction[rows];
		for (int i = 0; i < rows; i++) {
			// arrays are zero-indexed
			result[i] = elements[i][column - 1];
		}
		return result;
	}

	public void setColumn(int column, Fraction[] elements) {
		if (column < 1 || column > columns) {
			throw new IllegalArgumentException("Invalid matrix Dimensions");
		}

		if (elements.length != rows) {
			throw new IllegalArgumentException("Invalid matrix dimensions");
		}

		for (int i = 0; i < rows; i++) {
			set(i + 1, column + 1, elements[i]);
		}
	}
	
	public void swapRow(int r1, int r2) {
		if (r1 < 1 || r2 < 1 || r1 > rows || r2 > rows) {
			throw new IllegalArgumentException("Beyond row dimensions");
		}
		
		Fraction[] temp = getRow(r1);
		setRow(r1, getRow(r2));
		setRow(r2, temp);
	}
	
	public void swapColumn(int c1, int c2) {
		if (c1 < 1 || c2 < 1 || c1 > columns || c2 > columns) {
			throw new IllegalArgumentException("Beyond column dimensions");
		}
		
		Fraction[] temp = getColumn(c1);
		setRow(c1, getColumn(c2));
		setRow(c2, temp);
	}
	
	public Fraction[] addRow(int r1, int r2) {
		if (r1 < 1 || r1 > rows) {
			throw new IllegalArgumentException("Invalid row");
		}
		if (r2 < 1 || r2 > rows) {
			throw new IllegalArgumentException("Invalid row");
		}
		
		Fraction[] result = new Fraction[columns];
		for (int i = 0; i < columns; i++) {
			result[i] = elements[r1 - 1][i].add(elements[r2 - 1][i]).simplify();
		}
		return result;
	}
	
	public Fraction[] subtractRow(int r1, int r2) {
		if (r1 < 1 || r1 > rows) {
			throw new IllegalArgumentException("Invalid row");
		}
		if (r2 < 1 || r2 > rows) {
			throw new IllegalArgumentException("Invalid row");
		}
		
		Fraction[] result = new Fraction[columns];
		for (int i = 0; i < columns; i++) {
			result[i] = elements[r1 - 1][i].subtract(elements[r2 - 1][i]).simplify();
		}
		return result;
	}
	
	public Fraction[] multiplyRow(int row, Fraction f) {
		if (row < 1 || row > rows) {
			throw new IllegalArgumentException("Invalid row");
		}
		
		Fraction[] result = new Fraction[rows];
		for (int i = 0; i < rows; i++) {
			result[i] = elements[row - 1][i].multiply(f).simplify();
		}
		return result;
	}
	
	public Fraction[] multiplyColumn(int column, Fraction f) {
		if (column < 1 || column > columns) {
			throw new IllegalArgumentException("Invalid row");
		}
		
		Fraction[] result = new Fraction[rows];
		for (int i = 0; i < rows; i++) {
			result[i] = elements[i][column - 1].multiply(f).simplify();
		}
		return result;
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
		return add(this, m);
	}

	public static Matrix add(Matrix m1, Matrix m2) {
		if (m1.getNumberOfRows() != m2.getNumberOfRows() || m1.getNumberOfColumns() != m2.getNumberOfColumns()) {
			throw new IllegalArgumentException("Matrix dimensions do not match");
		}

		int rows = m1.getNumberOfRows();
		int columns = m1.getNumberOfColumns();
		Fraction[][] result = new Fraction[rows][columns];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				result[i][j] = Fraction.add(m1.get(i, j), m2.get(i, j));
			}
		}
		return new Matrix(rows, columns, result);
	}

	public static Fraction[] add(Fraction[] a, Fraction[] b) {
		if (a.length != b.length) {
			throw new IllegalArgumentException("Array lengths are not the same");
		}

		Fraction[] result = new Fraction[a.length];
		for (int i = 0; i < a.length; i++) {
			result[i] = a[i].add(b[i]);
		}
		return result;
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
		return subtract(this, m);
	}

	public static Matrix subtract(Matrix m1, Matrix m2) {
		if (m1.getNumberOfRows() != m2.getNumberOfRows() || m1.getNumberOfColumns() != m2.getNumberOfColumns()) {
			throw new IllegalArgumentException("Matrix dimensions do not match");
		}

		int rows = m1.getNumberOfRows();
		int columns = m1.getNumberOfColumns();
		Fraction[][] result = new Fraction[rows][columns];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				result[i][j] = Fraction.subtract(m1.get(i, j), m2.get(i, j));
			}
		}
		return new Matrix(rows, columns, result);
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
		return multiply(this, m);
	}

	public static Matrix multiply(Matrix m1, Matrix m2) {
		int rows = m1.getNumberOfRows();
		int columns = m2.getNumberOfColumns();

		if (rows != columns) {
			throw new IllegalArgumentException("Matrices cannot be multiplied");
		}

		Fraction[][] elements = new Fraction[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				elements[i][j] = Matrix.multiply(m1.getColumn(i), m2.getRow(j));
			}
		}
		return new Matrix(rows, columns, elements);
	}

	public static Fraction multiply(Fraction[] a, Fraction[] b) {
		if (a.length != b.length) {
			throw new IllegalArgumentException("Array lengths do not match");
		}

		if (a.length == 0 || b.length == 0) {
			return new Fraction(0, 1);
		}
		Fraction result = new Fraction(0, 1);
		for (int i = 0; i < a.length; i++) {
			result = Fraction.add(result, Fraction.multiply(a[i], b[i]));
		}
		return result;
	}

	public static Fraction sum(Fraction[] a) {
		if (a.length == 0) {
			return new Fraction(0, 1);
		}
		return Fraction.add(a);
	}

	public boolean hasInverse() {
		return hasInverse(this);
	}

	public static boolean hasInverse(Matrix m) {
		// only square matrices have an inverse
		if (m.getNumberOfRows() != m.getNumberOfColumns()) {
			return false;
		}
		// if the determinant of a square matrix is 0 it is singular
		if (determinant(m).equals(new Fraction(0, 1))) {
			return false;
		}
		return true;
	}

	public Matrix inverse() {
		return inverse(this);
	}

	public static Matrix inverse(Matrix m) {
		return null;
	}

	public Matrix minor(int row, int column) {
		return minor(row, column, this);
	}

	public static Matrix minor(int row, int column, Matrix m) {
		if (row != column) {
			throw new IllegalArgumentException("Matrix is not square");
		}

		if (row < 2) {
			throw new IllegalArgumentException("No minors exist for a 1 x 1 matrix");
		}

		// arrays are zero-indexed
		row -= 1;
		column -= 1;

		int rows = m.getNumberOfRows();
		int columns = m.getNumberOfColumns();
		Fraction[][] elements = m.getElements();
		Fraction[][] minor = new Fraction[rows - 1][column - 1];
		int minorRow = 0;
		int minorColumn = 0;

		for (int i = 0; i < rows; i++) {
			if (i == row)
				continue;
			for (int j = 0; j < columns; j++) {
				if (j == column)
					continue;
				minor[minorRow][minorColumn] = elements[i][j];
				minorColumn++;
			}
			minorRow++;
			minorColumn = 0;
		}
		return new Matrix(rows - 1, column - 1, minor);
	}

	public Fraction determinant() {
		return determinant(this);
	}

	public static Fraction determinant(Matrix m) {
		int rows = m.getNumberOfRows();
		int columns = m.getNumberOfColumns();
		if (rows != columns) {
			throw new IllegalArgumentException("Matrix is singular");
		}
		
		//1 = positive, -1 = negative
		int sign = 1;
		int pivotRow = 0;
		int pivotColumn = 0;
		
		Fraction[][] elements = m.getElements();
		
		// TODO matrix sizes 2 x 2 and 1 x 1 handled manually
		// TODO fix issues with row swaps
		
		//set pivot
		for (int c = pivotColumn; c < columns; c++) {
			Fraction[] column = new Fraction[columns - pivotColumn];
			int count = 0;
			for (int i = pivotRow; i < columns; i++) {
				column[count] = elements[i][c];
				count++;
			}
			
			boolean allColumnValuesAreZero = true;
			for (int i = 0; i < column.length; i++) {
				if (column[i].equals(Fraction.ZERO) == false) {
					allColumnValuesAreZero = false;
					break;
				}
			}
			
			if (allColumnValuesAreZero) {
				continue;
			}
			
			//set pivot
			Fraction pivot = elements[pivotRow][pivotColumn];
			System.out.println(pivot);
			
			//swap rows
			if (pivot.equals(Fraction.ZERO)) {
				for (int i = pivotRow + 1; i < rows; i++) {
					if (elements[i][pivotColumn].equals(Fraction.ZERO) == false) {
						Fraction[] temp = elements[i];
						elements[i] = elements[pivotRow];
						elements[pivotRow] = temp;
						sign *= -1;
						break;
					}
				}
			}
			
			//gaussian elimination
			for (int r = pivotRow + 1; r < rows; r++) {
				if (elements[r][pivotColumn].equals(Fraction.ZERO)) continue;
				
				Fraction coefficient = elements[r][pivotColumn];
				for (int i = 0; i < columns; i++) {
					elements[r][i] = elements[r][i].subtract(elements[pivotRow][i].divide(pivot).multiply(coefficient)).simplify();
				}
				for (int j = 0; j < rows; j++) {
					for (int k = 0; k < columns; k++) {
						System.out.print(j + "," + k + ": " + elements[j][k] + "    ");
					}
					System.out.println("\n");
				}
				System.out.println("\n");
			}
			pivotRow++;
			pivotColumn++;
		}
		
		//simplify
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				elements[i][j] = elements[i][j].simplify();
			}
		}

		//determinant = sum of elements on major diagonal
		Fraction determinant = Fraction.ONE;
		for (int i = 0; i < rows; i++) {
			determinant = determinant.multiply(elements[i][i]).simplify();
		}
		
		for (int j = 0; j < rows; j++) {
			for (int k = 0; k < columns; k++) {
				System.out.print(j + "," + k + ": " + elements[j][k] + "    ");
			}
			System.out.println("\n");
		}
		determinant = determinant.multiply(sign).simplify();
		return determinant;
	}
}
