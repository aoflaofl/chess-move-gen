package com.spamalot.chess.piece;

/**
 * Class for Chess piece.
 */
public class ChessPiece {

  /** Color of the piece. */
  private final Color color;

  /** Type of the piece. */
  private final PieceType type;

  static final int DIAGONAL_MOVE = 1;
  static final int ORTHOGONAL_MOVE = 2;
  static final int KING_MOVE = 4;
  static final int PAWN_MOVE = 8;
  static final int KNIGHT_MOVE = 16;

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
