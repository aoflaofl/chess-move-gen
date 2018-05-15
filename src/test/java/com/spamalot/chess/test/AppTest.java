package com.spamalot.chess.test;

import com.spamalot.chess.fen.FENUtil;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * 
 * @author gej
 *
 */
public final class AppTest {
  private static String GOODFEN = "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2";
  /** For testing exceptions. */
  @Rule
  public final ExpectedException exception = ExpectedException.none();

  /**
   * Test what happens when a FEN String is missing.
   */
  @Test
  public void emptyFENString() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Empty FEN String.");

    FENUtil.processFENString(null, null);
  }
  
  /**
   * Test what happens when a FEN String is missing.
   */
  @Test
  public void nullBoardElement() {
//    exception.expect(IllegalArgumentException.class);
//    exception.expectMessage("Empty FEN String.");

    FENUtil.processFENString(null, GOODFEN);
  }
}
