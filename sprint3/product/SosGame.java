package sprint3.product;

import java.util.ArrayList;

public class SosGame {

  public enum Cell {
    EMPTY, BLUE_S, BLUE_O, RED_S, RED_O
  }
  public enum Turn {
    BLUE, RED
  }
  public enum GameState {
    PLAYING, DRAW, BLUE_WON, RED_WON
  }

  protected Cell[][] grid;
  protected int boardSize = 3;
  protected int cellNumber, fullCells;
  protected Turn turn;
  protected GameState currentGameState;
  protected ArrayList<int[]> BlueSosList;
  protected ArrayList<int[]> RedSosList;

  // // Constructs SosGame object with default board size (3).
  public SosGame() {
    grid = new Cell[3][3];
    cellNumber = 9;
    initGame();
  }

  // Constructs SosGame object with a specified board size.
  public SosGame(int size) {
    boardSize = size;
    grid = new Cell[boardSize][boardSize];
    cellNumber = size * size;
    fullCells = 0;
    initGame();
  }

  public GameState getGameState() {
    return currentGameState;
  }

  protected void setGameState(GameState state) {
    currentGameState = state;
  }

  public Turn getTurn() {
    return turn;
  }

  // Change turn to other player.
  protected void switchTurn() {
    turn = (turn == Turn.BLUE) ? Turn.RED : Turn.BLUE;
  }

  public int getBoardSize() {
    return boardSize;
  }

  public void setBoardSize(int size) {
    if (size >=3 && size <= 10) {
      boardSize = size;
    }

  }

  // Returns the content of a specified game cell.
  public Cell getCell(int row, int column) {
    if (row >= 0 && row < boardSize && column >= 0 && column < boardSize) {
      return grid[row][column];
    } else {
      return null;
    }
  }

  public ArrayList<int[]> getBlueSosList(){
    return BlueSosList;
  }
  
  public ArrayList<int[]> getRedSosList(){
    return RedSosList;
  }
  
  /**
   * Adds an SOS to the current turn's SOS list.
   * @param sos SOS in the format {x1, y1, x2, y2}.
   */
  protected void addSosList(int[] sos) {
    switch (getTurn()) {
    case BLUE:
      BlueSosList.add(sos);
      break;
    case RED:
      RedSosList.add(sos);
      break;
    }
  }

  // Empties all cells and initializes game.
  private void initGame() {
    for (int row = 0; row < boardSize; ++row) {
      for (int col = 0; col < boardSize; ++col) {
        grid[row][col] = Cell.EMPTY;
      }
    }
    currentGameState = GameState.PLAYING;
    turn = Turn.BLUE;
    BlueSosList = new ArrayList<int[]>();
    RedSosList = new ArrayList<int[]>();

  }

  /**
   * Starts a game of a given mode and board size.
   * @param mode Game mode of new game.
   * @param size Size of board to be created.
   * @return True if game was successfully started, otherwise false
   */
  public boolean newGame(String size) {

    int newSize;
    // Check if Board Size input is a valid integer
    try {
      newSize = Integer.valueOf(size);
    }
    catch (Exception e) {
      newSize = 0;
    }


    if (newSize >=3 && newSize <= 10) {
      setBoardSize(newSize);
      grid = new Cell[boardSize][boardSize];
      cellNumber = boardSize * boardSize;
      fullCells = 0;
      initGame();
      return true;
    }

    else {
      return false;
    }
  }

  /**
   * Places a letter on an empty cell and updates game.
   * @param selectedLetter Letter to place in empty cell.
   */
  public void makeMove(int row, int column, Cell selectedLetter) {
    if (row >= 0 && row < boardSize && column >= 0 && column < boardSize && grid[row][column] == Cell.EMPTY) {

      grid[row][column] = selectedLetter;
      fullCells++;

      updateGame(row, column, selectedLetter);
    }
  }

  // Method to be overridden. Used by makeMove to update game state.
  protected void updateGame(int row, int column, Cell selectedLetter) {
  }

  /**
   * Check for SOSs formed by a placed O.
   * @param row Row of the placed O to check
   * @param column Column of the placed O to check
   * @return True if at least 1 SOS is formed, false otherwise
   */
  protected boolean checkFormedSosO(int row, int column) {

    boolean sosFormed = false;

    // Horizontal and diagonal SOSs
    for (int i = -1; i <= 1; i++) {
      try {
        if ((grid[row + i][column - 1] == Cell.BLUE_S || grid[row + i][column - 1] == Cell.RED_S)
            && (grid[row - i][column + 1] == Cell.BLUE_S || grid[row - i][column + 1] == Cell.RED_S)) {

          // Append SOS to list of turn's SOSs
          int[] sos = {column - 1, row + i, column + 1, row - i};
          addSosList(sos);
          sosFormed = true;
        }
      }
      catch (ArrayIndexOutOfBoundsException e) {

      }
    }

    // Vertical SOS
    try {
      if ((grid[row - 1][column] == Cell.BLUE_S || grid[row + 1][column] == Cell.RED_S)
          && (grid[row + 1][column] == Cell.BLUE_S || grid[row + 1][column] == Cell.RED_S)) {

        // Append SOS to list of turn's SOSs
        int[] sos = {column, row - 1, column, row + 1};
        addSosList(sos);
        sosFormed = true;
      }
    }
    catch (ArrayIndexOutOfBoundsException e) {

    }

    return sosFormed;

  }

  /**
   * Check for SOSs formed by a placed S.
   * @param row Row of the placed S to check
   * @param column Column of the placed S to check
   * @return True if at least 1 SOS is formed, false otherwise
   */
  protected boolean checkFormedSosS(int row, int column) {

    boolean sosFormed = false;

    // Check 5x5 grid centered around specified S

    for (int i = -1; i <=1; i++) {
      for (int j = -1; j <=1; j++) {
        try {
          int checkedRow = row + i;
          int checkedColumn = column + j;
          if ((grid[checkedRow][checkedColumn] == Cell.BLUE_O || grid[checkedRow][checkedColumn] == Cell.RED_O)
              && (grid[checkedRow + i][checkedColumn + j] == Cell.BLUE_S || grid[checkedRow + i][checkedColumn + j] == Cell.RED_S)) {

            // Append SOS to list of turn's SOSs
            int[] sos = {column, row, checkedColumn + j, checkedRow + i};
            addSosList(sos);
            sosFormed = true;
          }
        }
        catch (ArrayIndexOutOfBoundsException e) {
        }

      }
    }

    return sosFormed;
  }

}