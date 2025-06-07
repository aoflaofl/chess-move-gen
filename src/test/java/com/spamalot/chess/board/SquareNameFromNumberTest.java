package com.spamalot.chess.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.spamalot.chess.lib0x88.SquareName0x88;

/**
 * Tests for converting a 0x88 square number to a square name.
 */
@SuppressWarnings("static-method")
public class SquareNameFromNumberTest {

  /** Test that a valid square number returns the correct name. */
  @Test
  public void testValidSquare() {
    assertEquals("b2", SquareName0x88.fromBoard0x88number(17));
  }

  /** Test that an invalid square number returns null. */
  @Test
  public void testInvalidSquare() {
    assertNull(SquareName0x88.fromBoard0x88number(0x80));
  }
}
