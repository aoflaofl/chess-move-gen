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
  public ChessPiece(final Color c, final PieceType t) {
    this.color = c;
    this.type = t;
  }

  /**
   * Get Color of the Piece.
   * 
   * @return Color of the Piece.
   */
  public Color getColor() {
    return this.color;
  }

  /**
   * Get type of the Piece.
   * 
   * @return Type of the Piece.
   */
  public PieceType getType() {
    return this.type;
  }

  @Override
  public final String toString() {
    return this.type.getStringForColor(this.color);
  }
}
