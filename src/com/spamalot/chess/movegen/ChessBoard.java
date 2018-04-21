package com.spamalot.chess.movegen;

import com.spamalot.chess.base.Color;
import com.spamalot.chess.base.PieceType;
import com.spamalot.chess.fen.FENUtil;
import com.spamalot.chess.fen.FENboardable;

import java.util.LinkedList;
import java.util.List;

/**
 * I'm just going to jump in and start doing an 0x88 board and refactor later.
 * 
 * <p>I'll give it a catchy name: "Spacky"
 * 
 * <p>First goal: Make it solve mate in X problems. This will require move
 * generation and tree traversing code.
 * 
 * <p>WCGW?
 * 
 * @author gej
 *
 */
final class ChessBoard implements FENboardable {
  /** Hold the 0x88 representation of the board. */
  private ChessPiece[] board = new ChessPiece[128];

  /** Linked List of White Chess pieces currently on the board. */
  private LinkedList<PieceNode> whitePieceList = new LinkedList<>();
  /** Linked List of Black Chess pieces currently on the board. */
  private LinkedList<PieceNode> blackPieceList = new LinkedList<>();

  /** Orthogonal (up and down, right and left) diffs. */
  static final int[] ORTHO_DIFF = new int[] { -1, -16, 16, 1 };
  /** Diagonal diffs. */
  static final int[] DIAG_DIFF = new int[] { 17, 15, -17, -15 };
  /** Knight diffs. */
  static final int[] KNIGHT_DIFF = new int[] { 14, 18, 31, 33, -18, -14, -31, -33 };

  /**
   * Generate moves for sliding pieces (Queens, Bishops, Rooks).
   * 
   * @param s
   *          the Square
   * @param d
   *          the direction array
   */
  static void generateSliderMoves(final int s, final int[] d) {
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
  static void generateJumperMoves(final int s, final int[] d) {
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
  private static void generateMove(final int s, final int sd) {
    System.out.println(SquareName.toName(s) + '-' + SquareName.toName(sd));
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
  ChessBoard(final String fen) {
    if (fen.length() != 0) {
      FENUtil f = new FENUtil(this);
      f.processFENString(fen);
    }
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    builder.append("ChessBoard [whitePieceList=").append(this.whitePieceList).append("], [blackPieceList=")
        .append(this.blackPieceList).append("]\n");

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

    builder.append(buildMoveList());

    return builder.toString();
  }

  private List<ChessMove> buildMoveList() {
    for (PieceNode s : pieceList) {
      System.out.println(s);
    }
    return null;
  }

  /**
   * Put a Chess piece on the board.
   * 
   * @param p
   *          the Chess Piece
   * @param sq
   *          the Piece's square
   */
  private void addPiece(final ChessPiece p, final int sq) {
    if (p.getColor() == Color.WHITE) {
      this.whitePieceList.offer(new PieceNode(p, sq));
    } else {
      this.blackPieceList.offer(new PieceNode(p, sq));
    }

    this.board[sq] = p;
  }

  @Override
  public void addPiece(final PieceType p, final Color c, final int file, final int rank) {
    addPiece(new ChessPiece(c, p), Board0x88Util.fileAndRankToSquare(file - 1, rank - 1));
  }

  @Override
  public void setToMove(final Color s) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setCastling(final PieceType king, final Color white, final boolean b) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setEnPassantSquare(final int file, final int rank) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setHalfMovesSinceCaptureOrPawnMove(final int intValue) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setMoveNumber(final int intValue) {
    // TODO Auto-generated method stub

  }
}
