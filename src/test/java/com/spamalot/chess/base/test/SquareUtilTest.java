package com.spamalot.chess.base.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.spamalot.chess.base.SquareUtil;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SquareUtilTest {
  private SquareUtil squ = SquareUtil.valueOf(1, 4);

  /** For testing exceptions. */
  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Test
  public void testGetFile() {
    assertEquals(this.squ.getFile(), 1);
  }

  @Test
  public void testGetRank() {
    assertEquals(this.squ.getRank(), 4);
  }

  @Test
  public void testValueOfNotNull() {
    SquareUtil a = SquareUtil.valueOf(1, 2);
    org.junit.Assert.assertNotNull(a);
  }

  @Test
  public void testValueOfRange() {
    this.exception.expect(IllegalArgumentException.class);
    this.exception.expectMessage("File is -1.  Must be between 0 and 7 inclusive.");

    SquareUtil.valueOf(-1, 2);
  }

  @Test
  public void testEqualsObject() {
    assertTrue(this.squ == SquareUtil.valueOf(1, 4));

    assertFalse(this.squ == SquareUtil.valueOf(4, 1));
  }

}
