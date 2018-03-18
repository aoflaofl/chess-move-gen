package com.spamalot.chess.fen;

import com.spamalot.chess.base.Color;
import com.spamalot.chess.base.PieceType;

/**
 * Interface to implement to be able to be updateable by the FEN String parser.
 * 
 * @author gej
 *
 */
public interface FENboardable {

  /**
   * Add a Piece to the Chess Board.
   * 
   * @param pt
   *          the PieceType
   * @param c
   *          the Piece's Color
   * @param f
   *          the File the Piece goes in (1-8)
   * @param r
   *          the Rank the Piece goes in (1-8)
   */
  void addPiece(PieceType pt, Color c, int f, int r);

  /**
   * Set the color that is to move next.
   * 
   * @param c
   *          the Color
   */
  void setToMove(Color c);

  /**
   * Set whether castling to one side is posible.
   * 
   * @param pt
   *          Queen or King, depending on the side
   * @param c
   *          the Color
   * @param canCastle
   *          true if can castle to that side.
   */
  void setCastling(PieceType pt, Color c, boolean canCastle);

  void setEnPassantSquare(int file, int rank);

  void setHalfMovesSinceCaptureOrPawnMove(int intValue);

  void setMoveNumber(int intValue);

}
