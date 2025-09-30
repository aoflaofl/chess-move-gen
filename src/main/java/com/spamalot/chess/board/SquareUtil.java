package com.spamalot.chess.board;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * A Square Object. Meant only to communicate square information during
 * non-performance intensive operations. This class utilizes a flyweight pattern
 * to reduce the memory footprint.
 */
public final class SquareUtil {
  /** Hold generated Squares. */
  private static final SquareUtil[][] vals = new SquareUtil[8][8];

  /** The square's file number. */
  private final int file;

  /** The square's rank number. */
  private final int rank;

  /**
   * Private constructor to prevent instantiation.
   *
   * @param file the file number
   * @param rank the rank number
   */
  private SquareUtil(int file, int rank) {
    this.file = file;
    this.rank = rank;
  }

  /**
   * Get the file.
   *
   * @return the file number
   */
  public int getFile() {
    return file;
  }

  /**
   * Get the rank.
   *
   * @return the rank number
   */
  public int getRank() {
    return rank;
  }

  /**
   * Retrieve Square object for this file and rank.
   *
   * @param file the file number
   * @param rank the rank number
   * @return Square Object for this file and rank.
   */
  public static SquareUtil valueOf(int file, int rank) {
    checkArgument(file >= 0 && file <= 7, "File is %s. Must be between 0 and 7 inclusive.", file);
    checkArgument(rank >= 0 && rank <= 7, "Rank is %s. Must be between 0 and 7 inclusive.", rank);

    SquareUtil square = vals[file][rank];
    if (square == null) {
      square = new SquareUtil(file, rank);
      vals[file][rank] = square;
    }
    return square;
  }

  @Override
  public String toString() {
    return String.format("SquareUtil [file=%d, rank=%d]", file, rank);
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
  public boolean equals(Object obj) {
    // With proper flyweight implementation, only reference equality is needed
    return this == obj;
  }
}
