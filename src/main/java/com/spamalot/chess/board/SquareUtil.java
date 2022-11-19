package com.spamalot.chess.board;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * A Square Object. Meant only to communicate square information during
 * non-performance intensive operations.
 *
 * @author gej
 *
 */
public class SquareUtil {
  /** Hold generated Squares. */
  private static final SquareUtil[][] vals = new SquareUtil[8][8];

  /** The square's File number. */
  private int file;

  /** The square's rank number. */
  private int rank;

  /**
   * No public instantiation.
   *
   * @param file2 File number
   * @param rank2 Rank number
   */
  private SquareUtil(int file2, int rank2) {

    file = file2;
    rank = rank2;
  }

  /**
   * Get the file.
   *
   * @return the File.
   */
  public int getFile() {
    return file;
  }

  /**
   * Get the rank.
   *
   * @return the Rank.
   */
  public int getRank() {
    return rank;
  }

  /**
   * Retrieve Square object for this file and rank.
   *
   * @param file the File
   * @param rank the Rank
   * @return Square Object for this file and rank.
   */
  public static SquareUtil valueOf(int file, int rank) {
    checkArgument(file >= 0 && file <= 7, "File is %s.  Must be between 0 and 7 inclusive.", file);
    checkArgument(rank >= 0 && rank <= 7, "Rank is %s.  Must be between 0 and 7 inclusive.", rank);

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
    int prime = 11;
    int result = 1;
    result = prime * result + file;
    return prime * result + rank;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    SquareUtil other = (SquareUtil) obj;
    if (file != other.file) {
      return false;
    }
    return rank == other.rank;
  }
}
