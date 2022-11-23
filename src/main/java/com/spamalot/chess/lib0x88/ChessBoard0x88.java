package com.spamalot.chess.lib0x88;

import com.spamalot.chess.board.ChessBoard;
import com.spamalot.chess.piece.ChessPiece;

/**
 * The Board.
 *
 * @author gej
 *
 */
public class ChessBoard0x88 implements ChessBoard {
  /** Hold the 0x88 representation of the board. */
  private ChessPiece[] board = new ChessPiece[128];

  @Override
  public void addToBoard(ChessPiece p, int file, int rank) {
    addToBoard(p, ChessBoardUtil0x88.fileAndRankToSquare(file, rank));
  }

  /**
   * Add a piece.
   *
   * @param p  piece
   * @param sq square
   */
  private void addToBoard(ChessPiece p, int sq) {
    board[sq] = p;
  }

  @Override
  public boolean canMoveToSquare(int file, int rank) {
    return canMoveToSquare(ChessBoardUtil0x88.fileAndRankToSquare(file, rank));
  }

  private boolean canMoveToSquare(int sd) {
    boolean result = true;
    if (!(ChessBoardUtil0x88.isOnBoard(sd) && this.getPiece(sd) == null)) {
      result = false;
    }
    return result;
  }

  @Override
  public ChessPiece getPiece(int file, int rank) {
    return getPiece(ChessBoardUtil0x88.fileAndRankToSquare(file, rank));
  }

  /**
   * get a piece.
   *
   * @param sd square
   * @return piece.
   */
  private ChessPiece getPiece(int sd) {
    return board[sd];
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (int rank = 7; rank >= 0; rank--) {
      for (int file = 0; file < 8; file++) {

        int sq0x88 = ChessBoardUtil0x88.fileAndRankToSquare(file, rank);

        if (board[sq0x88] == null) {
          builder.append('.');
        } else {
          builder.append(board[sq0x88]);
        }
      }
      builder.append('\n');
    }
    return builder.toString();
  }
}
