package com.spamalot.chess.util;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spamalot.chess.game.ChessGameState;
import com.spamalot.chess.piece.Color;
import com.spamalot.chess.piece.PieceType;

/**
 * Process Forsythe Edwards Notation for Chess positions.
 *
 * <p>
 * TODO: call a method in board to clear board? Or make it a prereq to be empty?
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
   * @param board the board object
   * @param fen   the FEN String
   */
  public static void processFENString(ChessGameState board, String fen) {
    checkArgument(StringUtils.isNotBlank(fen), "Empty FEN String.");
    checkNotNull(board, "Null Board object.");

    processFENParts(board, fen.split(" "));
  }

  private static void processFENParts(ChessGameState board, String[] fenParts) {
    assert fenParts.length == 6
        : "FEN String does not have correct number of parts.  Needed : 6 Actual : " + fenParts.length;

    pieceSetup(board, fenParts[0].split("/"));
    board.setToMove(Color.toColor(fenParts[1]));
    toCastle(board, fenParts[2]);
    enPassantSquare(board, fenParts[3]);
    board.setHalfMovesSinceCaptureOrPawnMove(Integer.parseInt(fenParts[4]));
    board.setMoveNumber(Integer.parseInt(fenParts[5]));
  }

  private static void pieceSetup(ChessGameState board, String[] ranks) {
    checkArgument(ranks.length == 8, "FEN Board String does not have enough ranks : " + ranks.length);

    int rank = 8;
    for (String r : ranks) {
      processFENRow(board, r, rank);
      rank--;
    }
  }

  /**
   * Extract en-passant square from FEN string.
   *
   * @param board  board to work on
   * @param string En-passant square part of FEN string
   */
  private static void enPassantSquare(ChessGameState board, String string) {
    checkNotNull(string);
    checkArgument("-".equals(string));
    if (!"-".equals(string) && string.length() == 2) {
      int file = string.charAt(0) - 'a' + 1;
      int rank = string.charAt(1) - '0';
      board.setEnPassantSquare(file, rank);
    }
  }

  /**
   * Parse the castling part of the FEN String.
   *
   * @param board          board to work on
   * @param castlingString the String describing castling
   */
  private static void toCastle(ChessGameState board, String castlingString) {
    if (!"-".equals(castlingString)) {
      for (char ch : castlingString.toCharArray()) {
        Color color = Character.isUpperCase(ch) ? Color.WHITE : Color.BLACK;
        PieceType pieceType = (ch == 'K' || ch == 'k') ? PieceType.KING : PieceType.QUEEN;
        board.setCastling(pieceType, color, true);
      }
    }
  }

  /**
   * Process a row of the board from the FEN String.
   *
   * @param board  board to work on
   * @param fenRow the FEN row String
   * @param rank   the row's rank (1-8)
   */
  private static void processFENRow(ChessGameState board, String fenRow, int rank) {
    LOGGER.debug("Parsing FEN string : {}", fenRow);
    int file = 0;
    for (char s : fenRow.toCharArray()) {
      if (Character.isDigit(s)) {
        file += Character.getNumericValue(s);
      } else {
        genPiece(board, s, file++, rank - 1);
      }
    }
  }

  /**
   * Get a ChessPiece object from the FEN character and add it to the board.
   *
   * @param board     board to work on
   * @param pieceChar a character representing the piece in FEN
   * @param file      the Piece's file (1-8)
   * @param rank      the Piece's rank (1-8)
   */
  private static void genPiece(ChessGameState board, char pieceChar, int file, int rank) {
    PieceType pieceType = PieceType.fromChar(pieceChar);
    Color color = Character.isUpperCase(pieceChar) ? Color.WHITE : Color.BLACK;
    board.addPiece(pieceType, color, file, rank);
  }

}
