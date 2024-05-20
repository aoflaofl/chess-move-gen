package com.spamalot.chess.game;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spamalot.chess.lib0x88.ChessBoard0x88;
import com.spamalot.chess.lib0x88.ChessBoardUtil0x88;
import com.spamalot.chess.movegen.ChessMove;
import com.spamalot.chess.movegen.PieceNode;
import com.spamalot.chess.piece.ChessPiece;
import com.spamalot.chess.piece.Color;
import com.spamalot.chess.piece.PieceType;
import com.spamalot.chess.util.FENUtil;

// TODO: Add builder for FEN.

/**
 * I'm just going to jump in and start doing an 0x88 board and refactor later.
 *
 * <p>
 * I'll give it a catchy name: "Spacky"
 *
 * <p>
 * First goal: Make it solve mate in X problems. This will require move
 * generation and tree traversing code.
 *
 * <p>
 * WCGW?
 *
 * @author gej
 *
 */
public class ChessGameState {
  /** Logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ChessGameState.class);

  /** Diagonal diffs. */
  private static final int[] DIAG_DIFF = new int[] { 17, 15, -17, -15 };

  /** Knight diffs. */
  private static final int[] KNIGHT_DIFF = new int[] { 14, 18, 31, 33, -18, -14, -31, -33 };

  /** Orthogonal (up and down, right and left) diffs. */
  private static final int[] ORTHO_DIFF = new int[] { -1, -16, 16, 1 };

  /** Linked List of Black Chess pieces currently on the board. */
  private LinkedList<PieceNode> blackPieceList = new LinkedList<>();

  /** The Board being used for this game. */
  private ChessBoard0x88 board = new ChessBoard0x88();

  /** The Color whose move it is. */
  private Color toMove;

  /** Linked List of White Chess pieces currently on the board. */
  private LinkedList<PieceNode> whitePieceList = new LinkedList<>();

  /** Nothing to see here. */
  public ChessGameState() {
  }

  /**
   * Create a chess board using a FEN diagram.
   *
   * @param fen A Chess position in FEN
   */
  public ChessGameState(String fen) {
    checkArgument(StringUtils.isNotBlank(fen), "Empty FEN");

    LOGGER.info("Constructing a ChessBoard using FEN String.");
    FENUtil.processFENString(this, fen);
  }

  /**
   * Generate moves for single move pieces (Knights, Kings).
   *
   * @param s the Square
   * @param d the direction array
   * @return the move list.
   */
  private static List<ChessMove> generateJumperMoves(int s, int[] d) {
    List<ChessMove> m = new ArrayList<>();
    int sd;
    for (int dir : d) {

      sd = s + dir;

//      if (board.canMoveToSquare(sd)) {
//        m.add(generateMove(s, sd));
//      }
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

  /**
   * Put a Chess piece on the board.
   *
   * @param p    the Chess Piece
   * @param file file
   * @param rank rank
   */
  private void addPiece(ChessPiece p, int file, int rank) {
    if (p.getColor() == Color.WHITE) {
      whitePieceList.offer(new PieceNode(p, file, rank));
    } else {
      blackPieceList.offer(new PieceNode(p, file, rank));
    }

    board.addToBoard(p, file, rank);
  }

  public void addPiece(PieceType p, Color c, int file, int rank) {
    addPiece(new ChessPiece(c, p), file, rank);
  }

  /**
   * Get the list of chess moves available on the board.
   *
   * @return a List of Chess Moves.
   */
  private List<ChessMove> buildMoveList() {
    List<ChessMove> m = new ArrayList<>();
    List<PieceNode> pieceList = getSideToMovePieceList();

    for (PieceNode s : pieceList) {
      PieceType pt = s.getPiece().getType();

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

  private List<PieceNode> getSideToMovePieceList() {
    List<PieceNode> pieceList;
    if (toMove == Color.WHITE) {
      pieceList = whitePieceList;
    } else {
      pieceList = blackPieceList;
    }
    return pieceList;
  }

  public void setCastling(PieceType king, Color white, boolean b) {
    // TODO Auto-generated method stub

  }

  public void setEnPassantSquare(int file, int rank) {
    // TODO Auto-generated method stub

  }

  public void setHalfMovesSinceCaptureOrPawnMove(int intValue) {
    // TODO Auto-generated method stub

  }

  public void setMoveNumber(int intValue) {
    // TODO Auto-generated method stub

  }

  public void setToMove(Color s) {
    toMove = s;
  }

  @Override
  public String toString() {
    LOGGER.debug("toString()");
    StringBuilder builder = new StringBuilder();

    builder.append("ChessBoard [whitePieceList=").append(whitePieceList).append(",\n            blackPieceList=")
        .append(blackPieceList).append(",\n            toMove=").append(toMove).append("]\n");

    builder.append(board);

    builder.append(buildMoveList());

    return builder.toString();
  }
}
