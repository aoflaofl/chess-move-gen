package com.spamalot.chess.base;

/**
 * A Square Object. Meant only to communicate square information during
 * non-performance intensive operations.
 * 
 * @author gej
 *
 */
public final class SquareUtil {
  /** Hold generated Squares. */
  private static SquareUtil[][] vals = new SquareUtil[8][8];

  /** The square's File number. */
  private int file;

  /** The square's rank number. */
  private int rank;

  /**
   * No public instantiation.
   * 
   * @param file2
   *          File number
   * @param rank2
   *          Rank number
   */
  private SquareUtil(final int file2, final int rank2) {
    this.file = file2;
    this.rank = rank2;
  }

  /**
   * Get the file.
   * 
   * @return the File.
   */
  public int getFile() {
    return this.file;
  }

  /**
   * Get the rank.
   * 
   * @return the Rank.
   */
  public int getRank() {
    return this.rank;
  }

  /**
   * Retrieve Square object for this file and rank.
   * 
   * @param file
   *          the File
   * @param rank
   *          the Rank
   * @return Square Object for this file and rank.
   */
  static SquareUtil valueOf(final int file, final int rank) {
    SquareUtil ret = vals[file][rank];
    if (ret == null) {
      ret = new SquareUtil(file, rank);
      vals[file][rank] = ret;
    }
    return ret;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("SquareUtil [file=").append(file).append(", rank=").append(rank).append(']');
    return builder.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + file;
    result = prime * result + rank;
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    SquareUtil other = (SquareUtil) obj;
    if (file != other.file) {
      return false;
    }
    if (rank != other.rank) {
      return false;
    }
    return true;
  }
}
