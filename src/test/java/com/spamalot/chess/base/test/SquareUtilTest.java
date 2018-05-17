package com.spamalot.chess.base.test;

import static org.junit.Assert.fail;

import com.spamalot.chess.base.SquareUtil;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SquareUtilTest {

  /** For testing exceptions. */
  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testGetFile() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetRank() {
    fail("Not yet implemented");
  }

  @Test
  public void testValueOfNotNull() {
    SquareUtil a = SquareUtil.valueOf(1, 2);
    org.junit.Assert.assertNotNull(a);
  }

  @Test
  public void testValueOfRange() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("File must be between 0 and 7 inclusive.");

    SquareUtil.valueOf(-1, 2);
  }

  @Test
  public void testEqualsObject() {
    fail("Not yet implemented");
  }

}
