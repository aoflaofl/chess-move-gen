package com.spamalot.chess.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spamalot.chess.game.ChessGameState;

/**
 * Utility class for reading and processing FEN files.
 */
public final class ChessFileReader {
  /** Logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ChessFileReader.class);
  private static final Pattern COMMENT_REGEX = Pattern.compile("#.*");

  /** Private constructor to prevent instantiation. */
  private ChessFileReader() {
  }

  /**
   * Clean a line by removing comments and trimming whitespace.
   * 
   * @param line the line to clean
   * @return the cleaned line
   */
  static String cleanLine(String line) {
    return COMMENT_REGEX.matcher(line).replaceAll("").trim();
  }

  /**
   * Process a list of FEN files to extract chess game states.
   * 
   * @param fenFiles an iterable of file names containing FEN strings
   * @return a list of ChessGameState objects
   */
  public static List<ChessGameState> processFEN(Iterable<String> fenFiles) {
    List<ChessGameState> chessGameStates = new ArrayList<>();
    for (String fenFile : fenFiles) {
      LOGGER.debug("Processing FEN strings in file: {}", fenFile);
      Path file = Paths.get(fenFile);
      try (BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
        String fenString;
        while ((fenString = br.readLine()) != null) {
          Optional<ChessGameState> gameState = readFEN(fenFile, fenString);
          gameState.ifPresent(chessGameStates::add);
        }
      } catch (IOException e) {
        LOGGER.error("IOException while processing file {}: {}", fenFile, e.getMessage());
      }
    }
    return chessGameStates;
  }

  /**
   * Read a FEN string and convert it to a ChessGameState object.
   * 
   * @param fenFile the name of the file containing the FEN string
   * @param fen     the FEN string
   * @return an Optional containing the ChessGameState if the FEN string is valid,
   *         otherwise an empty Optional
   */
  static Optional<ChessGameState> readFEN(String fenFile, String fen) {
    String fenString = cleanLine(fen);
    if (!StringUtils.isBlank(fenString)) {
      LOGGER.debug("FEN string from {}: {}", fenFile, fenString);
      ChessGameState game = new ChessGameState(fenString);
      LOGGER.debug("The game:\n{}", game);
      return Optional.of(game);
    }
    return Optional.empty();
  }
}
