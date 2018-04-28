package com.spamalot.chess.base;

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

  /**
   * Construct the piece Type.
   * 
   * @param blackCh
   *          Black character representing this piece
   * @param whiteCh
   *          White character representing this piece
   */
  PieceType(final char blackCh, final char whiteCh) {
    this.whiteChar = whiteCh;
    this.blackChar = blackCh;
  }

  /**
   * Get the White Character that represents the piece on an ASCII board.
   * 
   * @return the whiteChar
   */
  public char getWhiteChar() {
    return this.whiteChar;
  }

  /**
   * Get the Black Character that represents the piece on an ASCII board.
   * 
   * @return the blackChar
   */
  public char getBlackChar() {
    return this.blackChar;
  }
}
