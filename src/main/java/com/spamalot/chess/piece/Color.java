package com.spamalot.chess.piece;

/**
 * The colors in a standard game of Chess.
 *
 * @author gej
 *
 */
public enum Color {
  /** Black color. */
  BLACK,
  /** White color. */
  WHITE;

  /**
   * Convert w or b to the Color.
   *
   * @param colorString "w" or "b"
   * @return the Color.
   */

  public static Color toColor(String colorString) {
    return "w".equals(colorString) ? WHITE : BLACK;
  }

  public Color opposite() {
    return this == WHITE ? BLACK : WHITE;
  }
}
