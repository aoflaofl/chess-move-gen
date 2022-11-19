package com.spamalot.chess.lib0x88;

/**
 * Utility functions to handle 0x88 Board representations.
 *
 * @author gej
 *
 */
public class ChessBoardUtil0x88 {

  /** Private Constructor. */
  private ChessBoardUtil0x88() {
  }

  /**
   * Get the file from the square's number.
   *
   * @param s The Square's number
   * @return The file the square is in.
   */
  static int fileFromSquare(int s) {
    return s & 7;
  }

  /**
   * Given a square's number in an 0x88 board, give the rank.
   *
   * @param s The Square's number
   * @return The rank the square is in.
   */
  static int rankFromSquare(int s) {
    return s >> 4;
  }

  /**
   * Convert file and rank to the index of the square in the 0x88 board.
   *
   * @param file the file
   * @param rank the rank
   * @return the index of the square in the 0x88 board.
   */
  public static int fileAndRankToSquare(int file, int rank) {
    return 16 * rank + file;
  }

  /**
   * Check if the square is on the board.
   *
   * @param s index of square in the 0x88 board array
   * @return true if the square is on the board.
   */
  public static boolean isOnBoard(int s) {
    return (s & 0x88) == 0;
  }
}
