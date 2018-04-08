package com.spamalot.chess.fen;

import com.spamalot.chess.base.Color;
import com.spamalot.chess.base.PieceType;

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
  /** Board that can be updated with information from FEN String. */
  private FENboardable board;

  /**
   * Construct the FEN parser.
   * 
   * @param b
   *          an object that can be updated with FEN information
   */
  public FENUtil(final FENboardable b) {
    this.board = b;
  }

  /**
   * Parse a FEN String.
   * 
   * @param fen
   *          the FEN String
   */
  public void processFENString(final String fen) {
    String[] x = fen.split(" ");

    String[] ranks = x[0].split("/");

    int rank = 8;
    for (String t : ranks) {
      processFENRow(t, rank);
      rank--;
    }

    Color s = toColor(x[1]);
    this.board.setToMove(s);

    toCastle(x[2]);

    enPassantSquare(x[3]);

    halfMovesSinceCaptureOrPawnMove(x[4]);

    moveNumber(x[5]);

  }

  /**
   * Extract move number.
   * 
   * @param string
   *          Move number part of FEN string
   */
  private void moveNumber(final String string) {
    this.board.setMoveNumber(Integer.parseInt(string));

  }

  /**
   * Extract half moves since last Capture or Pawn move.
   * 
   * @param string
   *          Half move part of FEN string
   */
  private void halfMovesSinceCaptureOrPawnMove(final String string) {
    this.board.setHalfMovesSinceCaptureOrPawnMove(Integer.parseInt(string));
  }

  /**
   * Extract en-passant square from FEN string.
   * 
   * @param string
   *          En-passant square part of FEN string
   */
  private void enPassantSquare(final String string) {
    if (!"-".equals(string) && string.length() == 2) {
      int file = string.charAt(0) - 'a' + 1;
      int rank = string.charAt(1) - '0';

      this.board.setEnPassantSquare(file, rank);
    }
  }

  /**
   * Parse the castling part of the FEN String.
   * 
   * @param castlingString
   *          the String describing castling
   */
  private void toCastle(final String castlingString) {
    if ("-".equals(castlingString)) {
      return;
    }

    for (char ch : castlingString.toCharArray()) {
      switch (ch) {
        case 'K':
          this.board.setCastling(PieceType.KING, Color.WHITE, true);
          break;
        case 'Q':
          this.board.setCastling(PieceType.QUEEN, Color.WHITE, true);
          break;
        case 'k':
          this.board.setCastling(PieceType.KING, Color.BLACK, true);
          break;
        case 'q':
          this.board.setCastling(PieceType.QUEEN, Color.BLACK, true);
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
   * @param fenRow
   *          the FEN row String
   * @param rank
   *          the row's rank (1-8)
   */
  private void processFENRow(final String fenRow, final int rank) {
    System.out.println(fenRow);
    int file = 1;
    for (char s : fenRow.toCharArray()) {
      if (Character.isDigit(s)) {
        file = file + s - '0';
      } else {
        genPiece(s, file, rank);
        System.out.println(rank + ":" + file);

        file++;
      }
    }
  }

  /**
   * Get a ChessPiece object from the FEN character and add it to the board.
   * 
   * @param pieceChar
   *          a character representing the piece in FEN
   * @param file
   *          the Piece's file (1-8)
   * @param rank
   *          the Piece's rank (1-8)
   */
  private void genPiece(final char pieceChar, final int file, final int rank) {
    for (PieceType pt : PieceType.values()) {
      if (pt.getBlackChar() == pieceChar) {
        this.board.addPiece(pt, Color.BLACK, file, rank);
      }

      if (pt.getWhiteChar() == pieceChar) {
        this.board.addPiece(pt, Color.BLACK, file, rank);
      }
    }

  }

}
