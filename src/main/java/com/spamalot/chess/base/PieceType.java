package com.spamalot.chess.base;

/**
 * Enum for type of Chess Piece.
 * 
 * @author gej
 *
 */
public enum PieceType {
  /** Pieces. */
  PAWN('p', 'P'), KNIGHT('n', 'N'), BISHOP('b', 'B'), ROOK('r', 'R'), QUEEN('q', 'Q'), KING('k', 'K');

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
   * Get the White Character representation for the piece.
   * 
   * @return the whiteChar
   */
  public char getWhiteChar() {
    return this.whiteChar;
  }

  /**
   * Get the Black Character representation for the piece.
   * 
   * @return the blackChar
   */
  public char getBlackChar() {
    return this.blackChar;
  }
}
