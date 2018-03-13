package com.spamalot.chess.fen;

import com.spamalot.chess.Color;
import com.spamalot.chess.movegen.PieceType;

public interface FENboardable {

  void addPiece(PieceType p, Color c, int file, int rank);

  void setToMove(Color s);

}
