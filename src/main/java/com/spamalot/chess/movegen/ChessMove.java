package com.spamalot.chess.movegen;

import com.spamalot.chess.lib0x88.SquareName0x88;

/**
 * Hold a chess move.
 *
 * @author gej
 *
 */
public class ChessMove {
  /** Source Square. */
  private int source;
  /** Destination Square. */
  private int destination;

  /**
   * Construct a Chess Move.
   *
   * @param s  source Square
   * @param sd Destination Square
   */
  public ChessMove(int s, int sd) {
    source = s;
    destination = sd;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */

  @Override
  public String toString() {
    return "ChessMove [source=" + source + ", destination=" + destination + "]";
  }

  public String toMoveString() {
    return String.format("%s-%s", SquareName0x88.fromBoard0x88number(source),
        SquareName0x88.fromBoard0x88number(destination));
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    return prime * (prime * destination + source);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof ChessMove)) {
      return false;
    }
    ChessMove other = (ChessMove) obj;
    return destination == other.destination && source == other.source;
  }
}
