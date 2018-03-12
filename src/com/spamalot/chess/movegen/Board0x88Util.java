package com.spamalot.chess.movegen;

/**
 * Utility functions to handle 0x88 Board representations.
 * 
 * @author gej
 *
 */
final class Board0x88Util {

  /** Private Constructor. */
  private Board0x88Util() {
  }

  /**
   * Get the file from the square's number.
   * 
   * @param s
   *          The Square's number
   * @return The file the square is in.
   */
  static int fileFromSquare(int s) {
    return s & 7;
  }

  /**
   * @param s
   *          The Square's number
   * @return The rank the square is in.
   */
  static int rankFromSquare(int s) {
    return s >> 4;
  }

  /**
   * Convert file and rank to the index of the square in the 0x88 board.
   * 
   * @param file
   *          the file
   * @param rank
   *          the rank
   * @return the index of the square in the 0x88 board.
   */
  static int fileAndRankToSquare(int file, int rank) {
    return 16 * rank + file;
  }

  /**
   * Check if the square is on the board.
   * 
   * @param s
   *          index of square in the 0x88 board array
   * @return true if the square is on the board.
   */
  static boolean isOnBoard(int s) {
    return ((s & 0x88) == 0);
  }
}
