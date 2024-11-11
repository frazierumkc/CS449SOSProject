package sprint4.product;

import java.util.ArrayList;
import java.util.Random;

import sprint4.product.SosGame.Cell;
import sprint4.product.SosGame.Turn;

public class ComputerPlayer extends Player {

  private Random rand = new Random();

  public ComputerPlayer() {
    super();
  }

  public ComputerPlayer(ArrayList<int[]> oldList) {
    super(oldList);
  }

  public boolean takeTurn(int row, int column, Cell selectedLetter, Cell[][] grid, Turn turn) {

    // Make a move that forms an SOS if available
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid.length; j++) {
        if (grid[i][j] == Cell.EMPTY) {
          if (checkFormedSosS(i, j, grid)) {
            Cell letter = (turn == Turn.BLUE) ? Cell.BLUE_S : Cell.RED_S;
            grid[i][j] = letter;
            lastSelectedRow = i;
            lastSelectedColumn = j;
            lastSelectedLetter = letter;
            return true;
          }
          else if (checkFormedSosO(i, j, grid)) {
            Cell letter = (turn == Turn.BLUE) ? Cell.BLUE_O : Cell.RED_O;
            grid[i][j] = letter;
            lastSelectedRow = i;
            lastSelectedColumn = j;
            lastSelectedLetter = letter;
            return true;
          }
        }
      }
    }

    // Make a random move on an empty cell if no SOS is available
    int randomRow = rand.nextInt(grid.length);
    int randomColumn = rand.nextInt(grid.length);
    Cell randomLetter;

    if (turn == Turn.BLUE) {
      randomLetter = (rand.nextBoolean() ? Cell.BLUE_S : Cell.BLUE_O);
    }
    else {
      randomLetter = (rand.nextBoolean() ? Cell.RED_S : Cell.RED_O);
    }

    // Find next empty cell starting from random cell
    for (int i = randomRow; true; i++) {
      for (int j = 0; j < grid.length; j++) {

        if (j + randomColumn < grid.length) {
          if (grid[i][j + randomColumn] == Cell.EMPTY) {
            grid[i][j + randomColumn] = randomLetter;
            lastSelectedRow = i;
            lastSelectedColumn = j + randomColumn;
            lastSelectedLetter = randomLetter;
            return true;
          }
        }

        else {
          if (grid[i][j - (grid.length - randomColumn)] == Cell.EMPTY) {
            grid[i][j - (grid.length - randomColumn)] = randomLetter;
            lastSelectedRow = i;
            lastSelectedColumn = j - (grid.length - randomColumn);
            lastSelectedLetter = randomLetter;
            return true;
          }
        }

      }

      if (i == grid.length - 1) {
        i = -1;
      }

    }
  }

  /**
   * Check for SOSs formed by a placed S. Does not add to SOS list.
   * @param row Row of the placed S to check
   * @param column Column of the placed S to check
   * @return True if at least 1 SOS is formed, false otherwise
   */
  private boolean checkFormedSosS(int row, int column, Cell[][] grid) {

    boolean sosFormed = false;

    // Check 5x5 grid centered around specified S

    for (int i = -1; i <=1; i++) {
      for (int j = -1; j <=1; j++) {
        try {
          int checkedRow = row + i;
          int checkedColumn = column + j;
          if ((grid[checkedRow][checkedColumn] == Cell.BLUE_O || grid[checkedRow][checkedColumn] == Cell.RED_O)
              && (grid[checkedRow + i][checkedColumn + j] == Cell.BLUE_S || grid[checkedRow + i][checkedColumn + j] == Cell.RED_S)) {

            sosFormed = true;
          }
        }
        catch (ArrayIndexOutOfBoundsException e) {
        }

      }
    }

    return sosFormed;
  }

  /**
   * Check for SOSs formed by a placed O. Does not add to SOS list.
   * @param row Row of the placed O to check
   * @param column Column of the placed O to check
   * @return True if at least 1 SOS is formed, false otherwise
   */
  private boolean checkFormedSosO(int row, int column, Cell[][] grid) {

    boolean sosFormed = false;

    // Horizontal and diagonal SOSs
    for (int i = -1; i <= 1; i++) {
      try {
        if ((grid[row + i][column - 1] == Cell.BLUE_S || grid[row + i][column - 1] == Cell.RED_S)
            && (grid[row - i][column + 1] == Cell.BLUE_S || grid[row - i][column + 1] == Cell.RED_S)) {

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

        sosFormed = true;
      }
    }
    catch (ArrayIndexOutOfBoundsException e) {

    }

    return sosFormed;

  }

}