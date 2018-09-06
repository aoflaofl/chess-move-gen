package com.spamalot.chess.fen.test;

import com.spamalot.chess.fen.FENUtil;
import com.spamalot.chess.movegen.ChessBoard;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test the FENUtil class.
 * 
 * @author gej
 *
 */
public final class FENUtilTest {
  /** Good FEN for testing. */
  private static final String GOODFEN = "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2";
  private static final String tooFewPartsFEN = "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R KQkq - 1 2";

  /** For testing exceptions. */
  @Rule
  public final ExpectedException exception = ExpectedException.none();

  /**
   * Test what happens when a FEN String is missing.
   */
  @Test
  public void emptyFENString() {
    this.exception.expect(IllegalArgumentException.class);
    this.exception.expectMessage("Empty FEN String.");

    FENUtil.processFENString(null, null);
  }

  /**
   * Test what happens when a Board Object is null.
   */
  @Test
  public void nullBoardElement() {
    this.exception.expect(IllegalArgumentException.class);
    this.exception.expectMessage("Null Board object.");

    FENUtil.processFENString(null, GOODFEN);
  }

  /**
   * Test what happens with badly formed FEN String.
   */
  @Test
  public void tooFewFENPartsBoardElement() {
    this.exception.expect(IllegalArgumentException.class);
    this.exception.expectMessage("FEN String does not have enough parts.  Needed : 6 Actual : 5");

    FENUtil.processFENString(new ChessBoard(), tooFewPartsFEN);
  }
}
