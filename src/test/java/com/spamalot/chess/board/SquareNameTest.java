package com.spamalot.chess.board;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SquareNameTest {

  @Test
  public void testToName() {
    assertEquals("b2", SquareName.toName(1, 1));
  }
}
