package com.spamalot.chess.piece;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ChessPieceTest {
  private ChessPiece blackPiece = new ChessPiece(Color.BLACK, PieceType.BISHOP);
  private ChessPiece whitePiece = new ChessPiece(Color.WHITE, PieceType.PAWN);

  @Test
  public void testChessPiece() {
    assertNotNull(blackPiece);
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.BLACK, blackPiece.getColor());
  }

  @Test
  public void testGetType() {
    assertNotNull(blackPiece.getType());
  }

  @Test
  public void testToString() {
    assertNotNull(blackPiece.toString());
    assertNotNull(whitePiece.toString());
  }

}
