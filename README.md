# ScalaMatrixLibrary

A matrix library for Scala that supports various matrix operations

## Documentation

**Transpose: Matrix** - this method transposes the Matrix, turning all rows into columns and columns into rows; these changes are returned in a new Matrix

**Traverse (visit: Double => Unit): Unit** - calls a visit function over every element in the Matrix

**Transform (visit: Double => Double): Matrix** - creates a new matrix with the values in the current matrix transformed by visit

**RowSwap (rowA: Int, rowB: Int): Matrix** - swaps the elements of two rows; these changes are returned in a new Matrix

**ReduceSum: Matrix** - adds up the values of all elements in each rows, making and returning a new Matrix with one column

**PrintMat: Unit** - prints the entire matrix to stdout

**Index (row: Int, col: Int): Double** - returns the value of the data stored at the given indices

**GetSize: Array\[Int]** - returns the size of the Matrix as an Array

**Add (lvalue: Matrix, rvalue: Matrix): Matrix** - adds two matrices together, returning the resulting matrix.

**Subtract (lvalue: Matrix, rvalue: Matrix): Matrix** - subtracts rvalue matrix from lvalue matrix, returning the resulting matrix.

**Multiply (lvalue: Matrix, rvalue: Matrix): Matrix** - multiplies two matrices together, returning the cross product of the matrices.

**AreEqual (lvalue: Matrix, rvalue: Matrix): Boolean** - evaluates whether lvalue and rvalue are identical matrices and returns the verdict.

**Identity (dim: Int): Matrix** - returns an identity matrix of size dim.
