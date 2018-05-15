package com.spamalot.chess.test;

import static org.junit.Assert.assertTrue;

import com.spamalot.chess.fen.FENUtil;

import org.junit.Test;

/**
 * Just a demo/stub JUnit class.
 * 
 * @author gej
 *
 */
public final class AppTest {

  /**
   * Stringent testing.
   */
  @Test
  public void emptyTest() {
    FENUtil.processFENString(null, null);

    assertTrue(true);
  }
}
