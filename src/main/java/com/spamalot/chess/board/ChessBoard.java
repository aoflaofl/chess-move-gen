package com.spamalot.chess.board;

import com.spamalot.chess.piece.ChessPiece;

/**
 * Some chess board thing.
 *
 * @author gej
 *
 */
public interface ChessBoard {

  /**
   * Get the piece in a square.
   *
   * @param file File
   * @param rank Rank
   * @return the Piece.
   */
  ChessPiece getPiece(int file, int rank);

  /**
   * Add a Piece to the Board.
   *
   * @param p    Piece
   * @param file File
   * @param rank Rank
   */
  void addToBoard(ChessPiece p, int file, int rank);

  /**
   * Check if a piece of the current color can legally move to a square. Does not
   * check if King will be attacked if it moves here.
   *
   * @param file File
   * @param rank Rank
   * @return true if the square can be moved to.
   */
  boolean canMoveToSquare(int file, int rank);
}
