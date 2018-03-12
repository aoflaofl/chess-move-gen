package com.spamalot.chess.fen;

import com.spamalot.chess.movegen.ChessPiece;

class FENChessPiece {
  private int rank;
  private int file;
  private ChessPiece piece;

  public FENChessPiece(ChessPiece p, int rank, int file) {
    this.piece = p;
    this.rank = rank;
    this.file = file;
  }
}
