package com.spamalot.chess.movegen;

import com.spamalot.chess.base.PieceType;

/**
 * Represent a chess piece that exists on the board.
 * 
 * @author gej
 *
 */
class PieceNode {
  /** The piece. */
  private ChessPiece piece;
  /** The square. */
  private int square;

  /**
   * Constructor.
   * 
   * @param p
   *          the Piece
   * @param sq
   *          the Square
   */
  PieceNode(final ChessPiece p, final int sq) {
    this.piece = p;
    this.square = sq;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(this.piece).append(SquareName.toName(this.square));
    return builder.toString();
  }

  /**
   * Get the Piece Type.
   * 
   * @return the Type of this Piece.
   */
  public PieceType getPieceType() {
    return this.piece.getType();
  }

  /**
   * Get the 0x88 square number.
   * 
   * @return the 0x88 square this Piece is in.
   */
  public int get0x88Square() {
    return this.square;
  }
}
