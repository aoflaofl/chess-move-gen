package com.spamalot.chess;

/**
 * Custom exception to handle application exit scenarios.
 */
public class ApplicationExitException extends Exception {
  private static final long serialVersionUID = 1L;
  private final int statusCode;

  public ApplicationExitException(String message, int statusCode) {
    super(message);
    this.statusCode = statusCode;
  }

  public int getStatusCode() {
    return statusCode;
  }
}