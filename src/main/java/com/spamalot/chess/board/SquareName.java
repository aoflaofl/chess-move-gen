package com.spamalot.chess.board;

import javax.annotation.Nullable;

/**
 * Utility functions for handling square names.
 * 
 * @author gej
 *
 */
public final class SquareName {
  /** Map file number to file letter. */
  private static final char[] FILE_LETTERS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };

  /** Private Constructor. */
  private SquareName() {
  }

  /** Map square number to name. */
  private static String[] sqNames = new String[128];

  static {
    for (int i = 0; i < 128; i++) {
      sqNames[i] = SquareName.fromBoard0x88number(i);
    }
  }

  /**
   * Get the square's name from the Board0x88 square number.
   * 
   * @param s
   *            the square's Board0x88 number
   * @return the name of the square.
   */
  public static String toName(final int s) {
    return sqNames[s];
  }

  /**
   * Get the 0x88 board square number from a square's algebraic name.
   * 
   * @param s
   *            the square's name
   * @return the number of the square.
   */
  static int toBoard0x88Number(final String s) {
    int file = s.charAt(0);
    file = file - 'a';
    int rank = s.charAt(1);
    rank = rank - '0';

    return Board0x88Util.fileAndRankToSquare(file, rank);
  }

  /**
   * Build the name string from the Board0x88 number.
   * 
   * @param s
   *            the Board0x88 square number
   * @return the square's name in algebraic notation.
   */
  @Nullable
  private static String fromBoard0x88number(final int s) {
    if (!Board0x88Util.isOnBoard(s)) {
      return null;
    }
    StringBuilder n = new StringBuilder(2);

    int file = Board0x88Util.fileFromSquare(s);
    int rank = Board0x88Util.rankFromSquare(s) + 1;

    return n.append(FILE_LETTERS[file]).append(rank).toString();
  }
}
