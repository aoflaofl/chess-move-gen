package com.spamalot.chess.movegen;

import com.spamalot.chess.Color;

/**
 * Entry class for Chess Move Generator program.
 * 
 * @author gej
 *
 */
public final class ChessMoveGen {
  /** Private constructor. */
  private ChessMoveGen() {
  }

  /**
   * Start here.
   * 
   * @param args
   *          command line
   */
  public static void main(String[] args) {
    ChessBoard b = new ChessBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    b.addPiece(new ChessPiece(Color.WHITE, PieceType.KING), 0);
    b.addPiece(new ChessPiece(Color.BLACK, PieceType.QUEEN), 1);

    System.out.println(b);
  }

}
