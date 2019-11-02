package com.spamalot.chess.board;

import com.spamalot.chess.piece.ChessPiece;

/**
 * The Board.
 * 
 * @author gej
 *
 */
public final class ChessBoard0x88 implements ChessBoard {
  /** Hold the 0x88 representation of the board. */
  private ChessPiece[] board = new ChessPiece[128];

  @Override
  public void addToBoard(final ChessPiece p, final int file, final int rank) {
    addToBoard(p, Board0x88Util.fileAndRankToSquare(file, rank));
  }

  private void addToBoard(final ChessPiece p, final int sq) {
    board[sq] = p;
  }

  public boolean canMoveToSquare(final int file, final int rank) {
    return canMoveToSquare(Board0x88Util.fileAndRankToSquare(file, rank));
  }

  private boolean canMoveToSquare(final int sd) {
    boolean result = true;
    if (!(Board0x88Util.isOnBoard(sd)
        && (this.getPiece(sd) == null /* || this.getPiece(sd).getColor() != this.toMove */))) {
      result = false;
    }
    return result;
  }

  @Override
  public ChessPiece getPiece(final int file, final int rank) {
    return getPiece(Board0x88Util.fileAndRankToSquare(file, rank));
  }

  private ChessPiece getPiece(final int sd) {
    return board[sd];
  }

  @Override
  public String toString() {
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
}
