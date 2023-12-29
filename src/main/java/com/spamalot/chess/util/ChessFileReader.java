package com.spamalot.chess.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spamalot.chess.game.ChessGameState;

public final class ChessFileReader {
  /** Logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ChessFileReader.class);
  private static final Pattern COMMENT_REGEX = Pattern.compile("#.*");

  private ChessFileReader() {
  }

  static String cleanLine(String line) {
    return COMMENT_REGEX.matcher(line).replaceAll("").trim();
  }

  public static void processFEN(List<String> fenFiles) {
    for (String fenFile : fenFiles) {
      LOGGER.debug("Processing FEN strings in file : {}", fenFile);

      Path file = Paths.get(fenFile);

      try (BufferedReader br = Files.newBufferedReader(file, Charset.defaultCharset())) {
        String fenString;
        while ((fenString = br.readLine()) != null) {
          readFEN(fenFile, fenString);
        }
      } catch (IOException e) {
        LOGGER.error("Exception: ", e);
      }
    }
  }

  static void readFEN(String fenFile, String fen) {
    String fenString = cleanLine(fen);
    if (!StringUtils.isBlank(fenString)) {

      LOGGER.debug("FEN string from {} : {}", fenFile, fenString);
      ChessGameState game = new ChessGameState(fenString);

      LOGGER.debug("The game:\n{}", game);
    }
  }

}
