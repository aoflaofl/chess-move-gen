
package com.spamalot.chess.lib0x88;

/**
 * This class is used to generate and manage square names in 0x88 chessboard
 * representation. The 0x88 representation is a method to represent a chessboard
 * in computer memory. It uses a 128-cell array, where each cell corresponds to
 * a square on the chessboard.
 */
public final class SquareName0x88 {

  // Array of file letters used in standard chess notation.
  private static final char[] FILE_LETTERS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };

  // Array of square names in 0x88 chessboard representation.
  private static final String[] SQUARE_NAMES = new String[128];

  // Static block to initialize SQUARE_NAMES array.
  static {
    for (int i = 0; i < 128; i++) {
      SQUARE_NAMES[i] = fromBoard0x88number(i);
    }
  }

  // Private constructor to prevent instantiation of this utility class.
  private SquareName0x88() {
  }

  /**
   * Converts a square number in 0x88 representation to a square name in standard
   * chess notation.
   * 
   * @param square the square number in 0x88 representation.
   * @return the square name in standard chess notation, or null if the square
   *         number is not on the board.
   */
  public static String fromBoard0x88number(int square) {
    if (!ChessBoardUtil0x88.isOnBoard(square)) {
      return null;
    }

    int file = ChessBoardUtil0x88.fileFromSquare(square);
    int rank = ChessBoardUtil0x88.rankFromSquare(square) + 1;
    return FILE_LETTERS[file] + Integer.toString(rank);
  }

  /**
   * Converts a file and rank to a square name in standard chess notation.
   * 
   * @param file the file number (0-7).
   * @param rank the rank number (0-7).
   * @return the square name in standard chess notation.
   */
  public static String toName(int file, int rank) {
    return SQUARE_NAMES[ChessBoardUtil0x88.fileAndRankToSquare(file, rank)];
  }
}
