package com.spamalot.chess;

import com.spamalot.chess.movegen.ChessBoard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
  /** Logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ChessMoveGen.class);

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
    List<String> fenFiles = parseCommandLineArguments(args);

    for (String fenFile : fenFiles) {
      LOGGER.info("Processing FEN strings in file : {}", fenFile);

      Path file = Paths.get(fenFile);

      try (BufferedReader br = Files.newBufferedReader(file, Charset.defaultCharset())) {
        String fenString;
        while ((fenString = br.readLine()) != null) {
          // Ignore empty lines and comments.
          fenString = fenString.replaceAll("#.*", "").trim();
          if (fenString.length() == 0) {
            continue;
          }

          LOGGER.info("FEN string from {} : {}", fenFile, fenString);
          ChessBoard b = new ChessBoard(fenString);
          String boardString = b.toString();
          LOGGER.info("The generated board:\n{}", boardString);
        }
      } catch (FileNotFoundException e) {
        usageAndExit();
        LOGGER.error("Exception: ", e);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        LOGGER.error("Exception: ", e);
      }
    }
  }

  /**
   * Parse command line arguments.
   * 
   * @param args
   *          Arguments to parse.
   * @return a List of file names to read in.
   */
  private static List<String> parseCommandLineArguments(final String... args) {

    Options options = new Options();
    CommandLineParser parser = new DefaultParser();

    List<String> fenFiles = new ArrayList<>();
    try {
      CommandLine s = null;
      s = parser.parse(options, args);
      fenFiles.addAll(s.getArgList());

    } catch (ParseException e1) {
      usageAndExit();
      LOGGER.error("Exception: ", e1);
    }

    if (fenFiles.isEmpty()) {
      usageAndExit();
    }
    return fenFiles;
  }

  /**
   * Print the usage.
   */
  private static void usageAndExit() {
    System.out.println("Blah Blah");
    System.exit(0);
  }
}
