package sprint4.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sprint4.product.SosGame;
import sprint4.product.SosGame.Cell;
import sprint4.product.SosGame.Turn;
import sprint4.product.SosSimpleGame;

public class TestMakeMoveSimple {
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
  void testBlueSMove() {
    game.makeMove(0, 0, Cell.BLUE_S);
    assertEquals(Cell.BLUE_S, game.getCell(0, 0));
    assertEquals(Turn.RED, game.getTurn());
  }
  
  @Test
  void testBlueOMove() {
    game.makeMove(2, 2, Cell.BLUE_O);
    assertEquals(Cell.BLUE_O, game.getCell(2, 2));
    assertEquals(Turn.RED, game.getTurn());
  }
  
  @Test
  void testRedSMove() {
    game.makeMove(2, 2, Cell.BLUE_O);
    game.makeMove(0, 0, Cell.RED_S);
    assertEquals(Cell.RED_S, game.getCell(0, 0));
    assertEquals(Turn.BLUE, game.getTurn());
  }
  
  @Test
  void testRedOMove() {
    game.makeMove(0, 0, Cell.BLUE_S);
    game.makeMove(2, 2, Cell.RED_O);
    assertEquals(Cell.RED_O, game.getCell(2, 2));
    assertEquals(Turn.BLUE, game.getTurn());
  }
  
  @Test
  void testInvalidMoveOnNonemptyCell() {
    game.makeMove(0, 0, Cell.BLUE_S);
    game.makeMove(0, 0, Cell.RED_O);
    assertEquals(Cell.BLUE_S, game.getCell(0, 0));
    assertEquals(Turn.RED, game.getTurn());
  }
  
  @Test
  void testInvalidMoveOutOfBounds() {
    game.makeMove(3, 3, Cell.BLUE_S);
    assertEquals(Turn.BLUE, game.getTurn());
  }
}