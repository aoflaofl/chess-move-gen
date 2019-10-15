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
   * @param s
   *          source Square
   * @param sd
   *          Destination Square
   */
  public ChessMove(final int s, final int sd) {
    this.source = s;
    this.destination = sd;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ChessMove [source=").append(this.source).append(", destination=").append(this.destination)
        .append(']');
    return builder.toString();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + this.destination;
    return prime * result + this.source;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ChessMove other = (ChessMove) obj;
    if (this.destination != other.destination) {
      return false;
    }
    if (this.source != other.source) {
      return false;
    }
    return true;
  }

}
