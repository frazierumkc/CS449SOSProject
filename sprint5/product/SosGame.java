package sprint5.product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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
  protected Player bluePlayer = new Player();
  protected Player redPlayer = new Player();

  private boolean isGameRecorded;
  @SuppressWarnings("unused")
  private File recordedGame;
  private FileWriter fileWriter;
  private Scanner gameReader;

  // // Constructs SosGame object with default board size (3).
  public SosGame() {
    grid = new Cell[3][3];
    cellNumber = 9;
    fullCells = 0;
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

  private Player currentPlayer() {
    switch (turn) {
    case BLUE:
      return bluePlayer;
    case RED:
      return redPlayer;
    default:
      return bluePlayer;
    }
  }

  public ArrayList<int[]> getBlueSosList(){
    return bluePlayer.getSosList();
  }

  public ArrayList<int[]> getRedSosList(){
    return redPlayer.getSosList();
  }

  // Returns the content of a specified game cell.
  public Cell getCell(int row, int column) {
    if (row >= 0 && row < boardSize && column >= 0 && column < boardSize) {
      return grid[row][column];
    } else {
      return null;
    }
  }
  
  private Cell parseCell(String s) {
    switch (s) {
    case "EMPTY":
      return Cell.EMPTY;
    case "BLUE_S":
      return Cell.BLUE_S;
    case "BLUE_O":
      return Cell.BLUE_O;
    case "RED_S":
      return Cell.RED_S;
    case "RED_O":
      return Cell.RED_O;
    }
    return null;
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
    bluePlayer = new Player();
    redPlayer = new Player();

  }

  /**
   * Starts a game of a given mode and board size.
   * @param mode Game mode of new game.
   * @param size Size of board to be created.
   * @return True if game was successfully started, otherwise false
   * @throws IOException 
   */
  public boolean newGame(String size, boolean gameRecorded, String gameMode) throws IOException {

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
      isGameRecorded = gameRecorded;
      if (gameRecorded) {
        String fileName;
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt files", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
           fileName = chooser.getSelectedFile().getName();
           if (!fileName.endsWith(".txt")) {
             fileName = fileName + ".txt";
           }
        }
        else {
          fileName = "recordedGame.txt";
        }
        
        recordedGame = new File(fileName);
        fileWriter = new FileWriter(fileName);
        fileWriter.write(String.format(gameMode + "\n%d\n", boardSize));
      }
      initGame();
      return true;
    }

    else {
      return false;
    }
  }

  public boolean replayGame(String fileName) {

    try {
      gameReader = new Scanner(new File(fileName));
      gameReader.nextLine();
      String data = gameReader.nextLine();

      setBoardSize(Integer.parseInt(data));
      grid = new Cell[boardSize][boardSize];
      cellNumber = boardSize * boardSize;
      fullCells = 0;
      isGameRecorded = false;

      initGame();
      return true;
    }

    catch (FileNotFoundException e) {
      return false;
    }
  }

  public boolean nextReplayMove() throws IOException {
    if (gameReader.hasNextLine()) {
      String data = gameReader.nextLine();
      String[] dataList = data.split(" ");
      
      int row = Integer.parseInt(dataList[0]);
      int column = Integer.parseInt(dataList[1]);
      Cell letter = parseCell(dataList[2]);
      
      makeMove(row, column, letter);
      
      return true;
    }
    else {
      return false;
    }
  }

  /**
   * Places a letter on an empty cell and updates game.
   * @param selectedLetter Letter to place in empty cell.
   * @throws IOException 
   */
  public void makeMove(int row, int column, Cell selectedLetter) throws IOException {
    if (selectedLetter == Cell.EMPTY) {
      switch (turn) {
      case BLUE:
        bluePlayer = new ComputerPlayer(bluePlayer.getSosList());
        break;
      case RED:
        redPlayer = new ComputerPlayer(redPlayer.getSosList());
        break;
      default:
        break;
      }
    }
    else {
      switch (turn) {
      case BLUE:
        bluePlayer = new Player(bluePlayer.getSosList());
        break;
      case RED:
        redPlayer = new Player(redPlayer.getSosList());
        break;
      default:
        break;
      }
    }

    if (currentPlayer().takeTurn(row, column, selectedLetter, grid, getTurn())) {

      fullCells++;

      if (isGameRecorded) {
        fileWriter.write(String.format("%d %d " + currentPlayer().getLastSelectedLetter().toString() + "\n", currentPlayer().getLastSelectedRow(), currentPlayer().getLastSelectedColumn()));
      }

      updateGame(currentPlayer().getLastSelectedRow(), currentPlayer().getLastSelectedColumn(), currentPlayer().getLastSelectedLetter());

      if (currentGameState != GameState.PLAYING && isGameRecorded) {
        fileWriter.close();
      }
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
          currentPlayer().addSosList(sos);

          sosFormed = true;
        }
      }
      catch (ArrayIndexOutOfBoundsException e) {

      }
    }

    // Vertical SOS
    try {
      if ((grid[row - 1][column] == Cell.BLUE_S || grid[row - 1][column] == Cell.RED_S)
          && (grid[row + 1][column] == Cell.BLUE_S || grid[row + 1][column] == Cell.RED_S)) {

        // Append SOS to list of turn's SOSs
        int[] sos = {column, row - 1, column, row + 1};
        currentPlayer().addSosList(sos);

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
            currentPlayer().addSosList(sos);

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