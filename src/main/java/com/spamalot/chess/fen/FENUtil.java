package com.spamalot.chess.fen;

import com.spamalot.chess.base.Color;
import com.spamalot.chess.base.PieceType;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Process Forsythe Edwards Notation for Chess positions.
 * 
 * <p>TODO: call a method in board to clear board? Or make it a prereq to be
 * empty?
 * 
 * @author gej
 *
 */
public final class FENUtil {
  /** Logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(FENUtil.class);

  /**
   * Don't construct the FEN parser.
   */
  private FENUtil() {
  }

  /**
   * Parse a FEN String.
   * 
   * @param board
   *          the board object
   * @param fen
   *          the FEN String
   */
  public static void processFENString(final FENboardable board, final String fen) {
    if (StringUtils.isEmpty(fen)) {
      throw new IllegalArgumentException("Empty FEN String.");
    }

    if (board == null) {
      throw new IllegalArgumentException("Null Board object.");
    }

    String[] fenParts = fen.split(" ");

    if (fenParts.length != 6) {
      LOGGER.error("Size of FEN is {}", fenParts.length);
      throw new IllegalArgumentException(
          "FEN String does not have enough parts.  Needed : 6 Actual : " + fenParts.length);
    }

    String[] ranks = fenParts[0].split("/");

    if (ranks.length != 8) {
      LOGGER.error("Number of ranks is {}", ranks.length);
      throw new IllegalArgumentException("FEN Board String does not have enough ranks : " + ranks.length);
    }

    int rank = 8;
    for (String t : ranks) {
      processFENRow(board, t, rank);
      rank--;
    }

    Color s = toColor(fenParts[1]);
    board.setToMove(s);

    toCastle(board, fenParts[2]);
    enPassantSquare(board, fenParts[3]);
    board.setHalfMovesSinceCaptureOrPawnMove(halfMovesSinceCaptureOrPawnMove(fenParts[4]));
    board.setMoveNumber(moveNumber(fenParts[5]));

  }

  /**
   * Parse move number.
   * 
   * @param string
   *          Move number part of FEN string
   * @return move number.
   */
  private static int moveNumber(final String string) {
    return Integer.parseInt(string);
  }

  /**
   * Extract half moves since last Capture or Pawn move.
   * 
   * @param string
   *          Half move part of FEN string
   * @return half moves.
   */
  private static int halfMovesSinceCaptureOrPawnMove(final String string) {
    return Integer.parseInt(string);
  }

  /**
   * Extract en-passant square from FEN string.
   * 
   * @param board
   *          board to work on
   * @param string
   *          En-passant square part of FEN string
   */
  private static void enPassantSquare(final FENboardable board, final String string) {
    if (!"-".equals(string) && string.length() == 2) {
      int file = string.charAt(0) - 'a' + 1;
      int rank = string.charAt(1) - '0';
      board.setEnPassantSquare(file, rank);
    }
  }

  /**
   * Parse the castling part of the FEN String.
   * 
   * @param board
   *          board to work on
   * @param castlingString
   *          the String describing castling
   */
  private static void toCastle(final FENboardable board, final String castlingString) {
    if ("-".equals(castlingString)) {
      return;
    }

    for (char ch : castlingString.toCharArray()) {
      switch (ch) {
      case 'K':
        board.setCastling(PieceType.KING, Color.WHITE, true);
        break;
      case 'Q':
        board.setCastling(PieceType.QUEEN, Color.WHITE, true);
        break;
      case 'k':
        board.setCastling(PieceType.KING, Color.BLACK, true);
        break;
      case 'q':
        board.setCastling(PieceType.QUEEN, Color.BLACK, true);
        break;
      default:
        throw new IllegalStateException();
      }
    }

  }

  /**
   * Convert w or b to the Color.
   * 
   * @param colorString
   *          "w" or "b"
   * @return the Color.
   */
  private static Color toColor(final String colorString) {
    if ("w".equals(colorString)) {
      return Color.WHITE;
    }
    return Color.BLACK;
  }

  /**
   * Process a row of the board from the FEN String.
   * 
   * @param board
   *          board to work on
   * @param fenRow
   *          the FEN row String
   * @param rank
   *          the row's rank (1-8)
   */
  private static void processFENRow(final FENboardable board, final String fenRow, final int rank) {
    LOGGER.debug("Parsing FEN string : {}", fenRow);
    int file = 1;
    for (char s : fenRow.toCharArray()) {
      if (Character.isDigit(s)) {
        file = file + s - '0';
      } else {
        genPiece(board, s, file, rank);

        file++;
      }
    }
  }

  /**
   * Get a ChessPiece object from the FEN character and add it to the board.
   * 
   * @param board
   *          board to work on
   * @param pieceChar
   *          a character representing the piece in FEN
   * @param file
   *          the Piece's file (1-8)
   * @param rank
   *          the Piece's rank (1-8)
   */
  private static void genPiece(final FENboardable board, final char pieceChar, final int file, final int rank) {
    for (PieceType pt : PieceType.values()) {
      if (pt.getBlackChar() == pieceChar) {
        board.addPiece(pt, Color.BLACK, file, rank);
      }

      if (pt.getWhiteChar() == pieceChar) {
        board.addPiece(pt, Color.WHITE, file, rank);
      }
    }

  }

}
