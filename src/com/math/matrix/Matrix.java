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
		
		Matrix m1 = m;
		
		// TODO matrix sizes 2 x 2 and 1 x 1 handled manually
		
		Fraction pivot;
		for (int c = 0; c < columns; c++) {

			for (int r = pivotRow; r < rows; r++) {
				//check if pivot is 0
				if (c == pivotColumn && r == pivotRow) {
					pivot = m1.get(pivotRow + 1, pivotColumn + 1);
					Fraction[] currentColumn = m1.getColumn(r + 1);
					//check if all elements of the current column are zero
					boolean allColumnValuesAreZero = true;
					for (int i = pivotColumn; i < currentColumn.length; i++) {
						if (currentColumn[i].equals(Fraction.ZERO) == false) {
							allColumnValuesAreZero = false;
						}
					}
					
					if (allColumnValuesAreZero) {
						//proceed to next column
						break;
					}

					//if the pivot is 0, swap with a non-zero row
					if (pivot.equals(Fraction.ZERO)) {
						System.out.println("row swap");
						for (int i = pivotColumn; i < currentColumn.length; i++) {
							if ((currentColumn[i].equals(Fraction.ZERO)) == false) {
								m1.swapRow(pivotRow + 1, i + 1);
								sign *= -1;
							}
						}
					}
					
					//gaussian elimination is now possible
					continue;
				}
				
				//reset pivot
				pivot = m1.get(pivotRow + 1, pivotColumn + 1);
				
				//begin gaussian elimination
				
				//row has already been simplified if leading coefficient is zero
				if (m1.get(r + 1, c + 1).equals(Fraction.ZERO)) {
					System.out.println("leading coefficient is 0");
					continue;
				}
				
				//leading coefficient of row
				Fraction coefficient = m1.get(r + 1, c + 1);
				
				//modified pivot
				Fraction[] modified = m1.getRow(pivotRow + 1);
				for (int i = 0; i < modified.length; i++) {
					//pivot divided by itself
					modified[i] = modified[i].divide(pivot).simplify();
					modified[i] = modified[i].multiply(coefficient).simplify();
				}
				//row - pivot
				Fraction[] subtracted = m1.getRow(r + 1);
				for (int i = 0; i < subtracted.length; i++) {
					subtracted[i] = subtracted[i].subtract(modified[i]).simplify();
				}
				m1.setRow(r + 1, subtracted);
			}
			pivotRow++;
			pivotColumn++;
		}
		
		System.out.println("Matrix: ");
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				System.out.print(r + ", " + c + ": " + m1.get(r + 1, c + 1) + "    ");
			}
			System.out.println("\n");
		}
		Fraction determinant = Fraction.ONE;
		for (int i = 0; i < rows; i++) {
			determinant = determinant.multiply(m1.get(i + 1, i + 1));
		}
		return determinant;
		
		/*
		
		//columns:
		for (int c = 0; c < columns; c++) {
			//rows:
			for (int r = pivotRow; r < rows; r++) {
				//initial row of matrix
				if (r == pivotRow) {
					//assign pivot
					Fraction pivot = m1.get(pivotRow + 1, pivotColumn + 1);
					//if the pivot is zero
					if (m1.get(pivotRow + 1, pivotColumn + 1).simplify().equals(Fraction.ZERO)) {
						//check if all values in the column are zero
						boolean allValuesAreZero = true;
						for (int i = pivotRow + 1; i < columns; i++) {
							//if not all values in the column are zero
							if (m1.get(pivotRow + 1, i).simplify().equals(Fraction.ZERO) == false) {
								allValuesAreZero = false;
								//swap rows with non-zero value
								m1.swapRow(pivotRow + 1, i + 1);
								sign *= -1;
								//assign new pivot
								pivot = m1.get(pivotRow + 1, pivotColumn + 1);
							}
						}
						//if all values in the column are zero, the column is completely simplified
						if (allValuesAreZero) {
							//proceed to next column
							pivotRow++;
							pivotColumn++;
							break;
						}
					}
				} else {
					//if leading coefficient of row is 0, row is simplified
					if (m1.get(r, pivotColumn + 1).simplify().equals(Fraction.ZERO)) {
						pivotRow++;
						pivotColumn++;
						break;
					}
					//copy of original pivot row (to be restored later)
					Fraction[] pivotCopy = m1.getRow(pivotRow + 1);
					//set pivot leading coefficient to 1
					m1.multiplyRow(pivotRow + 1, m1.getElements()[pivotRow][0].reciprocal());
					//make leading coefficient of row equal to pivot
					m1.setRow(pivotRow + 1, m1.multiplyRow(pivotRow + 1, elements[r][0]));
					//subtract pivot from row
					m1.setRow(r + 1, m1.subtractRow(r, pivotRow + 1));
					//restore pivot row
					m1.setRow(pivotRow + 1, pivotCopy);
				}
			}
		}
		System.out.println("Matrix: ");
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				System.out.print(r + ", " + c + ": " + m1.get(r + 1, c + 1) + "    ");
			}
			System.out.println("\n");
		}
		Fraction determinant = Fraction.ONE;
		for (int i = 0; i < rows; i++) {
			determinant = determinant.multiply(m1.get(i + 1, i + 1));
		}
		return determinant;
		*/
		/*for (int i = 0; i < rows; i++) {
		for (int j = pivotRow; j < rows; j++) {
			Fraction pivot = m1.get(pivotRow, pivotColumn);
			if (pivot.equals(Fraction.ZERO)) {
				
				boolean allAreZero = true;
				for (int k = pivotColumn; k < columns; k++) {
					//if pivot is zero
					//if all are zero
					if (m1.get(pivotRow, k).equals(Fraction.ZERO) == false) {
						allAreZero = false;
						
					}
				}
		}
		pivotRow++;
		pivotColumn++;
	}*/
		
		//n! operations
		/*for (int i = pivotColumn; i < elements.length; i++) {
			for (int j = pivotRow; j < elements.length; j++) {
				Fraction pivot = elements[i][j];
				if (pivot.equals(Fraction.ZERO)) {
					
				}
			}
		}*/
		
		/*
		if (rows == 2) {
			Fraction a = elements[0][0];
			Fraction b = elements[0][1];
			Fraction c = elements[1][0];
			Fraction d = elements[1][1];
			return a.multiply(d).subtract(b.multiply(c));
		} else {
			// sum all minor (of minor) 2 x 2 matrices

			Fraction determinant = new Fraction(0, 1);
			List<Matrix> minors = new ArrayList<Matrix>();
			List<Fraction> a = new ArrayList<Fraction>();
			List<List<Matrix>> finalMinors = new ArrayList<List<Matrix>>();
			
			//4 x 4 matrix
			for (int i = 0; i < rows; i++) {
				a.add(elements[0][i]);
				minors.add(minor(0, i + 1, m));
			}
			finalMinors.add(minors);
			//4 minors in list
			for (int i = 0; i < 4; i++) {
				minors.set(i, minor(0, i + 1, minors.get(i)));
			}
			finalMinors.add(minors);
			//3 minors in list
		*/
		/*
			List<List<Matrix>> finalMinors = new ArrayList<List<Matrix>>();

			for (int i = 0; i < columns; i++) {
				minors.add(minor(0, i + 1, m));
			}

			// while the matrix is fully simplified (a 2 x 2 matrix)
			while (minors.get(0).getNumberOfRows() != 2) {
				List<Matrix> temp = new ArrayList<Matrix>();
				for (int i = 0; i < minors.size(); i++) {
					temp.add(minor(0, i, minors.get(i)));
				}
				minors = temp;
			}
			finalMinors.add(minors);
			*/
	}
}
