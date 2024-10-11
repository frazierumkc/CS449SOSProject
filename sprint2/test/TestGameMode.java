package sprint2.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sprint2.product.SosGame;
import sprint2.product.SosGame.GameMode;

public class TestGameMode {
  
  SosGame game;
  
  @BeforeEach
  public void setUp() throws Exception {
    // 3x3 game
    game = new SosGame();
  }
  
  @AfterEach
  public void tearDown() throws Exception {
  }
  
  @Test
  void testSimpleMode() {
    game.setGameMode(GameMode.SIMPLE);
    assertEquals(GameMode.SIMPLE, game.getGameMode());
  }
  
  @Test
  void testGeneralMode() {
    game.setGameMode(GameMode.GENERAL);
    assertEquals(GameMode.GENERAL, game.getGameMode());
  }
  
  @Test
  void testInvalidGameMode() {
    game.newGame("hello", "10");
    // If board size is 5, new game was not started
    assertEquals(3, game.getBoardSize());
  }
  
}