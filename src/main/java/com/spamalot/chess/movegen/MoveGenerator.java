package com.spamalot.chess.movegen;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spamalot.chess.game.ChessGameState;
import com.spamalot.chess.lib0x88.ChessBoardUtil0x88;
import com.spamalot.chess.lib0x88.SquareName0x88;
import com.spamalot.chess.piece.ChessPiece;
import com.spamalot.chess.piece.Color;
import com.spamalot.chess.piece.PieceType;

public class MoveGenerator {

  private static final Logger LOGGER = LoggerFactory.getLogger(MoveGenerator.class);
  /** Diagonal diffs. */
  public static final int[] DIAG_DIFF = new int[] { 17, 15, -17, -15 };
  /** Knight diffs. */
  private static final int[] KNIGHT_DIFF = new int[] { 14, 18, 31, 33, -18, -14, -31, -33 };
  /** Orthogonal (up and down, right and left) diffs. */
  public static final int[] ORTHO_DIFF = new int[] { -1, -16, 16, 1 };

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
    isAttacked(chessGameState, s, chessPiece);
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
    // if (LOGGER.isInfoEnabled()) {
    LOGGER.info("Generate move : ({}-{})", s, sd);
    // LOGGER.info("Generate move : {}-{}", SquareName.toName(s),
    // SquareName.toName(sd));
    // }
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

  /**
   * Checks if a given square on the chess board is attacked by any piece.
   *
   * @param chessGameState The current state of the chess game.
   * @param square         The square on the chess board to check.
   * @param chessPiece     The chess piece on the square.
   * @return true if the square is attacked by any piece, false otherwise.
   */
  static boolean isAttacked(ChessGameState chessGameState, int square, ChessPiece chessPiece) {
    return isKnightAttacking(chessGameState, square, chessPiece) || isPawnAttacking(chessGameState, square, chessPiece)
        || isKingAttacking(chessGameState, square, chessPiece) || isQueenAttacking(chessGameState, square, chessPiece)
        || isBishopAttacking(chessGameState, square, chessPiece) || isRookAttacking(chessGameState, square, chessPiece);
  }

  private static boolean isPieceAttacking(ChessGameState chessGameState, int square, ChessPiece chessPiece,
      PieceType pieceType, int[] diffs, boolean isSlider) {
    for (int p : diffs) {
      if (isAttackingInDirection(chessGameState, square, chessPiece, pieceType, p, isSlider)) {
        return true;
      }
    }
    return false;
  }

  private static boolean isAttackingInDirection(ChessGameState chessGameState, int square, ChessPiece chessPiece,
      PieceType pieceType, int diff, boolean isSlider) {
    int potentialSquare = square + diff;
    while (ChessBoardUtil0x88.isOnBoard(potentialSquare)) {
      ChessPiece potentialAttacker = chessGameState.board.getPiece(potentialSquare);
      if (potentialAttacker != null && potentialAttacker.getType() == pieceType
          && potentialAttacker.getColor() != chessPiece.getColor()) {
        return true;
      }
      if (!isSlider || potentialAttacker != null) {
        break;
      }
      potentialSquare += diff;
    }
    return false;
  }

  private static boolean isRookAttacking(ChessGameState chessGameState, int square, ChessPiece chessPiece) {
    System.out.println("isRookAttacking");

    return isPieceAttacking(chessGameState, square, chessPiece, PieceType.ROOK, ORTHO_DIFF, true);
  }

  private static boolean isKnightAttacking(ChessGameState chessGameState, int square, ChessPiece chessPiece) {
    return isPieceAttacking(chessGameState, square, chessPiece, PieceType.KNIGHT, KNIGHT_DIFF, false);
  }

  private static boolean isKingAttacking(ChessGameState chessGameState, int square, ChessPiece chessPiece) {
    return isPieceAttacking(chessGameState, square, chessPiece, PieceType.KING, ORTHO_DIFF, false)
        || isPieceAttacking(chessGameState, square, chessPiece, PieceType.KING, DIAG_DIFF, false);
  }

  private static boolean isQueenAttacking(ChessGameState chessGameState, int square, ChessPiece chessPiece) {
    return isPieceAttacking(chessGameState, square, chessPiece, PieceType.QUEEN, ORTHO_DIFF, true)
        || isPieceAttacking(chessGameState, square, chessPiece, PieceType.QUEEN, DIAG_DIFF, true);
  }

  private static boolean isBishopAttacking(ChessGameState chessGameState, int square, ChessPiece chessPiece) {
    return isPieceAttacking(chessGameState, square, chessPiece, PieceType.BISHOP, DIAG_DIFF, true);
  }

  private static boolean isPawnAttacking(ChessGameState chessGameState, int square, ChessPiece chessPiece) {
    // TODO Auto-generated method stub
    return false;
  }
}
