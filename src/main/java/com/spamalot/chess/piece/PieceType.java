package com.spamalot.chess.piece;

/**
 * Enum for type of Chess Piece.
 *
 * @author gej
 *
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
  private char whiteChar;
  /** Black character for this piece. */
  private char blackChar;
  /** String array with String representation of the PieceType */
  private String[] stringRepresentation;

  /**
   * Construct the piece Type.
   *
   * @param blackCh Black character representing this piece
   * @param whiteCh White character representing this piece
   */
  PieceType(char blackCh, char whiteCh) {
    whiteChar = whiteCh;
    blackChar = blackCh;
    stringRepresentation = new String[Color.values().length];
    stringRepresentation[Color.WHITE.ordinal()] = String.valueOf(whiteCh);
    stringRepresentation[Color.BLACK.ordinal()] = String.valueOf(blackCh);
  }

  /**
   * Get the White Character that represents the piece on an ASCII board.
   *
   * @return the whiteChar
   */
  public char getWhiteChar() {
    return whiteChar;
  }

  /**
   * Get the Black Character that represents the piece on an ASCII board.
   *
   * @return the blackChar
   */
  public char getBlackChar() {
    return blackChar;
  }

  String getStringForColor(Color c) {
    return stringRepresentation[c.ordinal()];
  }
}
