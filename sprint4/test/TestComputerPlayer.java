package sprint4.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sprint4.product.SosGame;
import sprint4.product.SosGame.*;
import sprint4.product.SosSimpleGame;

public class TestComputerPlayer {
  SosGame game;
  
  @BeforeEach
  public void setUp() throws Exception {
    // 3x3 game
    game = new SosSimpleGame();
  }
  
  @AfterEach
  public void tearDown() throws Exception {
  }
  
  @Test
  void testComputerRandomMove() {
    // If the turn switches after attempting to make the computer player make a move on a nonempty
    // cell that a human previously chose, then the computer player successfully made a random move.
    game.makeMove(0, 0, Cell.BLUE_S);
    assertEquals(Cell.BLUE_S, game.getCell(0, 0));
    assertEquals(Turn.RED, game.getTurn());
    game.makeMove(0, 0, Cell.EMPTY);
    assertEquals(Turn.BLUE, game.getTurn());
  }
  
  @Test
  void testComputerSosFormingMove() {
    // If the blue player has won after making a computer move when an SOS is available to form,
    // then the computer player successfully recognized the move that will form the SOS.
    game.makeMove(0, 0, Cell.BLUE_S);
    assertEquals(Cell.BLUE_S, game.getCell(0, 0));
    assertEquals(Turn.RED, game.getTurn());
    game.makeMove(0, 1, Cell.RED_O);
    assertEquals(Cell.RED_O, game.getCell(0, 1));
    assertEquals(Turn.BLUE, game.getTurn());
    game.makeMove(0, 0, Cell.EMPTY);
    assertEquals(GameState.BLUE_WON, game.getGameState());
  }
  
}