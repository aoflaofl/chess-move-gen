package com.spamalot.chess.movegen;

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
  public char whiteChar;
  /** Black character for this piece. */
  public char blackChar;

  /**
   * Construct the piece Type.
   * 
   * @param blackChar
   *          Black character representing this piece
   * @param whiteChar
   *          White character representing this piece
   */
  PieceType(char blackChar, char whiteChar) {
    this.whiteChar = whiteChar;
    this.blackChar = blackChar;
  }
}
