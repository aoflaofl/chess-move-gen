package com.spamalot.chess.lib0x88;

import com.spamalot.chess.piece.ChessPiece;

/**
 * The Board using 0x88 representation.
 */
public class ChessBoard0x88 {
  /** Hold the 0x88 representation of the board. */
  private final ChessPiece[] board = new ChessPiece[128];

  /**
   * Add a piece to the board at the specified file and rank.
   *
   * @param piece the chess piece to add
   * @param file  the file (column) of the square
   * @param rank  the rank (row) of the square
   */
  public void addToBoard(ChessPiece piece, int file, int rank) {
    int square = ChessBoardUtil0x88.fileAndRankToSquare(file, rank);
    addToBoard(piece, square);
  }

  /**
   * Add a piece to the board at the specified square.
   *
   * @param piece  the chess piece to add
   * @param square the square in 0x88 representation
   */
  private void addToBoard(ChessPiece piece, int square) {
    board[square] = piece;
  }

  /**
   * Check if a piece can move to the specified file and rank.
   *
   * @param file the file (column) of the square
   * @param rank the rank (row) of the square
   * @return true if a piece can move to the square, false otherwise
   */
  public boolean canMoveToSquare(int file, int rank) {
    int square = ChessBoardUtil0x88.fileAndRankToSquare(file, rank);
    return canMoveToSquare(square);
  }

  /**
   * Check if a piece can move to the specified square.
   *
   * @param square the square in 0x88 representation
   * @return true if a piece can move to the square, false otherwise
   */
  private boolean canMoveToSquare(int square) {
    return ChessBoardUtil0x88.isOnBoard(square) && getPiece(square) == null;
  }

  /**
   * Get the piece at the specified file and rank.
   *
   * @param file the file (column) of the square
   * @param rank the rank (row) of the square
   * @return the chess piece at the square, or null if the square is empty
   */
  public ChessPiece getPiece(int file, int rank) {
    int square = ChessBoardUtil0x88.fileAndRankToSquare(file, rank);
    return getPiece(square);
  }

  /**
   * Get the piece at the specified square.
   *
   * @param square the square in 0x88 representation
   * @return the chess piece at the square, or null if the square is empty
   */
  private ChessPiece getPiece(int square) {
    return board[square];
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (int rank = 7; rank >= 0; rank--) {
      for (int file = 0; file < 8; file++) {
        int square = ChessBoardUtil0x88.fileAndRankToSquare(file, rank);
        ChessPiece piece = board[square];
        builder.append(piece == null ? '.' : piece);
      }
      builder.append('\n');
    }
    return builder.toString();
  }
}
