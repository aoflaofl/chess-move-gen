package com.spamalot.chess.fen;

import com.spamalot.chess.movegen.PieceColor;
import com.spamalot.chess.movegen.PieceType;

public interface FENboardable {

  void addPiece(PieceType p, PieceColor c, int file, int rank);

  void setToMove(PieceColor s);

}
