package com.spamalot.chess.movegen;

import com.spamalot.chess.Color;
import com.spamalot.chess.PieceType;

/**
 * Class for Chess piece.
 * 
 * @author gej
 *
 */
public class ChessPiece {
  /**
   * Construct Object for Chess Pieces.
   * 
   * @param color
   *          Color of the piece
   * @param type
   *          Type of the piece
   */
  ChessPiece(Color color, PieceType type) {
    this.color = color;
    this.type = type;
  }

  /** Color of the piece. */
  private Color color;
  /** Type of the piece. */
  private PieceType type;

  @Override
  public final String toString() {
    StringBuilder builder = new StringBuilder();
    if (this.color == Color.BLACK) {
      builder.append(this.type.blackChar);
    } else {
      builder.append(this.type.whiteChar);
    }
    return builder.toString();
  }
}
