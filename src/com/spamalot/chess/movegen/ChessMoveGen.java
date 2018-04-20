package com.spamalot.chess.movegen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Entry class for Chess Move Generator program.
 * 
 * @author gej
 *
 */
public final class ChessMoveGen {
  /** Private constructor. */
  private ChessMoveGen() {
  }

  /**
   * Start here.
   * 
   * @param args
   *          command line
   */
  public static void main(final String[] args) {

    File file = new File("./fen.txt");

    BufferedReader br;
    try {
      br = new BufferedReader(new FileReader(file));
      String st;
      try {
        while ((st = br.readLine()) != null) {
          System.out.println(st);
          ChessBoard b = new ChessBoard(st);
          System.out.println(b);
        }
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }

}
