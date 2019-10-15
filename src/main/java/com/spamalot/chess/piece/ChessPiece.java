package com.spamalot.chess.piece;

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
   * @param c
   *            Color of the piece
   * @param t
   *            Type of the piece
   */
  public ChessPiece(final Color c, final PieceType t) {
    this.color = c;
    this.type = t;
  }

  /** Color of the piece. */
  private Color color;

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
