package sprint2.product;

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
  public enum GameMode {
    SIMPLE, GENERAL
  }

  private Cell[][] grid;
  private int boardSize = 3;
  private Turn turn;
  private GameState currentGameState;
  private GameMode gameMode;

  // // Constructs SosGame object with default board size (3).
  public SosGame() {
    grid = new Cell[3][3];
    initGame();
  }

  // Constructs SosGame object with a specified board size.
  public SosGame(int size) {
    boardSize = size;
    grid = new Cell[boardSize][boardSize];
    initGame();
  }

  public GameState getGameState() {
    return currentGameState;
  }

  public Turn getTurn() {
    return turn;
  }

  public int getBoardSize() {
    return boardSize;
  }
  
  public void setBoardSize(int size) {
    if (size >=3 && size <= 10) {
      boardSize = size;
    }

  }
  
  public void setGameMode(GameMode mode) {
      gameMode = mode;
  }
  
  public GameMode getGameMode() {
    return gameMode;
  }
  
  // Returns the content of a specified game cell.
  public Cell getCell(int row, int column) {
    if (row >= 0 && row < boardSize && column >= 0 && column < boardSize) {
        return grid[row][column];
    } else {
        return null;
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
  }

  /**
   * Starts a game of a given mode and board size.
   * @param mode Game mode of new game.
   * @param size Size of board to be created.
   * @return True if game was successfully started, otherwise false
   */
  public boolean newGame(String mode, String size) {

    int newSize;
    GameMode newMode;
    // Check if Board Size input is a valid integer
    try {
      newSize = Integer.valueOf(size);
    }
    catch (Exception NumberFormatException) {
      newSize = 0;
    }
    
    switch (mode) {
    case "Simple":
      newMode = GameMode.SIMPLE;
      break;

    case "General":
      newMode = GameMode.GENERAL;
      break;
    default:
      newMode = null;
    }
    
    if (newSize >=3 && newSize <= 10 && newMode != null) {
      setBoardSize(newSize);
      setGameMode(newMode);
      grid = new Cell[boardSize][boardSize];
      initGame();
      return true;
    }
    
    else {
      return false;
    }

  }

  /**
   * Places a letter on an empty cell and changes player turn.
   * @param selectedLetter Letter to place in empty cell.
   */
  public void makeMove(int row, int column, Cell selectedLetter) {
    if (row >= 0 && row < boardSize && column >= 0 && column < boardSize && grid[row][column] == Cell.EMPTY) {
      grid[row][column] = selectedLetter;

      turn = (turn == Turn.BLUE) ? Turn.RED : Turn.BLUE;
    }
  }
}