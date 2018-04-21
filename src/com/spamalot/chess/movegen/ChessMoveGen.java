package com.spamalot.chess.movegen;

import com.google.common.io.Files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entry class for Chess Move Generator program.
 * 
 * @author gej
 *
 */
public final class ChessMoveGen {
  private static final Logger logger = LoggerFactory.getLogger(ChessMoveGen.class);

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

    try (BufferedReader br = Files.newReader(file, Charset.defaultCharset())) {
      String st;
      while ((st = br.readLine()) != null) {
        // Ignore empty lines.
        // TODO: Add comment ignoring.
        if (st.trim().length() == 0) {
          continue;
        }
        
        logger.info("{}", st);
        ChessBoard b = new ChessBoard(st);
        logger.info("{}", b);
      }
    } catch (FileNotFoundException e) {
      usage();
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Print the usage.
   */
  private static void usage() {
    System.out.println("Blah Blah");
  }
}
