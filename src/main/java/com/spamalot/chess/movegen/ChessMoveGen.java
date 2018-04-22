package com.spamalot.chess.movegen;

import com.google.common.io.Files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
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
    // Handle command line options.
    Options options = new Options();
    CommandLineParser parser = new DefaultParser();

    List<String> fenFiles = new ArrayList<>();
    try {
      CommandLine s = null;
      s = parser.parse(options, args);
      fenFiles.addAll(s.getArgList());

    } catch (ParseException e1) {
      usageAndExit();
      e1.printStackTrace();
    }

    if (fenFiles.size() == 0) {
      usageAndExit();
    }

    for (String fenFile : fenFiles) {
      logger.info("Processing FEN strings in file : {}", fenFile);
    }

    File file = new File("./fen.txt");

    try (BufferedReader br = Files.newReader(file, Charset.defaultCharset())) {
      String st;
      while ((st = br.readLine()) != null) {
        // Ignore empty lines. TODO: Add comment ignoring.
        if (st.trim().length() == 0) {
          continue;
        }

        logger.info("Read FEN string from file: {}", st);
        ChessBoard b = new ChessBoard(st);
        String boardString = b.toString();
        logger.info("The generated board:\n{}", boardString);
      }
    } catch (FileNotFoundException e) {
      usageAndExit();
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Print the usage.
   */
  private static void usageAndExit() {
    System.out.println("Blah Blah");
    System.exit(0);
  }
}
