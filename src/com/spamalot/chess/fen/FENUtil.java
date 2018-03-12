package com.spamalot.chess.fen;

import com.spamalot.chess.movegen.ChessPiece;
import com.spamalot.chess.movegen.PieceColor;
import com.spamalot.chess.movegen.PieceType;

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

    PieceColor s = toColor(x[1]);
    board.setToMove(s);

  }

  private PieceColor toColor(String string) {
    if ("w".equals(string))
      return PieceColor.WHITE;
    return PieceColor.BLACK;
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
        this.board.addPiece(pt, PieceColor.BLACK, file, rank);
      }

      if (pt.whiteChar == pieceChar) {
        this.board.addPiece(pt, PieceColor.BLACK, file, rank);
      }
    }

  }

}
