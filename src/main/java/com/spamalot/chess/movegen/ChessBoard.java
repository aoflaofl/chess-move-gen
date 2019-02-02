package com.spamalot.chess.movegen;

import com.spamalot.chess.base.Color;
import com.spamalot.chess.base.PieceType;
import com.spamalot.chess.fen.FENUtil;
import com.spamalot.chess.fen.FENboardable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public final class ChessBoard implements FENboardable {
  /** Logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ChessBoard.class);

  /** Diagonal diffs. */
  private static final int[] DIAG_DIFF = new int[] { 17, 15, -17, -15 };

  /** Knight diffs. */
  static final int[] KNIGHT_DIFF = new int[] { 14, 18, 31, 33, -18, -14, -31, -33 };

  /** Orthogonal (up and down, right and left) diffs. */
  private static final int[] ORTHO_DIFF = new int[] { -1, -16, 16, 1 };

  /** Linked List of Black Chess pieces currently on the board. */
  private LinkedList<PieceNode> blackPieceList = new LinkedList<>();

  /** Hold the 0x88 representation of the board. */
  private ChessPiece[] board = new ChessPiece[128];

  /** The Color whose move it is. */
  private Color toMove;

  /** Linked List of White Chess pieces currently on the board. */
  private LinkedList<PieceNode> whitePieceList = new LinkedList<>();

  /** Nothing to see here. */
  public ChessBoard() {
  }

  /**
   * Create a chess board using a FEN diagram.
   * 
   * @param fen
   *          A Chess position in FEN
   */
  public ChessBoard(final String fen) {
    if (fen.length() != 0) {
      LOGGER.info("Constructing a ChessBoard using FEN String.");
      try {
        FENUtil.processFENString(this, fen);
      } catch (Exception e) {
        LOGGER.error("Problem with FEN string : ", e);
      }
    } else {
      LOGGER.info("No FEN String received.");
    }
  }

  /**
   * Generate moves for single move pieces (Knights, Kings).
   * 
   * @param s
   *          the Square
   * @param d
   *          the direction array
   * @return the move list.
   */
  private List<ChessMove> generateJumperMoves(final int s, final int[] d) {
    List<ChessMove> m = new ArrayList<>();
    int sd;
    for (int dir : d) {

      sd = s + dir;

      if (canMoveToSquare(sd)) {
        m.add(generateMove(s, sd));
      }
    }
    return m;
  }

  /**
   * Check if a piece of the current color can legally move to a square. Does not
   * check if King will be attacked if it moves here.
   * 
   * @param sd
   *          Destination Square
   * @return true if the square can be moved to.
   */
  private boolean canMoveToSquare(final int sd) {
    boolean result = true;
    if (!(Board0x88Util.isOnBoard(sd) && (getPiece(sd) == null || getPiece(sd).getColor() != this.toMove))) {

      result = false;

    }
    return result;
  }

  /**
   * Get the piece in a square.
   * 
   * @param sd
   *          the Square
   * @return the Piece.
   */
  private ChessPiece getPiece(final int sd) {
    return this.board[sd];
  }

  /**
   * Generate a move.
   * 
   * @param s
   *          the source square
   * @param sd
   *          the destination square
   * @return a chess move.
   */
  private static ChessMove generateMove(final int s, final int sd) {
    LOGGER.info("Generate move : {}-{}", SquareName.toName(s), SquareName.toName(sd));
    return new ChessMove(s, sd);
  }

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

  /**
   * Get the list of chess moves available on the board.
   * 
   * @return a List of Chess Moves.
   */
  private List<ChessMove> buildMoveList() {
    List<ChessMove> m = new ArrayList<>();
    List<PieceNode> pieceList;
    if (this.toMove == Color.WHITE) {
      pieceList = this.whitePieceList;
    } else {
      pieceList = this.blackPieceList;
    }

    for (PieceNode s : pieceList) {
      // System.out.println(s);
      PieceType pt = s.getPieceType();

      LOGGER.info("Piece {}", s);
      switch (pt) {
        case KING:
          m.addAll(generateJumperMoves(s.get0x88Square(), ORTHO_DIFF));
          m.addAll(generateJumperMoves(s.get0x88Square(), DIAG_DIFF));

          break;
        case BISHOP:
          break;
        case KNIGHT:
          break;
        case PAWN:
          break;
        case QUEEN:
          break;
        case ROOK:
          break;
        default:
          break;
      }

    }

    return m;
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

  @Override
  public void setToMove(final Color s) {
    this.toMove = s;
  }

  @Override
  public String toString() {
    LOGGER.debug("toString()");
    StringBuilder builder = new StringBuilder();

    builder.append("ChessBoard [whitePieceList=").append(this.whitePieceList).append(",\n            blackPieceList=")
        .append(this.blackPieceList).append(",\n            toMove=").append(this.toMove).append("]\n");

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
}
