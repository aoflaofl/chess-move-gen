package com.spamalot.chess.util;

import com.spamalot.chess.game.ChessGameState;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ChessFileReader {

  private static final Logger LOGGER = LoggerFactory.getLogger(ChessFileReader.class);
  private static final Pattern COMMENT_REGEX = Pattern.compile("#.*");

  private ChessFileReader() {
    // Private constructor to prevent instantiation.
  }

  /**
   * Processes a list of FEN (Forsyth-Edwards Notation) files and returns a list
   * of ChessGameState objects. Each file is read line by line, with each line
   * representing a FEN string. Each FEN string is converted to a ChessGameState
   * object and added to the list. If an IOException occurs while processing a
   * file, an error message is logged and the file is skipped.
   *
   * @param fenFiles An Iterable of file paths, each file contains FEN strings.
   * @return A List of ChessGameState objects, each representing a FEN string from
   *         the files.
   */
  public static List<ChessGameState> processFen(Iterable<String> fenFiles) {
    List<ChessGameState> chessGameStates = new ArrayList<>();
    fenFiles.forEach(fenFile -> processSingleFenFile(fenFile, chessGameStates));
    return chessGameStates;
  }

  private static void processSingleFenFile(String fenFile, List<ChessGameState> chessGameStates) {
    LOGGER.debug("Processing FEN strings in file: {}", fenFile);
    try (Stream<String> fenStream = Files.lines(Paths.get(fenFile), StandardCharsets.UTF_8)) {
      fenStream.map(fen -> readFen(fen)).filter(Optional::isPresent).map(Optional::get).forEach(chessGameStates::add);
    } catch (IOException e) {
      LOGGER.error("IOException while processing file {}: {}", fenFile, e.getMessage());
    }
  }

  private static Optional<ChessGameState> readFen(String fen) {
    String fenString = cleanLine(fen);
    if (fenString.isEmpty()) {
      return Optional.empty();
    }

    LOGGER.debug("FEN string : {}", fenString);
    ChessGameState game = createChessGameState(fenString);
    // LOGGER.debug("The game:\n{}", game);

    return Optional.of(game);
  }

  private static ChessGameState createChessGameState(String fenString) {
    ChessGameState game = new ChessGameState();
    FenUtil.processFenString(game, fenString);
    return game;
  }

  private static String cleanLine(String line) {
    return COMMENT_REGEX.matcher(line).replaceAll("").trim();
  }
}
