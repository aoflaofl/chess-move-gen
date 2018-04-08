package com.spamalot.chess.movegen;

import com.spamalot.chess.base.Color;
import com.spamalot.chess.base.PieceType;

/**
 * Class for Chess piece.
 * 
 * @author gej
 *
 */
class ChessPiece {
  /**
   * Construct Object for Chess Pieces.
   * 
   * @param c
   *          Color of the piece
   * @param t
   *          Type of the piece
   */
  ChessPiece(final Color c, final PieceType t) {
    this.color = c;
    this.type = t;
  }

  /** Color of the piece. */
  private Color color;
  /** Type of the piece. */
  private PieceType type;

  @Override
  public final String toString() {
    StringBuilder builder = new StringBuilder();
    if (this.color == Color.BLACK) {
      builder.append(this.type.getBlackChar());
    } else {
      builder.append(this.type.getWhiteChar());
    }
    return builder.toString();
  }
}
