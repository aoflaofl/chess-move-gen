package com.spamalot.chess.fen.test;

import static org.junit.Assert.assertFalse;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.spamalot.chess.game.ChessGameState;
import com.spamalot.chess.piece.Color;
import com.spamalot.chess.piece.PieceType;
import com.spamalot.chess.util.FENUtil;

/**
 * Test the FENUtil class.
 *
 * @author gej
 *
 */
public class FENUtilTest {
  /** Good FEN for testing. */
  private static final String GOOD_FEN = "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2";

  private static final String GOOD_BOARD = "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R";
  private static final String BAD_BOARD = "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP";

  private static final String GOOD_COLOR_TO_MOVE = "b";

  private static final String GOOD_CASTLE = "KQkq";

  private static final String GOOD_EN_PASSANT = "-";

  private static final String GOOD_MOVES_SINCE_LAST_PAWN_OR_CAPTURE = "1";
  private static final String GOOD_MOVE_NUMBER = "2";

  /** Bad FEN. */
  private static final String TOO_FEW_PARTS_FEN = "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R KQkq - 1 2";

  /** For testing exceptions. */
  @Rule
  public ExpectedException exception = ExpectedException.none();

  /**
   * Test what happens when a FEN String is missing.
   */
  @Test
  public void testEmptyFENString() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Empty FEN String.");

    FENUtil.processFENString(null, null);
  }

  /**
   * Test what happens when a Board Object is null.
   */
  @Test
  public void testNullBoardElement() {
    exception.expect(NullPointerException.class);
    exception.expectMessage("Null Board object.");

    FENUtil.processFENString(null, String.join(" ", GOOD_BOARD, GOOD_COLOR_TO_MOVE, GOOD_CASTLE, GOOD_EN_PASSANT,
        GOOD_MOVES_SINCE_LAST_PAWN_OR_CAPTURE, GOOD_MOVE_NUMBER));
  }

  /**
   * Test what happens with badly formed FEN String.
   */
  @Test
  public void testTooFewFENPartsBoardElement() {
    exception.expect(AssertionError.class);
    exception.expectMessage("FEN String does not have correct number of parts.  Needed : 6 Actual : 5");

    FENUtil.processFENString(new ChessGameState(), TOO_FEW_PARTS_FEN);
  }

  @Test
  public void testGoodFEN() {
    FENUtil.processFENString(new ChessGameState(), String.join(" ", GOOD_BOARD, GOOD_COLOR_TO_MOVE, GOOD_CASTLE,
        GOOD_EN_PASSANT, GOOD_MOVES_SINCE_LAST_PAWN_OR_CAPTURE, GOOD_MOVE_NUMBER));
  }

  @Test
  public void testBadBoardFEN() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("FEN Board String does not have enough ranks : 7");

    FENUtil.processFENString(new ChessGameState(), String.join(" ", BAD_BOARD, GOOD_COLOR_TO_MOVE, GOOD_CASTLE,
        GOOD_EN_PASSANT, GOOD_MOVES_SINCE_LAST_PAWN_OR_CAPTURE, GOOD_MOVE_NUMBER));
  }

  /**
   * Test what happens when the en-passant square is invalid.
   */
  @Test
  public void testInvalidEnPassantSquare() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Expected condition to be true");

    String invalidEnPassantFEN = String.join(" ", GOOD_BOARD, GOOD_COLOR_TO_MOVE, GOOD_CASTLE, "e3",
        GOOD_MOVES_SINCE_LAST_PAWN_OR_CAPTURE, GOOD_MOVE_NUMBER);

    FENUtil.processFENString(new ChessGameState(), invalidEnPassantFEN);
  }

  /**
   * Test what happens when the castling string is invalid.
   */
  @Test
  public void testInvalidCastlingString() {
    String invalidCastlingFEN = String.join(" ", GOOD_BOARD, GOOD_COLOR_TO_MOVE, "X", GOOD_EN_PASSANT,
        GOOD_MOVES_SINCE_LAST_PAWN_OR_CAPTURE, GOOD_MOVE_NUMBER);

    ChessGameState board = new ChessGameState();
    FENUtil.processFENString(board, invalidCastlingFEN);

    // Assert that no castling rights are set
    assertFalse(board.canCastle(PieceType.KING, Color.WHITE));
    assertFalse(board.canCastle(PieceType.QUEEN, Color.WHITE));
    assertFalse(board.canCastle(PieceType.KING, Color.BLACK));
    assertFalse(board.canCastle(PieceType.QUEEN, Color.BLACK));
  }

  /**
   * Test what happens when the board setup string has invalid ranks.
   */
  @Test
  public void testInvalidBoardSetup() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("FEN Board String does not have enough ranks");

    String invalidBoardSetupFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP";

    FENUtil.processFENString(new ChessGameState(), String.join(" ", invalidBoardSetupFEN, GOOD_COLOR_TO_MOVE,
        GOOD_CASTLE, GOOD_EN_PASSANT, GOOD_MOVES_SINCE_LAST_PAWN_OR_CAPTURE, GOOD_MOVE_NUMBER));
  }

  /**
   * Test what happens when the move number is invalid.
   */
  @Test
  public void testInvalidMoveNumber() {
    exception.expect(NumberFormatException.class);

    String invalidMoveNumberFEN = String.join(" ", GOOD_BOARD, GOOD_COLOR_TO_MOVE, GOOD_CASTLE, GOOD_EN_PASSANT,
        GOOD_MOVES_SINCE_LAST_PAWN_OR_CAPTURE, "invalid");

    FENUtil.processFENString(new ChessGameState(), invalidMoveNumberFEN);
  }

  /**
   * Test what happens when the half-move clock is invalid.
   */
  @Test
  public void testInvalidHalfMoveClock() {
    exception.expect(NumberFormatException.class);

    String invalidHalfMoveClockFEN = String.join(" ", GOOD_BOARD, GOOD_COLOR_TO_MOVE, GOOD_CASTLE, GOOD_EN_PASSANT,
        "invalid", GOOD_MOVE_NUMBER);

    FENUtil.processFENString(new ChessGameState(), invalidHalfMoveClockFEN);
  }
}
