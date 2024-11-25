package sprint5.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sprint5.product.SosGame;
import sprint5.product.SosGeneralGame;
import sprint5.product.SosSimpleGame;

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
  void testValidSimpleNewGame() throws IOException {
    game = new SosSimpleGame(5);
    game.newGame("3", false, "Simple");
    assertEquals(3, game.getBoardSize());
  }
  
  @Test
  void testValidGeneralNewGame() throws IOException {
    game = new SosGeneralGame(5);
    game.newGame("10", false, "General");
    assertEquals(10, game.getBoardSize());
  }
  
  @Test
  void testInvalidBoardSize() throws IOException {
    game = new SosSimpleGame(5);
    game.newGame("11", false, "Simple");
    // If board size is 5, new game was correctly not created
    assertEquals(5, game.getBoardSize());
  }
}