package com.spamalot.chess.piece;

/**
 * Enum for type of Chess Piece.
 */
public enum PieceType {

  /** Pawn type. */
  PAWN('p', 'P', MoveStyle.PAWN, ChessPiece.PAWN_MOVE),
  /** Knight type. */
  KNIGHT('n', 'N', MoveStyle.JUMPER, ChessPiece.KNIGHT_MOVE),
  /** Bishop type. */
  BISHOP('b', 'B', MoveStyle.SLIDER, ChessPiece.DIAGONAL_MOVE),
  /** Rook type. */
  ROOK('r', 'R', MoveStyle.SLIDER, ChessPiece.ORTHOGONAL_MOVE),
  /** Queen type. */
  QUEEN('q', 'Q', MoveStyle.SLIDER, ChessPiece.DIAGONAL_MOVE | ChessPiece.ORTHOGONAL_MOVE),
  /** King type. */
  KING('k', 'K', MoveStyle.JUMPER, ChessPiece.KING_MOVE);

  /** White character for this piece. */
  private final char whiteChar;
  /** Black character for this piece. */
  private final char blackChar;
  /** String array with String representation of the PieceType */
  final String[] stringRepresentation;
  private MoveStyle moveStyle;
  private int moveType;

  /**
   * Construct the piece type.
   *
   * @param blackCh   Black character representing this piece
   * @param whiteCh   White character representing this piece
   * @param moveStyle
   */
  PieceType(char blackCh, char whiteCh, MoveStyle moveStyle, int moveType) {
    this.whiteChar = whiteCh;
    this.blackChar = blackCh;
    this.stringRepresentation = new String[Color.values().length];
    this.stringRepresentation[Color.WHITE.ordinal()] = String.valueOf(whiteCh);
    this.stringRepresentation[Color.BLACK.ordinal()] = String.valueOf(blackCh);
    this.moveStyle = moveStyle;
    this.moveType = moveType;
  }

  /**
   * Get the white character that represents the piece on an ASCII board.
   *
   * @return the whiteChar
   */
  public char getWhiteChar() {
    return whiteChar;
  }

  /**
   * Get the black character that represents the piece on an ASCII board.
   *
   * @return the blackChar
   */
  public char getBlackChar() {
    return blackChar;
  }

  public static PieceType fromChar(char c) {
    for (PieceType pt : PieceType.values()) {
      if (pt.getBlackChar() == c || pt.getWhiteChar() == c) {
        return pt;
      }
    }
    return null;
  }

  String getCharForColor(Color color) {
    return stringRepresentation[color.ordinal()];
  }

  public MoveStyle getMoveStyle() {
    return moveStyle;
  }

  public boolean isOrthogonal() {
    return (moveType & ChessPiece.ORTHOGONAL_MOVE) != 0;

  }

  public boolean isDiagonal() {
    return (moveType & ChessPiece.DIAGONAL_MOVE) != 0;
  }
}
