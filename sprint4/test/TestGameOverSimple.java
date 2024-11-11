package sprint4.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sprint4.product.SosGame.GameState;
import sprint4.product.SosGame;
import sprint4.product.SosGame.Cell;
import sprint4.product.SosSimpleGame;

public class TestGameOverSimple {
  SosGame game;
  
  @BeforeEach
  public void setUp() throws Exception {
    // 3x3 simple game
    game = new SosSimpleGame();
  }
  
  @AfterEach
  public void tearDown() throws Exception {
  }
   
  @Test
  void testBlueWin() {
    game.makeMove(0, 0, Cell.BLUE_S);
    game.makeMove(0, 1, Cell.RED_O);
    game.makeMove(0, 2, Cell.BLUE_S);
    assertEquals(GameState.BLUE_WON, game.getGameState());
  }
  
  @Test
  void testRedWin() {
    game.makeMove(0, 0, Cell.BLUE_S);
    game.makeMove(1, 1, Cell.RED_S);
    game.makeMove(0, 2, Cell.BLUE_S);
    game.makeMove(0, 1, Cell.RED_O);
    assertEquals(GameState.RED_WON, game.getGameState());
  }
  
  @Test
  void testDraw() {
    // fill board with S
    for (int i = 0; i < game.getBoardSize(); i++) {
      for (int j = 0; j < game.getBoardSize(); j++) {
        game.makeMove(i, j, Cell.BLUE_S);
      }
    }
    assertEquals( GameState.DRAW, game.getGameState());
  }
  
  @Test
  void testGameNotOver() {
    game.makeMove(0, 0, Cell.BLUE_S);
    game.makeMove(0, 1, Cell.RED_O);
    game.makeMove(1, 1, Cell.BLUE_S);
    assertEquals(GameState.PLAYING, game.getGameState());
  }
  
}