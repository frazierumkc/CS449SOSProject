package sprint4.product;

import java.util.ArrayList;
import sprint4.product.SosGame.Cell;
import sprint4.product.SosGame.Turn;

public class Player {

  protected ArrayList<int[]> sosList;
  protected int lastSelectedRow;
  protected int lastSelectedColumn;
  protected Cell lastSelectedLetter;

  public Player() {
    sosList = new ArrayList<int[]>();
  }
  
  public Player(ArrayList<int[]> oldList) {
    sosList = oldList;
  }
  
  public ArrayList<int[]> getSosList(){
    return sosList;
  }

  public int getLastSelectedRow() {
    return lastSelectedRow;
  }
  
  public int getLastSelectedColumn() {
    return lastSelectedColumn;
  }
  
  public Cell getLastSelectedLetter() {
    return lastSelectedLetter;
  }
  
  /**
   * Adds an SOS to the player's SOS list.
   * @param sos SOS in the format {x1, y1, x2, y2}.
   */
  public void addSosList(int[] sos) {
    sosList.add(sos);
  }
  
  public boolean takeTurn(int row, int column, Cell selectedLetter, Cell[][] grid, Turn turn) {
    if (row >= 0 && row < grid.length && column >= 0 && column < grid.length && grid[row][column] == Cell.EMPTY) {

      grid[row][column] = selectedLetter;
      
      lastSelectedRow = row;
      lastSelectedColumn = column;
      lastSelectedLetter = selectedLetter;
      
      return true;
    }
    else {
      return false;
    }
  }
  
}