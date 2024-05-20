package com.spamalot.chess.lib0x88;

import javax.annotation.Nullable;

/**
 * Utility functions for handling square names.
 */
public final class SquareName0x88 {

  /** Map file number to file letter. */
  private static final char[] FILE_LETTERS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };

  /** Cache square number to name. */
  private static final String[] SQUARE_NAMES = new String[128];

  static {
    for (int i = 0; i < 128; i++) {
      SQUARE_NAMES[i] = fromBoard0x88number(i);
    }
  }

  /** Private constructor to prevent instantiation. */
  private SquareName0x88() {
  }

  /**
   * Build the name string from the Board0x88 number.
   *
   * @param square the Board0x88 square number
   * @return the square's name in algebraic notation, or null if the square is not
   *         on the board.
   */
  @Nullable
  private static String fromBoard0x88number(int square) {
    if (!ChessBoardUtil0x88.isOnBoard(square)) {
      return null;
    }

    int file = ChessBoardUtil0x88.fileFromSquare(square);
    int rank = ChessBoardUtil0x88.rankFromSquare(square) + 1;
    return makeSquareName(file, rank);
  }

  /**
   * Make square name.
   *
   * @param file the file
   * @param rank the rank
   * @return the name of the square in algebraic notation.
   */
  private static String makeSquareName(int file, int rank) {
    return FILE_LETTERS[file] + Integer.toString(rank);
  }

  /**
   * Get the square's name from the Board0x88 square number.
   *
   * @param file the file
   * @param rank the rank
   * @return the name of the square.
   */
  public static String toName(int file, int rank) {
    return SQUARE_NAMES[ChessBoardUtil0x88.fileAndRankToSquare(file, rank)];
  }
}
