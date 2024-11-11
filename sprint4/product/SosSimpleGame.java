package sprint4.product;

public class SosSimpleGame extends SosGame {

  public SosSimpleGame() {
    super();
  }
  
  public SosSimpleGame(int size) {
    super(size);
  }
  

  /**
   * Check if game should end or change turn otherwise.
   * @param row Row of last letter placed
   * @param column Column of last letter placed
   * @param selectedLetter Letter type of last letter placed.
   */
  @Override
  protected void updateGame(int row, int column, Cell selectedLetter) {
    
    // Check if an SOS is formed from placed letter
    switch (selectedLetter) {
    case Cell.BLUE_S:
      if (checkFormedSosS(row, column)) {
        setGameState(GameState.BLUE_WON);
      }
      break;
    case Cell.RED_S:
      if (checkFormedSosS(row, column)) {
        setGameState(GameState.RED_WON);
      }
      break;
    case Cell.BLUE_O:
      if (checkFormedSosO(row, column)) {
        setGameState(GameState.BLUE_WON);
      }
      break;
    case Cell.RED_O:
      if (checkFormedSosO(row, column)) {
        setGameState(GameState.RED_WON);
      }
      break;
    default:
      break;
    }
    
    // Check if board is full
    if (fullCells == cellNumber && getGameState() == GameState.PLAYING) {
      setGameState(GameState.DRAW);
    }

    switchTurn();
  }
  
}