
package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Tiffany Tang
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *            The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+
  /** Properties of a matrix, height, width and information stored. */
  int cols;
  int rows;
  T defaultVal;
  T[][] storage;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param width
   *               The width of the matrix.
   * @param height
   *               The height of the matrix.
   * @param def
   *               The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *                                    If either the width or height are
   *                                    negative.
   */
  @SuppressWarnings("unchecked")
  public MatrixV0(int width, int height, T def) throws NegativeArraySizeException {
    if (width < 0 || height < 0) {
      throw new NegativeArraySizeException();
    } // if
    cols = width;
    rows = height;
    defaultVal = def;
    storage = (T[][]) new Object[height][width];
    for (int n = 0; n < rows; n++) {
      for (int m = 0; m < cols; m++) {
        storage[n][m] = def;
      } // for
    } // for
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param width
   *               The width of the matrix.
   * @param height
   *               The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *                                    If either the width or height are
   *                                    negative.
   */
  public MatrixV0(int width, int height) throws NegativeArraySizeException {
    this(width, height, null);
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *            The row of the element.
   * @param col
   *            The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *                                   If either the row or column is out of
   *                                   reasonable bounds.
   */
  public T get(int row, int col) throws IndexOutOfBoundsException {
    if (notValid(row, col)) {
      throw new IndexOutOfBoundsException();
    } // if
    return this.storage[row][col];
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *            The row of the element.
   * @param col
   *            The column of the element.
   * @param val
   *            The value to set.
   *
   * @throws IndexOutOfBoundsException
   *                                   If either the row or column is out of
   *                                   reasonable bounds.
   */
  public void set(int row, int col, T val) {
    if (notValid(row, col)) {
      throw new IndexOutOfBoundsException();
    } // if
    this.storage[row][col] = val;
  } // set(int, int, T)

  /**
   * Determine if the row and col is valid positions.
   *
   * @param row
   * @param col
   * @return true if it is not valid, false otherwise.
   */
  public boolean notValid(int row, int col) {
    if (row >= this.height() || col >= this.height()
        || row < 0 || col < 0) {
      return true;
    } // if
    return false;
  } // isValid

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.rows;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.cols;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *            The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *                                   If the row is negative or greater than the
   *                                   height.
   */
  @SuppressWarnings("unchecked")
  public void insertRow(int row) {
    if (row > this.height() || row < 0) {
      throw new IndexOutOfBoundsException();
    } // if
    T[][] hold = (T[][]) new Object[row++][this.cols];
    for (int n = 0; n < hold.length; n++) {
      for (int m = 0; m < hold[0].length; m++) {
        if (n == this.rows) {
          hold[n][m] = this.defaultVal;
        } else {
          hold[n][m] = this.storage[n][m];
        } // if-else
      } // for
    } // for
    this.storage = hold;
    this.rows++;
    for (int n = this.rows - 1; n >= row; n--) {
      for (int m = 0; m < this.cols; m++) {
        if (n == row) {
          this.storage[n][m] = this.defaultVal;
          return;
        } // if
        this.storage[n][m] = this.storage[n - 1][m];
      } // for
    } // for
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *             The number of the row to insert.
   * @param vals
   *             The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *                                   If the row is negative or greater than the
   *                                   height.
   * @throws ArraySizeException
   *                                   If the size of vals is not the same as the
   *                                   width of the matrix.
   */
  @SuppressWarnings("unchecked")
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    if (row > this.height() || row < 0) {
      throw new IndexOutOfBoundsException();
    } // if
    T[][] hold = (T[][]) new Object[row++][this.cols];
    for (int n = 0; n < hold.length; n++) {
      for (int m = 0; m < hold[0].length; m++) {
        if (n == this.rows) {
          hold[n][m] = this.defaultVal;
        } else {
          hold[n][m] = this.storage[n][m];
        } // if-else
      } // for
    } // for
    this.storage = hold;
    this.rows++;
    for (int n = this.rows - 1; n >= row; n--) {
      for (int m = 0; m < this.cols; m++) {
        if (n == row) {
          this.storage[n] = vals;
          return;
        } // if
        this.storage[n][m] = this.storage[n - 1][m];
      } // for
    } // for
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *            The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *                                   If the column is negative or greater than
   *                                   the width.
   */
  @SuppressWarnings("unchecked")
  public void insertCol(int col) {
    if (col > this.width() || col < 0) {
      throw new IndexOutOfBoundsException();
    } // if
    T[][] hold = (T[][]) new Object[this.rows][col++];
    for (int n = 0; n < hold.length; n++) {
      for (int m = 0; m < hold[0].length; m++) {
        if (n == this.cols) {
          hold[n][m] = this.defaultVal;
        } else {
          hold[n][m] = this.storage[n][m];
        } // if-else
      } // for
    } // for
    this.storage = hold;
    this.cols++;
    for (int n = 0; n < this.rows; n++) {
      for (int m = this.cols - 1; m >= col; m--) {
        if (m == col) {
          this.storage[n][m] = this.defaultVal;
          return;
        } // if
        this.storage[n][m] = this.storage[n][m - 1];
      } // for
    } // for
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *             The number of the column to insert.
   * @param vals
   *             The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *                                   If the column is negative or greater than
   *                                   the width.
   * @throws ArraySizeException
   *                                   If the size of vals is not the same as the
   *                                   height of the matrix.
   */
  @SuppressWarnings("unchecked")
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    if (col > this.width() || col < 0) {
      throw new IndexOutOfBoundsException();
    } // if
    T[][] hold = (T[][]) new Object[this.rows][col++];
    for (int n = 0; n < hold.length; n++) {
      for (int m = 0; m < hold[0].length; m++) {
        if (n == this.cols) {
          hold[n][m] = this.defaultVal;
        } else {
          hold[n][m] = this.storage[n][m];
        } // if-else
      } // for
    } // for
    this.storage = hold;
    this.cols++;
    for (int n = 0; n < this.rows; n++) {
      for (int m = this.cols - 1; m >= col; m--) {
        if (m == col) {
          this.storage[n][m] = vals[n];
          return;
        } else {
          this.storage[n][m] = this.storage[n][m - 1];
        } // if-else
      } // for
    } // for
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *            The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *                                   If the row is negative or greater than or
   *                                   equal to the height.
   */
  public void deleteRow(int row) throws IndexOutOfBoundsException {
    if (row < 0 || row > this.rows) {
      throw new IndexOutOfBoundsException();
    } // if
    for (int n = row; n < this.rows; n++) {
      for (int m = 0; m < this.cols; m++) {
        if (n == this.rows - 1) {
          this.storage[n] = null;
        } else {
          this.storage[n][m] = this.storage[n + 1][m];
        } // if-else
      } // for
    } // for
    this.rows--;
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *            The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *                                   If the column is negative or greater than
   *                                   or equal to the width.
   */
  public void deleteCol(int col) {
    if (col < 0 || col > this.cols) {
      throw new IndexOutOfBoundsException();
    } // if
    for (int n = 0; n < this.rows; n++) {
      for (int m = col; m < this.cols; m++) {
        if (m == this.cols - 1) {
          this.storage[n][m] = null;
        } else {
          this.storage[n][m] = this.storage[n][m + 1];
        } // if-else
      } // for
    } // for
    this.cols--;
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *                 The top edge / row to start with (inclusive).
   * @param startCol
   *                 The left edge / column to start with (inclusive).
   * @param endRow
   *                 The bottom edge / row to stop with (exclusive).
   * @param endCol
   *                 The right edge / column to stop with (exclusive).
   * @param val
   *                 The value to store.
   *
   * @throw IndexOutOfBoundsException
   *        If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
      T val) {
    if (notValid(endRow, endCol)
        || notValid(startRow, startCol)) {
      throw new IndexOutOfBoundsException();
    } // if
    for (int n = startRow; n < endRow; n++) {
      for (int m = startCol; m < endCol; m++) {
        this.storage[n][m] = val;
      } // for
    } // for
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *                 The row to start with (inclusive).
   * @param startCol
   *                 The column to start with (inclusive).
   * @param deltaRow
   *                 How much to change the row in each step.
   * @param deltaCol
   *                 How much to change the column in each step.
   * @param endRow
   *                 The row to stop with (exclusive).
   * @param endCol
   *                 The column to stop with (exclusive).
   * @param val
   *                 The value to store.
   *
   * @throw IndexOutOfBoundsException
   *        If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) {
    if (notValid(startRow, startCol)
        || notValid(endRow, endCol)) {
      throw new IndexOutOfBoundsException();
    } // if
    for (int n = startRow; n < endRow; n += deltaRow) {
      for (int m = startCol; m < endCol; m += deltaCol) {
        this.storage[n][m] = val;
      } // for
    } // for
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Matrix clone() {
    T[][] hold = (T[][]) new Object[this.rows][this.cols];
    for (int n = 0; n < hold.length; n++) {
      for (int m = 0; m < hold[0].length; m++) {
        hold[n][m] = this.storage[n][m];
      } // for
    } // for
    MatrixV0<T> cloneM = new MatrixV0(this.cols, this.rows, this.defaultVal);
    cloneM.storage = hold;
    return cloneM;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *              The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   *         height, and equal elements; false otherwise.
   */
  @SuppressWarnings("unchecked")
  public boolean equals(Object other) {
    if (other == null
        || !(other instanceof MatrixV0)) {
      return false;
    } else {
      MatrixV0<T> otherM = (MatrixV0<T>) other;
      if (this.width() != otherM.width()
          || this.height() != otherM.height()) {
        return false;
      } else {
        for (int n = 0; n < this.rows; n++) {
          for (int m = 0; m < this.cols; m++) {
            if (this.storage[n][m] != otherM.storage[n][m]) {
              return false;
            } // if
          } // for
        } // for
      } // if-else
    }
    return true;
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
