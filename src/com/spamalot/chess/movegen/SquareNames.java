package com.spamalot.chess.movegen;

/**
 * Utility functions for handling square names.
 * 
 * @author gej
 *
 */
final class SquareNames {
  /** Private Constructor. */
  private SquareNames() {
  }

  /** Map square number to name. */
  private static String[] sqNames = new String[128];

  static {
    for (int i = 0; i < 128; i++) {
      sqNames[i] = SquareNames._sqName(i);
    }
  }

  /**
   * Get the square's name from the number.
   * 
   * @param s
   *          the square's number
   * @return the name of the square.
   */
  static String toName(final int s) {
    return sqNames[s];
  }

  /**
   * Get the number from a square's name.
   * 
   * @param s
   *          the square's name
   * @return the number of the square.
   */
  static int toNumber(final String s) {
    int file = s.charAt(0);
    file = file - 'a';
    int rank = s.charAt(1);
    rank = rank - '0';

    return Board0x88Util.fileAndRankToSquare(file, rank);
  }

  /**
   * Get the file's letter.
   * 
   * @param file
   *          the file number
   * @return the letter of the file.
   */
  private static char fileLetter(final int file) {
    return "abcdefgh".charAt(file);
  }

  /**
   * Build the name string.
   * 
   * @param s
   *          the Square
   * @return the square's name.
   */
  private static String _sqName(final int s) {
    int file = Board0x88Util.fileFromSquare(s);
    int rank = Board0x88Util.rankFromSquare(s) + 1;
    if (Board0x88Util.isOnBoard(s)) {
      return SquareNames.fileLetter(file) + "" + rank; // Not the best way to do it. Probably better as a lookup.
    }
    return "NN";
  }
}
