package com.spamalot.chess.movegen;

import com.spamalot.chess.lib0x88.ChessBoardUtil0x88;
import com.spamalot.chess.lib0x88.SquareName0x88;
import com.spamalot.chess.piece.ChessPiece;

/**
 * Represent a chess piece that exists on the board.
 *
 * @author gej
 *
 */
public class PieceNode {
  /** The file. */
  private int file;
  /** The piece. */
  private ChessPiece piece;
  /** The rank. */
  private int rank;

  /**
   * Constructor.
   *
   * @param p the Piece
   * @param f file
   * @param r rank
   */
  public PieceNode(ChessPiece p, int f, int r) {
    piece = p;
    file = f;
    rank = r;
  }

  /**
   * Get the 0x88 square number.
   *
   * @return the 0x88 square this Piece is in.
   */
  public int get0x88Square() {
    return ChessBoardUtil0x88.fileAndRankToSquare(file, rank);
  }

  /**
   * Get the Piece.
   *
   * @return the Piece.
   */
  public ChessPiece getPiece() {
    return piece;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(piece).append(SquareName0x88.toName(file, rank));
    return builder.toString();
  }
}
