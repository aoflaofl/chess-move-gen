package com.spamalot.chess.piece;

/**
 * Enum for type of Chess Piece.
 */
public enum PieceType {
  /** Pawn type. */
  PAWN('p', 'P'),
  /** Knight type. */
  KNIGHT('n', 'N'),
  /** Bishop type. */
  BISHOP('b', 'B'),
  /** Rook type. */
  ROOK('r', 'R'),
  /** Queen type. */
  QUEEN('q', 'Q'),
  /** King type. */
  KING('k', 'K');

  /** White character for this piece. */
  private final char whiteChar;
  /** Black character for this piece. */
  private final char blackChar;
  /** String array with String representation of the PieceType */
  private final String[] stringRepresentation;

  /**
   * Construct the piece type.
   *
   * @param blackChar Black character representing this piece
   * @param whiteChar White character representing this piece
   */
  PieceType(char blackChar, char whiteChar) {
    this.whiteChar = whiteChar;
    this.blackChar = blackChar;
    this.stringRepresentation = new String[Color.values().length];
    this.stringRepresentation[Color.WHITE.ordinal()] = String.valueOf(whiteChar);
    this.stringRepresentation[Color.BLACK.ordinal()] = String.valueOf(blackChar);
  }

  /**
   * Get the white character that represents the piece on an ASCII board.
   *
   * @return the whiteChar
   */
  public char getWhiteChar() {
    return whiteChar;
  }

  /**
   * Get the black character that represents the piece on an ASCII board.
   *
   * @return the blackChar
   */
  public char getBlackChar() {
    return blackChar;
  }

  /**
   * Get the string representation of the piece for the given color.
   *
   * @param color the color of the piece
   * @return the string representation of the piece
   */
  public String getStringForColor(Color color) {
    return stringRepresentation[color.ordinal()];
  }
}
