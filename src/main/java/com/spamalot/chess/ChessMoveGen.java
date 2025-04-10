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
/**
 * The ChessMoveGen class serves as the main entry point for the chess move
 * generation application.
 * It processes command-line arguments, reads FEN files, and logs the resulting
 * chess game states.
 * 
 * <p>
 * This class is designed to be non-instantiable by having a private
 * constructor.
 * It provides utility methods for parsing command-line arguments and displaying
 * help information.
 * 
 * <p>
 * Features:
 * <ul>
 * <li>Parses command-line arguments to retrieve FEN file paths.</li>
 * <li>Processes FEN files to generate chess game states.</li>
 * <li>Logs the resulting game states for debugging or informational
 * purposes.</li>
 * <li>Displays help information when requested or when invalid arguments are
 * provided.</li>
 * </ul>
 * 
 * <p>
 * Usage:
 * 
 * <pre>
 * java -jar ChessMoveGen.jar -f <file1.fen> <file2.fen> ...
 * </pre>
 * 
 * <p>
 * Command-line options:
 * <ul>
 * <li><b>-f, --filetype</b>: Specifies the file type (currently only FEN is
 * supported).</li>
 * <li><b>-h, --help</b>: Displays help information.</li>
 * </ul>
 * 
 * <p>
 * Exceptions:
 * <ul>
 * <li>{@link ApplicationExitException}: Thrown to indicate an intentional
 * application exit with a specific status code.</li>
 * <li>{@link ParseException}: Caught and handled internally when command-line
 * arguments cannot be parsed.</li>
 * </ul>
 * 
 * <p>
 * Logging:
 * <ul>
 * <li>Logs errors and unexpected exceptions.</li>
 * <li>Logs the parsed chess game states for informational purposes.</li>
 * </ul>
 * 
 * <p>
 * Dependencies:
 * <ul>
 * <li>Apache Commons CLI for command-line argument parsing.</li>
 * <li>SLF4J for logging.</li>
 * </ul>
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
    try {
      List<String> fenFiles = parseCommandLineArguments(args);
      List<ChessGameState> gameStates = ChessFileReader.processFEN(fenFiles);
      gameStates.forEach(gameState -> LOGGER.info("ChessGameState: {}", gameState));
    } catch (ApplicationExitException e) {
      LOGGER.info("Exiting: {}", e.getMessage());
      System.exit(e.getStatusCode());
    } catch (Exception e) {
      LOGGER.error("Unexpected error", e);
      System.exit(1);
    }
  }

  /**
   * Parses command line arguments.
   *
   * @param args Arguments to parse.
   * @return a List of file names to read.
   * @throws ApplicationExitException
   */
  private static List<String> parseCommandLineArguments(String... args) throws ApplicationExitException {
    Options options = new Options();
    options.addOption("f", "filetype", true, "filetype. FEN is the only choice now.");
    options.addOption("h", "help", false, "display help information");

    CommandLineParser parser = new DefaultParser();
    List<String> fenFiles = new ArrayList<>();

    try {
      CommandLine cmd = parser.parse(options, args);

      // Check for help flag first
      if (cmd.hasOption("h")) {
        printHelpAndExit(options, 0);
      }

      fenFiles.addAll(cmd.getArgList());
    } catch (ParseException parseException) {
      LOGGER.error("Failed to parse command line arguments: {}", parseException.getMessage());
      printHelpAndExit(options, 1);
    }

    if (fenFiles.isEmpty()) {
      LOGGER.error("No input files specified");
      printHelpAndExit(options, 1);
    }
    return fenFiles;
  }

  /**
   * Prints help information and exits the application.
   *
   * @param options Command line options
   * @param status  Exit status code
   * @throws ApplicationExitException
   */
  private static void printHelpAndExit(Options options, int status) throws ApplicationExitException {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("ChessMoveGen", options);
    throw new ApplicationExitException("Application exit requested", status);
  }
}
