package com.spamalot.chess.game;

import com.spamalot.chess.piece.Color;
import com.spamalot.chess.piece.PieceType;

/**
 * Interface to implement to be able to be updateable by the FEN String parser.
 * 
 * @author gej
 *
 */
public interface ChessGame {

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
   * Set whether castling to one side is possible.
   * 
   * @param pt
   *          Queen or King, depending on the side
   * @param c
   *          the Color
   * @param canCastle
   *          true if can castle to that side.
   */
  void setCastling(PieceType pt, Color c, boolean canCastle);

  /**
   * Set the en-passant square.
   * 
   * @param file
   *          the file
   * @param rank
   *          the rank
   */
  void setEnPassantSquare(int file, int rank);

  /**
   * Set half moves made since last capture or pawn move.
   * 
   * @param intValue
   *          number of half-moves
   */
  void setHalfMovesSinceCaptureOrPawnMove(int intValue);

  /**
   * Set move number.
   * 
   * @param intValue
   *          move number
   */
  void setMoveNumber(int intValue);
}
