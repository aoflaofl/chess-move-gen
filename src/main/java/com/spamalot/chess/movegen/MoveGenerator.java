package com.spamalot.chess.movegen;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spamalot.chess.game.ChessGameState;
import com.spamalot.chess.lib0x88.ChessBoardUtil0x88;
import com.spamalot.chess.piece.ChessPiece;

public class MoveGenerator {

  private static final Logger LOGGER = LoggerFactory.getLogger(MoveGenerator.class);

  /**
   * Generate moves for single move pieces (Knights, Kings).
   * 
   * @param chessGameState
   * 
   * @param chessPiece
   *
   * @param s              the Square
   * @param d              the direction array
   * @return the move list.
   */
  public static List<ChessMove> generateJumperMoves(ChessGameState chessGameState, ChessPiece chessPiece, int s,
      int[] d) {
    List<ChessMove> m = new ArrayList<>();
    int sd;
    for (int dir : d) {

      sd = s + dir;

      if (chessGameState.board.canMoveToSquare(chessPiece, sd)) {
        m.add(MoveGenerator.generateMove(s, sd));
      }
    }
    return m;
  }

  /**
   * Generate a move.
   *
   * @param s  the source square
   * @param sd the destination square
   * @return a chess move.
   */
  private static final ChessMove generateMove(int s, int sd) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Generate move : ({}-{})", s, sd);
      // LOGGER.info("Generate move : {}-{}", SquareName.toName(s),
      // SquareName.toName(sd));
    }
    return new ChessMove(s, sd);
  }

  /**
   * Generate moves for sliding pieces (Queens, Bishops, Rooks).
   *
   * @param s the Square
   * @param d the direction array
   */
  static void generateSliderMoves(int s, int[] d) {
    for (int dir : d) {
      for (int sd = s + dir; ChessBoardUtil0x88.isOnBoard(sd); sd += dir) {
        generateMove(s, sd);
      }
    }
  }

}
