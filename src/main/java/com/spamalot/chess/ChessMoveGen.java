package com.spamalot.chess;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spamalot.chess.util.ChessFileReader;

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
   * @param args command line
   */
  public static void main(String[] args) {
    List<String> fenFiles = parseCommandLineArguments(args);

    ChessFileReader.processFEN(fenFiles);
  }

  /**
   * Parse command line arguments.
   *
   * @param args Arguments to parse.
   * @return a List of file names to read in.
   */
  private static List<String> parseCommandLineArguments(String... args) {
    Options options = new Options();
    options.addOption("f", "filetype", true, "filetype. fen is the only choice now.");
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
      // automatically generate the help statement

      HelpFormatter formatter = new HelpFormatter();

      formatter.printHelp("ChessMoveGen", options);

      usageAndExit();
    }
    return fenFiles;
  }

  /**
   * Print the usage.
   */
  private static void usageAndExit() {
    System.out.println("Usage: TBD");
    System.exit(0);
  }
}
