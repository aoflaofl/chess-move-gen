package com.spamalot.chess.game;

import com.spamalot.chess.movegen.Board0x88Util;
import com.spamalot.chess.piece.ChessPiece;

public class Board {
  /** Hold the 0x88 representation of the board. */
  ChessPiece[] board = new ChessPiece[128];

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
}
