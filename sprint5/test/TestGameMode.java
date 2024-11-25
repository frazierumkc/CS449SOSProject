package sprint5.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sprint5.product.SosGame;
import sprint5.product.SosGeneralGame;
import sprint5.product.SosSimpleGame;

public class TestGameMode {
  
  SosGame game;
  
  @BeforeEach
  public void setUp() throws Exception {
    // 3x3 game
    game = null;
  }
  
  @AfterEach
  public void tearDown() throws Exception {
  }
  
  @Test
  void testSimpleMode() {
    game = new SosSimpleGame();
    assertNotNull(game);
  }
  
  @Test
  void testGeneralMode() {
    game = new SosGeneralGame();
    assertNotNull(game);
  }
  
}