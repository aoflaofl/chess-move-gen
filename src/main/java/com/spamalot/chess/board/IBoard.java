package com.spamalot.chess.board;

import com.spamalot.chess.piece.ChessPiece;

public interface IBoard {

  /**
   * Get the piece in a square.
   * 
   * @param sd
   *             the Square
   * @return the Piece.
   */
  ChessPiece getPiece(int sd);

  @Override
  String toString();

  /**
   * Add a Piece to the Board.
   * 
   * @param p
   *             Piece
   * @param sq
   *             Square
   */
  void addToBoard(ChessPiece p, int sq);

}
