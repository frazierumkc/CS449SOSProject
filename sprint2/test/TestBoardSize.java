package sprint2.test;

import sprint2.product.SosGame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBoardSize {
  
  SosGame game;
  
  @BeforeEach
  public void setUp() throws Exception {
    // 5 will be used as the default board size for all tests
    game = new SosGame(5);
  }
  
  @AfterEach
  public void tearDown() throws Exception {
  }
  
  @Test // Written by Chat GPT
  public void testValidMinBoardSize() {
      // Set the board size to the minimum valid value
      game.setBoardSize(3);
      
      // Assert that the board size has been updated to 3
      assertEquals(3, game.getBoardSize(), "Board size should be set to 3");
  }
 
  @Test
  void testValidMaxBoardSize() {
    game.setBoardSize(10);
    assertEquals(10, game.getBoardSize());
  }
  
  @Test // Written by Chat GPT
  void testBelowMinBoardSize() {
    // Try to set the board size to an invalid value (below the minimum of 3)
    game.setBoardSize(2);

    // Assert that the board size remains the default value (5)
    assertEquals(5, game.getBoardSize(), "Board size should remain 5 when an invalid size is set.");
}
  
  @Test
  void testAboveMaxBoardSize() {
    game.setBoardSize(11);
    assertEquals(5, game.getBoardSize());
  }
  
}