package com.spamalot.chess.fen;

import com.spamalot.chess.Color;
import com.spamalot.chess.PieceType;
import com.spamalot.chess.movegen.ChessPiece;

import java.util.ArrayList;
import java.util.List;

/**
 * Process Forsythe Edwards Notation for Chess positions.
 * 
 * @author gej
 *
 */
public final class FENUtil {
  private List<FENChessPiece> pieces = new ArrayList<>();
  private FENboardable board;

  /** Why? */
  public FENUtil(FENboardable board) {
    this.board = board;
  }

  public void processFENString(String fen) {
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

  }

  private void toCastle(String string) {
    if ("-".equals(string)) {
      return;
    }

    for (char ch : string.toCharArray()) {
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
      }
    }

  }

  private Color toColor(String string) {
    if ("w".equals(string)) {
      return Color.WHITE;
    }
    return Color.BLACK;
  }

  private void processFENRow(String fenRow, int rank) {
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
   * Get a ChessPiece object from the FEN character.
   * 
   * @param pieceChar
   *          a character representing the piece in FEN
   * @param rank
   * @param file
   * @return Chess Piece of the piece argument.
   */
  private void genPiece(char pieceChar, int file, int rank) {
    for (PieceType pt : PieceType.values()) {
      if (pt.blackChar == pieceChar) {
        this.board.addPiece(pt, Color.BLACK, file, rank);
      }

      if (pt.whiteChar == pieceChar) {
        this.board.addPiece(pt, Color.BLACK, file, rank);
      }
    }

  }

}
