package com.spamalot.chess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spamalot.chess.game.ChessGameState;
import com.spamalot.chess.game.ChessGameStateImpl;

/**
 * Entry class for Chess Move Generator program.
 *
 * @author gej
 *
 */
public class ChessMoveGen {
  private static final Pattern COMMENT_REGEX = Pattern.compile("#.*");

  /** Logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ChessMoveGen.class);

  /** Private constructor. */
  private ChessMoveGen() {
  }

  private static final String cleanLine(String line) {
    return COMMENT_REGEX.matcher(line).replaceAll("").trim();
  }

  /**
   * Start here.
   *
   * @param args command line
   */
  public static void main(String[] args) {
    List<String> fenFiles = parseCommandLineArguments(args);

    processFEN(fenFiles);
  }

  /**
   * Parse command line arguments.
   *
   * @param args Arguments to parse.
   * @return a List of file names to read in.
   */
  private static final List<String> parseCommandLineArguments(String... args) {
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

  private static final void processFEN(List<String> fenFiles) {
    for (String fenFile : fenFiles) {
      LOGGER.debug("Processing FEN strings in file : {}", fenFile);

      Path file = Paths.get(fenFile);

      try (BufferedReader br = Files.newBufferedReader(file, Charset.defaultCharset())) {
        String fenString;
        while ((fenString = br.readLine()) != null) {
          readFEN(fenFile, fenString);
        }
      } catch (FileNotFoundException e) {
        usageAndExit();
        LOGGER.error("Exception: ", e);
      } catch (IOException e) {
        LOGGER.error("Exception: ", e);
      }
    }
  }

  private static final void readFEN(String fenFile, String fen) {
    String fenString = cleanLine(fen);
    if (!StringUtils.isBlank(fenString)) {

      LOGGER.debug("FEN string from {} : {}", fenFile, fenString);
      ChessGameState game = new ChessGameStateImpl(fenString);

      LOGGER.debug("The game:\n{}", game);
    }
  }

  /**
   * Print the usage.
   */
  private static final void usageAndExit() {
    System.out.println("Usage: TBD");
    System.exit(0);
  }
}
