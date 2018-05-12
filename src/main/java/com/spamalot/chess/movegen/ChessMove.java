package com.spamalot.chess.movegen;

/**
 * Hold a chess move.
 * 
 * @author gej
 *
 */
class ChessMove {
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
  ChessMove(final int s, final int sd) {
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

}
