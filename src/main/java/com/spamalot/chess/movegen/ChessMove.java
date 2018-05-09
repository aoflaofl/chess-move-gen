package com.spamalot.chess.movegen;

/**
 * Hold a chess move.
 * 
 * @author gej
 *
 */
class ChessMove {
  private int source;
  private int destination;

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
    builder.append("ChessMove [source=").append(this.source).append(", destination=").append(this.destination).append(']');
    return builder.toString();
  }

}
