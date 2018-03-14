package com.spamalot.chess.movegen;

import static com.google.common.base.Preconditions.checkNotNull;

import com.spamalot.chess.Color;
import com.spamalot.chess.PieceType;
import com.spamalot.chess.fen.FENUtil;
import com.spamalot.chess.fen.FENboardable;

import java.util.LinkedList;

/**
 * I'm just going to jump in and start doing an 0x88 board and refactor later.
 * 
 * I'll give it a catchy name: "Spacky"
 * 
 * First goal: Make it solve mate in X problems. This will require move
 * generation and tree traversing code.
 * 
 * WCGW?
 * 
 * @author gej
 *
 */
final class ChessBoard implements FENboardable {
  /** Hold the 0x88 representation of the board. */
  private ChessPiece[] board = new ChessPiece[128];

  /** Linked List of Chess pieces currently on the board. */
  private LinkedList<PieceNode> pieceList = new LinkedList<>();

  /** Orthogonal (up and down, right and left) diffs. */
  static final int[] ORTHO_DIFF = new int[] { -1, -16, 16, 1 };
  /** Diagonal diffs. */
  static final int[] DIAG_DIFF = new int[] { 17, 15, -17, -15 };
  /** Knight diffs. */
  static final int[] KNIGHT_DIFF = new int[] { 14, 18, 31, 33, -18, -14, -31, -33 };

  /**
   * Put a Chess piece on the board.
   * 
   * @param p
   *          the Chess Piece
   * @param sq
   *          the Piece's square
   */
  void addPiece(ChessPiece p, int sq) {
    this.pieceList.offer(new PieceNode(p, sq));

    this.board[sq] = p;
  }

  /**
   * Generate moves for sliding pieces (Queens, Bishops, Rooks).
   * 
   * @param s
   *          the Square
   * @param d
   *          the direction array
   */
  static void generateSliderMoves(int s, int[] d) {
    for (int dir : d) {
      for (int sd = s + dir; Board0x88Util.isOnBoard(sd); sd += dir) {
        generateMove(s, sd);
      }
    }
  }

  /**
   * Generate moves for single move pieces (Knights, Kings).
   * 
   * @param s
   *          the Square
   * @param d
   *          the direction array
   */
  static void generateJumperMoves(int s, int[] d) {
    int sd;
    for (int dir : d) {

      sd = s + dir;

      if (Board0x88Util.isOnBoard(sd)) {
        System.out.println(dir);
        generateMove(s, sd);
      }
    }
  }

  /**
   * Generate a move.
   * 
   * @param s
   *          the source square
   * @param sd
   *          the destination square
   */
  private static void generateMove(int s, int sd) {
    System.out.println(SquareNames.toName(s) + "-" + SquareNames.toName(sd));
  }

  /** Nothing to see here. */
  ChessBoard() {
  }

  /**
   * Create a chess board using a FEN diagram.
   * 
   * @param fen
   *          A Chess position in FEN
   */
  ChessBoard(String fen) {
    FENUtil f = new FENUtil(this);
    f.processFENString(fen);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    builder.append("ChessBoard [pieceList=").append(this.pieceList).append("]\n");

    for (int rank = 7; rank >= 0; rank--) {
      for (int file = 0; file < 8; file++) {

        int sq0x88 = Board0x88Util.fileAndRankToSquare(file, rank);

        if (this.board[sq0x88] == null) {
          builder.append(".");
        } else {
          builder.append(this.board[sq0x88]);
        }
      }
      builder.append("\n");
    }

    return builder.toString();
  }

  @Override
  public void addPiece(PieceType p, Color c, int file, int rank) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setToMove(Color s) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setCastling(PieceType king, Color white, boolean b) {
    // TODO Auto-generated method stub

  }
}
