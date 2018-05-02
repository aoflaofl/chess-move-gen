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
}
