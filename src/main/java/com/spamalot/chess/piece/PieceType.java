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
  final String[] stringRepresentation;

  /**
   * Construct the piece type.
   *
   * @param blackCh Black character representing this piece
   * @param whiteCh White character representing this piece
   */
  PieceType(char blackCh, char whiteCh) {
    this.whiteChar = whiteCh;
    this.blackChar = blackCh;
    this.stringRepresentation = new String[Color.values().length];
    this.stringRepresentation[Color.WHITE.ordinal()] = String.valueOf(whiteCh);
    this.stringRepresentation[Color.BLACK.ordinal()] = String.valueOf(blackCh);
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

  public static PieceType fromChar(char c) {
    for (PieceType pt : PieceType.values()) {
      if (pt.getBlackChar() == c || pt.getWhiteChar() == c) {
        return pt;
      }
    }
    return null;
  }

  String getCharForColor(Color color) {
    return stringRepresentation[color.ordinal()];
  }

}
