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

import com.spamalot.chess.game.ChessGameState;
import com.spamalot.chess.util.ChessFileReader;

/**
 * Entry class for Chess Move Generator program.
 */
public final class ChessMoveGen {

  private static final Logger LOGGER = LoggerFactory.getLogger(ChessMoveGen.class);

  private ChessMoveGen() {
    // Private constructor to prevent instantiation.
  }

  /**
   * Main entry point for the application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    List<String> fenFiles = parseCommandLineArguments(args);
    List<ChessGameState> gameStates = ChessFileReader.processFEN(fenFiles);

    gameStates.forEach(gameState -> LOGGER.info("ChessGameState: {}", gameState));
  }

  /**
   * Parses command line arguments.
   *
   * @param args Arguments to parse.
   * @return a List of file names to read.
   */
  private static List<String> parseCommandLineArguments(String... args) {
    Options options = new Options();
    options.addOption("f", "filetype", true, "filetype. FEN is the only choice now.");

    CommandLineParser parser = new DefaultParser();
    List<String> fenFiles = new ArrayList<>();

    try {
      CommandLine cmd = parser.parse(options, args);
      fenFiles.addAll(cmd.getArgList());
    } catch (ParseException parseException) {
      LOGGER.error("Failed to parse command line arguments", parseException);
      printHelpAndExit(options, 1);
    }

    if (fenFiles.isEmpty()) {
      printHelpAndExit(options, 1);
    }
    return fenFiles;
  }

  /**
   * Prints help information and exits the application.
   *
   * @param options Command line options
   * @param status  Exit status code
   */
  private static void printHelpAndExit(Options options, int status) {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("ChessMoveGen", options);
    System.exit(status);
  }
}
