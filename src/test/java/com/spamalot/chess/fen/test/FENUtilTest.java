package com.spamalot.chess.fen.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.spamalot.chess.game.ChessGameStateImpl;
import com.spamalot.chess.util.FENUtil;

/**
 * Test the FENUtil class.
 *
 * @author gej
 *
 */
public class FENUtilTest {
  /** Good FEN for testing. */
  private static final String GOOD_FEN = "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2";

  private static final String GOOD_BOARD = "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R";
  private static final String BAD_BOARD = "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP";

  private static final String GOOD_COLOR_TO_MOVE = "b";

  private static final String GOOD_CASTLE = "KQkq";

  private static final String GOOD_EN_PASSANT = "-";

  private static final String GOOD_MOVES_SINCE_LAST_PAWN_OR_CAPTURE = "1";
  private static final String GOOD_MOVE_NUMBER = "2";

  /** Bad FEN. */
  private static final String TOO_FEW_PARTS_FEN = "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R KQkq - 1 2";

  /** For testing exceptions. */
  @Rule
  public ExpectedException exception = ExpectedException.none();

  /**
   * Test what happens when a FEN String is missing.
   */
  @Test
  public void testEmptyFENString() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Empty FEN String.");

    FENUtil.processFENString(null, null);
  }

  /**
   * Test what happens when a Board Object is null.
   */
  @Test
  public void testNullBoardElement() {
    exception.expect(NullPointerException.class);
    exception.expectMessage("Null Board object.");

    FENUtil.processFENString(null, String.join(" ", GOOD_BOARD, GOOD_COLOR_TO_MOVE, GOOD_CASTLE, GOOD_EN_PASSANT,
        GOOD_MOVES_SINCE_LAST_PAWN_OR_CAPTURE, GOOD_MOVE_NUMBER));
  }

  /**
   * Test what happens with badly formed FEN String.
   */
  @Test
  public void testTooFewFENPartsBoardElement() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("FEN String does not have enough parts.  Needed : 6 Actual : 5");

    FENUtil.processFENString(new ChessGameStateImpl(), TOO_FEW_PARTS_FEN);
  }

  @Test
  public void testGoodFEN() {
    FENUtil.processFENString(new ChessGameStateImpl(), String.join(" ", GOOD_BOARD, GOOD_COLOR_TO_MOVE, GOOD_CASTLE,
        GOOD_EN_PASSANT, GOOD_MOVES_SINCE_LAST_PAWN_OR_CAPTURE, GOOD_MOVE_NUMBER));
  }

  @Test
  public void testBadBoardFEN() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("FEN Board String does not have enough ranks : 7");

    FENUtil.processFENString(new ChessGameStateImpl(), String.join(" ", BAD_BOARD, GOOD_COLOR_TO_MOVE, GOOD_CASTLE,
        GOOD_EN_PASSANT, GOOD_MOVES_SINCE_LAST_PAWN_OR_CAPTURE, GOOD_MOVE_NUMBER));
  }
}
