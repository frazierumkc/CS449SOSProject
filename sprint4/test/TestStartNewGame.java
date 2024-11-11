package sprint4.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sprint4.product.SosGame;
import sprint4.product.SosGeneralGame;
import sprint4.product.SosSimpleGame;

public class TestStartNewGame {
  SosGame game;
  
  @BeforeEach
  public void setUp() throws Exception {
    // 3x3 board
    game = new SosGame();
  }
  
  @AfterEach
  public void tearDown() throws Exception {
  }
  
  @Test
  void testValidSimpleNewGame() {
    game = new SosSimpleGame(5);
    game.newGame("3");
    assertEquals(3, game.getBoardSize());
  }
  
  @Test
  void testValidGeneralNewGame() {
    game = new SosGeneralGame(5);
    game.newGame("10");
    assertEquals(10, game.getBoardSize());
  }
  
  @Test
  void testInvalidBoardSize() {
    game = new SosSimpleGame(5);
    game.newGame("11");
    // If board size is 5, new game was correctly not created
    assertEquals(5, game.getBoardSize());
  }
}