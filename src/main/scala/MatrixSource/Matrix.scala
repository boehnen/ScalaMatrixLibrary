package MatrixSource
/*
Class: Matrix

Methods:
  def rows: Int - getter for _rows
  def cols: Int - getter for _cols
  def data_= (value: Array[Array[Int]]): Unit - setter for _data
  def transpose: Matrix - does the transpose operation
    on the matrix; returns a new matrix
*/
class Matrix(val _rows: Int, val _cols: Int) {
  private var _data: Array[Array[Double]] = Array.ofDim(_rows, _cols)

  def rows: Int = _rows
  def cols: Int = _cols

  def data:Array[Array[Double]] = _data
  def data_= (value: Array[Array[Double]]): Unit = {
    if(value.length != _rows) throw new MatrixException("Incorrect X Dimensions")
    for (list <- value) if (list.length != _cols) throw new MatrixException("Incorrect Y Dimensions")

    _data = value
  }

  def transpose: Matrix = {
    val newData: Array[Array[Double]] = Array.ofDim(_cols, _rows)

    for (i <- 0 until _rows)
      for (j <- 0 until _cols)
        newData(j)(i) = _data(i)(j)

    val matrix = new Matrix(_cols, _rows)
    matrix.data_=(newData)
    matrix
  }

  def traverse (func: Double => Unit): Unit = {
    for(i <- 0 until _rows)
      for(j <- 0 until _cols)
        func(_data(i)(j))
  }


  def transform (func: Double => Double): Matrix = {
    val newData: Array[Array[Double]] = Array.ofDim(_rows, _cols)

    for(i <- 0 until _rows)
      for(j <- 0 until _cols)
        newData(i)(j) = func(_data(i)(j))

    val newMatrix = new Matrix(_rows, _cols)
    newMatrix.data_=(newData)
    newMatrix
  }

  def printMat(): Unit = for (list <- _data) { for (item <- list) print("[" + item + "]"); println()}

  def index(x: Int, y: Int): Double = { _data(x)(y) }
}

object Matrix {
  def add(lvalue: Matrix, rvalue: Matrix): Matrix = {
    if (lvalue.rows != rvalue.rows || lvalue.cols != rvalue.cols)
      throw new MatrixException("Cannot add matrices of differing degrees")

    val newData: Array[Array[Double]] = Array.ofDim(lvalue.rows, lvalue.cols)

    for(i <- 0 until lvalue.rows)
      for (j <- 0 until lvalue.cols)
        newData(i)(j) = lvalue.data(i)(j) + rvalue.data(i)(j)

    val newMatrix: Matrix = new Matrix(lvalue.rows, lvalue.cols)
    newMatrix.data_=(newData)
    newMatrix
  }

  def subtract(lvalue: Matrix, rvalue: Matrix): Matrix = {
    if (lvalue.rows != rvalue.rows || lvalue.cols != rvalue.cols)
      throw new MatrixException("Cannot add matrices of differing degrees")

    val newData: Array[Array[Double]] = Array.ofDim(lvalue.rows, lvalue.cols)

    for(i <- 0 until lvalue.rows)
      for (j <- 0 until lvalue.cols)
        newData(i)(j) = lvalue.data(i)(j) - rvalue.data(i)(j)

    val newMatrix: Matrix = new Matrix(lvalue.rows, lvalue.cols)
    newMatrix.data_=(newData)
    newMatrix
  }

  def crossProduct(lvalue: Matrix, rvalue: Matrix): Matrix = {
    if (lvalue.cols != rvalue.rows) throw new MatrixException("Column on lvalue must equal row on rvalue")

    val newData: Array[Array[Double]] = Array.ofDim(lvalue.rows, rvalue.cols)

    for (i <- 0 until lvalue.rows)
      for (j <- 0 until rvalue.cols)
        for (k <- 0 until lvalue.cols)
          newData(i)(j) += lvalue.data(i)(k) * rvalue.data(k)(j)

    val newMatrix: Matrix = new Matrix(lvalue.rows, rvalue.cols)
    newMatrix.data_=(newData)
    newMatrix
  }

  def dotProduct(lvalue: Matrix, rvalue: Matrix): Matrix = {
    if (lvalue.rows != rvalue.rows || lvalue.cols != rvalue.cols)
      throw new MatrixException("Cannot perform dot product on matrices of differing degrees")

    Matrix.crossProduct(lvalue, rvalue.transpose)
  }

  def areEqual(lvalue: Matrix, rvalue: Matrix): Boolean = {
    var isEqual: Boolean = true

    if(lvalue.rows != rvalue.rows || lvalue.cols != rvalue.cols) isEqual = false
    else
      for (i <- 0 until lvalue.rows)
        for (j <- 0 until lvalue.cols)
          if (lvalue.data(i)(j) != rvalue.data(i)(j)) isEqual = false

    isEqual
  }
}
