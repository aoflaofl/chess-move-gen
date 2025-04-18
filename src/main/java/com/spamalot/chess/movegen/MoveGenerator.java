package com.spamalot.chess.movegen;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spamalot.chess.game.ChessGameState;
import com.spamalot.chess.lib0x88.ChessBoard0x88;
import com.spamalot.chess.lib0x88.ChessBoardUtil0x88;
import com.spamalot.chess.piece.ChessPiece;
import com.spamalot.chess.piece.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.spamalot.chess.piece.MoveStyle;
import com.spamalot.chess.piece.PieceType;

public class MoveGenerator {
  /** Logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(MoveGenerator.class);

  /** Diagonal diffs. */
  public static final int[] DIAG_DIFF = new int[] { 17, 15, -17, -15 };
  /** Knight diffs. */
  private static final int[] KNIGHT_DIFF = new int[] { 14, 18, 31, 33, -18, -14, -31, -33 };
  /** Orthogonal (up and down, right and left) diffs. */
  public static final int[] ORTHO_DIFF = new int[] { -1, -16, 16, 1 };

  private MoveGenerator() {
  }

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
    if (isAttacked(chessGameState, s, chessPiece.getColor().opposite())) {
      LOGGER.info("Attacked");
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
    // if (LOGGER.isInfoEnabled()) {
    LOGGER.info("Generate move : ({}-{})", s, sd);
    // LOGGER.info("Generate move : {}-{}", SquareName.toName(s),
    // SquareName.toName(sd));
    // }
    return new ChessMove(s, sd);
  }

  /**
   * Checks if a given square on the chess board is attacked by any piece.
   *
   * @param chessGameState The current state of the chess game.
   * @param square         The square on the chess board to check.
   * @param attackingColor The chess piece on the square.
   * @return true if the square is attacked by any piece, false otherwise.
   */

  private static boolean isAttacked(ChessGameState chessGameState, int square, Color attackingColor) {
    return isOrthoAttacking(chessGameState.getBoard(), square, attackingColor)
        || isDiagAttacking(chessGameState.getBoard(), square, attackingColor)
        || isKnightAttacking(chessGameState.getBoard(), square, attackingColor);
  }

  private static boolean isKnightAttacking(ChessBoard0x88 chessBoard0x88, int square, Color color) {
    // TODO Auto-generated method stub
    return false;
  }

  private static boolean isAttacked(ChessGameState chessGameState, int square, ChessPiece chessPiece) {
    return isKnightAttacking(chessGameState, square, chessPiece) || isPawnAttacking(chessGameState, square, chessPiece)
        || isKingAttacking(chessGameState, square, chessPiece) || isQueenAttacking(chessGameState, square, chessPiece)
        || isBishopAttacking(chessGameState, square, chessPiece) || isRookAttacking(chessGameState, square, chessPiece);
  }

  private static boolean isPieceAttacking(ChessGameState chessGameState, int square, ChessPiece chessPiece,
      PieceType pieceType, int[] diffs) {
    for (int p : diffs) {
      if (isAttackingInDirection(chessGameState, square, chessPiece, pieceType, p)) {
        return true;
      }
    }
    return false;
  }

  private static boolean isAttackingInDirection(ChessGameState chessGameState, int square, ChessPiece color,
      PieceType pieceType, int diff) {
    int potentialSquare = square + diff;
    while (ChessBoardUtil0x88.isOnBoard(potentialSquare)) {
      ChessPiece potentialAttacker = chessGameState.board.getPiece(potentialSquare);
      if (potentialAttacker != null && potentialAttacker.getType() == pieceType
          && potentialAttacker.getColor() != color.getColor()) {
        return true;
      }
      if (pieceType.getMoveStyle() != MoveStyle.SLIDER || potentialAttacker != null) {
        break;
      }
      potentialSquare += diff;
    }
    return false;
  }

  private static boolean isRookAttacking(ChessGameState chessGameState, int square, ChessPiece chessPiece) {
    System.out.println("isRookAttacking");

    return isPieceAttacking(chessGameState, square, chessPiece, PieceType.ROOK, ORTHO_DIFF);
  }

  private static boolean isKnightAttacking(ChessGameState chessGameState, int square, ChessPiece chessPiece) {
    return isPieceAttacking(chessGameState, square, chessPiece, PieceType.KNIGHT, KNIGHT_DIFF);
  }

  private static boolean isKingAttacking(ChessGameState chessGameState, int square, ChessPiece chessPiece) {
    return isPieceAttacking(chessGameState, square, chessPiece, PieceType.KING, ORTHO_DIFF)
        || isPieceAttacking(chessGameState, square, chessPiece, PieceType.KING, DIAG_DIFF);
  }

  private static boolean isQueenAttacking(ChessGameState chessGameState, int square, ChessPiece chessPiece) {
    return isPieceAttacking(chessGameState, square, chessPiece, PieceType.QUEEN, ORTHO_DIFF)
        || isPieceAttacking(chessGameState, square, chessPiece, PieceType.QUEEN, DIAG_DIFF);
  }

  private static boolean isBishopAttacking(ChessGameState chessGameState, int square, ChessPiece chessPiece) {
    return isPieceAttacking(chessGameState, square, chessPiece, PieceType.BISHOP, DIAG_DIFF);
  }

  private static boolean isPawnAttacking(ChessGameState chessGameState, int square, ChessPiece chessPiece) {
    // TODO Auto-generated method stub
    return false;
  }

  private static boolean isDiagAttacking(ChessBoard0x88 chessBoard0x88, int attackedSquare, Color attackingColor) {
    for (int diff : DIAG_DIFF) {
      if (isPieceDiagAttackingInDirection(chessBoard0x88, attackedSquare, diff, attackingColor)) {
        return true;
      }
    }
    return false;
  }

  private static boolean isOrthoAttacking(ChessBoard0x88 chessBoard0x88, int attackedSquare, Color attackingColor) {
    for (int diff : ORTHO_DIFF) {
      if (isPieceOrthoAttackingInDirection(chessBoard0x88, attackedSquare, diff, attackingColor)) {
        return true;
      }
    }
    return false;
  }

  private static boolean isPieceDiagAttackingInDirection(ChessBoard0x88 chessBoard0x88, int attackedSquare, int diff,
      Color attackingColor) {
    ChessPiece piece = getPieceInDirection(chessBoard0x88, attackedSquare, diff);
    return piece != null && piece.getColor() == attackingColor && piece.getType().isDiagonal();
  }

  private static boolean isPieceOrthoAttackingInDirection(ChessBoard0x88 chessBoard0x88, int attackedSquare, int diff,
      Color attackingColor) {
    ChessPiece piece = getPieceInDirection(chessBoard0x88, attackedSquare, diff);
    return piece != null && piece.getColor() == attackingColor && piece.getType().isOrthogonal();
  }

  private static ChessPiece getPieceInDirection(ChessBoard0x88 chessBoard0x88, int attackedSquare, int diff) {
    for (int square = attackedSquare + diff; ChessBoardUtil0x88.isOnBoard(square); square += diff) {
      ChessPiece piece = chessBoard0x88.getPiece(square);
      if (piece != null) {
        return piece;
      }
    }
    return null;
  }

}
