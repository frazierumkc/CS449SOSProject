package sprint2.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sprint2.product.SosGame;
import sprint2.product.SosGame.GameMode;

public class TestStartNewGame {
  SosGame game;
  
  @BeforeEach
  public void setUp() throws Exception {
    // 5 will be used as the default board size for all tests
    game = new SosGame(5);
  }
  
  @AfterEach
  public void tearDown() throws Exception {
  }
  
  @Test
  void testValidSimpleNewGame() {
    game.newGame("Simple", "3");
    assertEquals(3, game.getBoardSize());
    assertEquals(GameMode.SIMPLE, game.getGameMode());
  }
  
  @Test
  void testValidGeneralNewGame() {
    game.newGame("General", "10");
    assertEquals(10, game.getBoardSize());
    assertEquals(GameMode.GENERAL, game.getGameMode());
  }
  
  @Test
  void testInvalidGameMode() {
    game.newGame("hello", "10");
    // If board size is 5, new game was not started
    assertEquals(5, game.getBoardSize());
  }
  
  @Test
  void testInvalidBoardSize() {
    game.newGame("Simple", "goodbye");
    // If board size is 5, new game was not started
    assertEquals(5, game.getBoardSize());
  }
}