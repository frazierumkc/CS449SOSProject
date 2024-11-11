package sprint4.product;

public class SosGeneralGame extends SosGame {

  public SosGeneralGame() {
    super();
  }

  public SosGeneralGame(int size) {
    super(size);
  }

  /**
   * Check if SOS has been formed or if game should end, and change turn if needed.
   * @param row Row of last letter placed
   * @param column Column of last letter placed
   * @param selectedLetter Letter type of last letter placed.
   */
  @Override
  protected void updateGame(int row, int column, Cell selectedLetter) {

    // Check if an SOS is formed from placed letter
    switch (selectedLetter) {
    case Cell.BLUE_S:
      if (!checkFormedSosS(row, column)) {
        switchTurn();
      }
      break;
    case Cell.RED_S:
      if (!checkFormedSosS(row, column)) {
        switchTurn();
      }
      break;
    case Cell.BLUE_O:
      if (!checkFormedSosO(row, column)) {
        switchTurn();
      }
      break;
    case Cell.RED_O:
      if (!checkFormedSosO(row, column)) {
        switchTurn();
      }
      break;
    default:
      break;
    }

    // Check if board is full and determine winner
    if (fullCells == cellNumber) {
      if (bluePlayer.sosList.size() > redPlayer.sosList.size()) {
        setGameState(GameState.BLUE_WON);
      }
      else if (bluePlayer.sosList.size() < redPlayer.sosList.size()) {
        setGameState(GameState.RED_WON);
      }
      else {
        setGameState(GameState.DRAW);
      }
    }
  }

}