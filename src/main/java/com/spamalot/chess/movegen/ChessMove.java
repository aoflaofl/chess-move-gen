package com.spamalot.chess.movegen;

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
    StringBuilder builder = new StringBuilder();
    builder.append("ChessMove [source=").append(source).append(", destination=").append(destination).append(']');
    return builder.toString();
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    int prime = 31;
    int result = 1;
    result = prime * result + destination;
    return prime * result + source;
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
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    ChessMove other = (ChessMove) obj;
    if (destination != other.destination) {
      return false;
    }
    return source == other.source;
  }

}
