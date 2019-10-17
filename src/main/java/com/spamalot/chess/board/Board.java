package com.spamalot.chess.board;

import com.spamalot.chess.piece.ChessPiece;

/**
 * The Board.
 * 
 * @author gej
 *
 */
public class Board {
  /** Hold the 0x88 representation of the board. */
  private ChessPiece[] board = new ChessPiece[128];

  /**
   * Get the piece in a square.
   * 
   * @param sd
   *             the Square
   * @return the Piece.
   */
  public ChessPiece getPiece(final int sd) {
    return board[sd];
  }

  @Override
  public final String toString() {
    StringBuilder builder = new StringBuilder();
    for (int rank = 7; rank >= 0; rank--) {
      for (int file = 0; file < 8; file++) {

        int sq0x88 = Board0x88Util.fileAndRankToSquare(file, rank);

        if (this.board[sq0x88] == null) {
          builder.append('.');
        } else {
          builder.append(this.board[sq0x88]);
        }
      }
      builder.append('\n');
    }
    return builder.toString();
  }

  /**
   * Add a Piece to the Board.
   * 
   * @param p
   *             Piece
   * @param sq
   *             Square
   */
  public void addToBoard(final ChessPiece p, final int sq) {
    board[sq] = p;
  }
}