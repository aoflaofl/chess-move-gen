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

  /** Number of ranks on a chess board. */
  private static final int BOARD_SIZE = 8;

  /** Number of parts in a valid FEN string. */
  private static final int FEN_PARTS_COUNT = 6;

  /** Character indicating no special conditions in FEN. */
  private static final String NO_CONDITION_MARKER = "-";

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
    checkArgument(fenParts.length == FEN_PARTS_COUNT,
        "FEN String does not have correct number of parts.  Needed : %s Actual : %s", FEN_PARTS_COUNT, fenParts.length);
    pieceSetup(board, fenParts[0].split("/"));
    Color toMoveColor = Color.toColor(fenParts[1]);
    board.setToMove(toMoveColor);
    toCastle(board, fenParts[2]);
    enPassantSquare(board, toMoveColor, fenParts[3]);
    board.setHalfMovesSinceCaptureOrPawnMove(Integer.parseInt(fenParts[4]));
    board.setMoveNumber(Integer.parseInt(fenParts[5]));
  }

  private static void pieceSetup(ChessGameState board, String[] ranks) {
    checkArgument(ranks.length == BOARD_SIZE, "FEN Board String does not have enough ranks : " + ranks.length);
    int rank = BOARD_SIZE;
    for (String r : ranks) {
      processFENRow(board, r, rank);
      rank--;
    }
  }

  /**
   * Extract en-passant square from FEN string.
   *
   * @param board       board to work on
   * @param toMoveColor
   * @param string      En-passant square part of FEN string
   */
  private static void enPassantSquare(ChessGameState board, Color toMoveColor, String string) {
    checkNotNull(string);

    if (NO_CONDITION_MARKER.equals(string)) {
      return;
    }

    checkArgument(string.length() == 2, "Invalid en-passant square: %s", string);

    int file = string.charAt(0) - 'a';
    int rank = string.charAt(1) - '1';
    checkArgument(file >= 0 && file < BOARD_SIZE, "Invalid file in en-passant square: %s", string);
    checkArgument(rank >= 0 && rank < BOARD_SIZE, "Invalid rank in en-passant square: %s", string);

    LOGGER.info("En-passant square: color {}, file {}, rank {}", toMoveColor, file, rank);
    checkArgument((toMoveColor == Color.WHITE && rank == 5) || (toMoveColor == Color.BLACK && rank == 2),
        "Invalid rank for en-passant square for color %s: %s", toMoveColor, string);

    // Store file and rank using zero-based indexing.
    board.setEnPassantSquare(file, rank);
  }

  /**
   * Parse the castling part of the FEN String. In FEN notation: - 'K'/'k'
   * represents kingside castling for white/black - 'Q'/'q' represents queenside
   * castling for white/black - '-' indicates no castling rights
   *
   * @param board          board to work on
   * @param castlingString the String describing castling rights
   * @throws IllegalArgumentException if the castling string contains invalid
   *                                  characters
   */
  private static void toCastle(ChessGameState board, String castlingString) {
    checkNotNull(board, "Board cannot be null");
    checkNotNull(castlingString, "Castling string cannot be null");
    if (NO_CONDITION_MARKER.equals(castlingString)) {
      return;
    }
    for (char ch : castlingString.toCharArray()) {
      Color color = Character.isUpperCase(ch) ? Color.WHITE : Color.BLACK;
      char upperCh = Character.toUpperCase(ch);
      switch (upperCh) {
      case 'K':
        board.setCastling(PieceType.KING, color, true);
        break;
      case 'Q':
        board.setCastling(PieceType.QUEEN, color, true);
        break;
      default:
        throw new IllegalArgumentException(
            String.format("Invalid castling character: '%c'. Expected K, k, Q, or q", ch));
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
