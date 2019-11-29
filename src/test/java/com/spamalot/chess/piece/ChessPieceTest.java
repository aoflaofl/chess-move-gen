package com.spamalot.chess.piece;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ChessPieceTest {
  private ChessPiece blackPiece = new ChessPiece(Color.BLACK, PieceType.BISHOP);
  private ChessPiece whitePiece = new ChessPiece(Color.WHITE, PieceType.PAWN);

  @Test
  public void testChessPiece() {
    assertNotNull(this.blackPiece);
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.BLACK, this.blackPiece.getColor());
  }

  @Test
  public void testGetType() {
    assertNotNull(this.blackPiece.getType());
  }

  @Test
  public void testToString() {
    assertNotNull(this.blackPiece.toString());
    assertNotNull(this.whitePiece.toString());
  }

}
