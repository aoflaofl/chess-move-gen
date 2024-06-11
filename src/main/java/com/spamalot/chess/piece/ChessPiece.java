package com.spamalot.chess.piece;

/**
 * Class for Chess piece.
 */
public class ChessPiece {

  /** Color of the piece. */
  private final Color color;

  /** Type of the piece. */
  private final PieceType type;

  /**
   * Construct a ChessPiece object.
   *
   * @param color Color of the piece
   * @param type  Type of the piece
   */
  public ChessPiece(Color color, PieceType type) {
    this.color = color;
    this.type = type;
  }

  /**
   * Get the color of the piece.
   *
   * @return Color of the piece.
   */
  public Color getColor() {
    return color;
  }

  /**
   * Get the type of the piece.
   *
   * @return Type of the piece.
   */
  public PieceType getType() {
    return type;
  }

  @Override
  public String toString() {
    return type.getCharForColor(color);
  }

}
