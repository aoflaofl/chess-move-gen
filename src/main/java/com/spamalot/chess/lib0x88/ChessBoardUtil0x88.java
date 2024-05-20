package com.spamalot.chess.lib0x88;

/**
 * Utility functions to handle 0x88 Board representations.
 */
public final class ChessBoardUtil0x88 {

  /** Private constructor to prevent instantiation. */
  private ChessBoardUtil0x88() {
  }

  /**
   * Get the file from the square's number.
   *
   * @param square the square's number
   * @return the file the square is in
   */
  public static int fileFromSquare(int square) {
    return square & 7;
  }

  /**
   * Get the rank from the square's number.
   *
   * @param square the square's number
   * @return the rank the square is in
   */
  public static int rankFromSquare(int square) {
    return square >> 4;
  }

  /**
   * Convert file and rank to the index of the square in the 0x88 board.
   *
   * @param file the file
   * @param rank the rank
   * @return the index of the square in the 0x88 board
   */
  public static int fileAndRankToSquare(int file, int rank) {
    return 16 * rank + file;
  }

  /**
   * Check if the square is on the board.
   *
   * @param square index of the square in the 0x88 board array
   * @return true if the square is on the board, false otherwise
   */
  public static boolean isOnBoard(int square) {
    return (square & 0x88) == 0;
  }
}
