package com.spamalot.chess.piece;

/**
 * Class for Chess piece.
 *
 * @author gej
 *
 */
public class ChessPiece {

  /** Color of the piece. */
  private Color color;

  /** Type of the piece. */
  private PieceType type;

  /**
   * Construct Object for Chess Pieces.
   *
   * @param c Color of the piece
   * @param t Type of the piece
   */
  public ChessPiece(Color c, PieceType t) {
    color = c;
    type = t;
  }

  /**
   * Get Color of the Piece.
   *
   * @return Color of the Piece.
   */
  public Color getColor() {
    return color;
  }

  /**
   * Get type of the Piece.
   *
   * @return Type of the Piece.
   */
  public PieceType getType() {
    return type;
  }

  @Override
  public String toString() {
    return type.getStringForColor(color);
  }
}
