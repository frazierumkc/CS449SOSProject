package sprint3.product;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import sprint3.product.SosGame.*;


public class SosGui {

  // Determine cell size and derived variables by height of screen resolution
  GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
  private int cellSize = gd.getDisplayMode().getHeight() / 21;
  private int cellPadding = cellSize / 6;
  private int symbolWidth = cellSize - cellPadding * 2;

  private SosGame game = new SosSimpleGame();
  private GameBoard gameBoard = new GameBoard();

  // Shows current player's turn and winner
  private JLabel turnLabel = new JLabel("Blue's Turn");
  
  // Game mode option drop-down list
  JLabel gameModeLabel = new JLabel("Game Mode  ");
  String[] gameModeChoices = {"Simple", "General"};
  JComboBox<String> gameModeComboBox = new JComboBox<String>(gameModeChoices);
  
  // Board Size label
  JLabel boardSizeLabel = new JLabel("Board Size [3-10]  ");
  
  // Board Size input field
  JTextField boardSizeInput = new JTextField("3");
  
  // Blue player S/O buttons
  JPanel bluePanel = new JPanel();
  JLabel bluePlayerLabel = new JLabel("Blue Player");
  JRadioButton blueSButton = new JRadioButton("S");
  JRadioButton blueOButton = new JRadioButton("O");
  ButtonGroup blueButtonGroup = new ButtonGroup();
  JLabel blueSosLabel = new JLabel("SOS Count: 0");
  
  // Red player S/O buttons
  JPanel redPanel = new JPanel();
  JLabel redPlayerLabel = new JLabel("Red Player");
  JRadioButton redSButton = new JRadioButton("S");
  JRadioButton redOButton = new JRadioButton("O");
  ButtonGroup redButtonGroup = new ButtonGroup();
  JLabel redSosLabel = new JLabel("SOS Count: 0");

  // New Game button
  JButton newGameButton = new JButton("New Game");
  
  JFrame frame = new JFrame("SOS Game");

  {
    frame.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();;

    c.weighty = 1;
    c.weightx = 1;
    c.anchor = GridBagConstraints.LINE_END;
    c.gridx = 0;
    c.gridy = 0;
    frame.add(gameModeLabel, c);
    c.anchor = GridBagConstraints.LINE_START;
    c.gridx = 1;
    c.gridy = 0;
    frame.add(gameModeComboBox, c);

    c.anchor = GridBagConstraints.LINE_END;
    c.gridx = 2;
    c.gridy = 0;
    frame.add(boardSizeLabel, c);

    boardSizeInput.setHorizontalAlignment(JTextField.CENTER);
    c.anchor = GridBagConstraints.LINE_START;
    c.ipadx = 10;
    c.gridx = 3;
    c.gridy = 0;
    frame.add(boardSizeInput, c);

    // Set grid anchor back to default
    c.anchor = GridBagConstraints.CENTER;
    c.ipadx = 0;

    bluePanel.setLayout(new GridLayout(4, 1, 0, 30));
    bluePlayerLabel.setForeground(Color.BLUE);
    bluePanel.add(bluePlayerLabel);

    blueSButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        gameBoard.setBlueSelection(Cell.BLUE_S);
      }
    });

    blueOButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        gameBoard.setBlueSelection(Cell.BLUE_O);
      }
    });


    blueButtonGroup.add(blueSButton);
    blueButtonGroup.add(blueOButton);
    blueSButton.setSelected(true);
    bluePanel.add(blueSButton);
    bluePanel.add(blueOButton);
    blueSosLabel.setForeground(Color.BLUE);
    bluePanel.add(blueSosLabel);
    blueSosLabel.setVisible(false);
    c.gridx = 0;
    c.gridy = 1;
    frame.add(bluePanel, c);

    redPanel.setLayout(new GridLayout(4, 1, 0, 30));
    redPlayerLabel.setForeground(Color.RED);
    redPanel.add(redPlayerLabel);

    redSButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        gameBoard.setRedSelection(Cell.RED_S);
      }
    });

    redOButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        gameBoard.setRedSelection(Cell.RED_O);
      }
    });
    
    redButtonGroup.add(redSButton);
    redButtonGroup.add(redOButton);
    redSButton.setSelected(true);
    redPanel.add(redSButton);
    redPanel.add(redOButton);
    redSosLabel.setForeground(Color.RED);
    redPanel.add(redSosLabel);
    redSosLabel.setVisible(false);
    c.gridx = 3;
    c.gridy = 1;
    frame.add(redPanel, c);

    turnLabel.setForeground(Color.BLUE);
    c.gridwidth = 2;
    c.gridx = 1;
    c.gridy = 2;
    frame.add(turnLabel, c);

    c.gridx = 3;
    c.gridy = 2;
    frame.add(newGameButton, c);
    newGameButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        SosGame newGame = null;

        // Choose between Simple and General game mode
        switch ((String) gameModeComboBox.getSelectedItem()) {
        case "Simple":
          newGame = new SosSimpleGame();
          blueSosLabel.setVisible(false);
          redSosLabel.setVisible(false);
          break;
        case "General":
          newGame = new SosGeneralGame();
          blueSosLabel.setVisible(true);
          redSosLabel.setVisible(true);
          break;
        }

        // Update GUI and board if new game creation is successful
        if (newGame.newGame(boardSizeInput.getText())) {
          game = newGame;
          turnLabel.setText("Blue's Turn");
          turnLabel.setForeground(Color.BLUE);
          blueSosLabel.setText("SOS Count: 0");
          redSosLabel.setText("SOS Count: 0");
          int gameBoardDimension = cellSize * game.getBoardSize();
          gameBoard.setPreferredSize((new Dimension(gameBoardDimension, gameBoardDimension)));
          gameBoard.repaint();
          frame.setSize(gameBoardDimension + 300, gameBoardDimension + 300);
        }
      }
    });

    // Draw initial Game Board
    int gameBoardDimension = cellSize * game.getBoardSize();
    gameBoard.setPreferredSize((new Dimension(gameBoardDimension , gameBoardDimension)));
    c.gridx = 1;
    c.gridy = 1;
    frame.add(gameBoard, c);

    frame.setSize(gameBoardDimension + 300, gameBoardDimension + 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    frame.setVisible(true);

  } 

  // Draw dividing lines
  @SuppressWarnings("serial")
  class GameBoard extends JPanel {

    // Update game and board when player clicks a cell to make a move
    GameBoard() {
      addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          if (game.getGameState() == GameState.PLAYING) {
            int rowSelected = e.getY() / cellSize;
            int colSelected = e.getX() / cellSize;
            game.makeMove(rowSelected, colSelected, (game.getTurn() == Turn.BLUE) ? blueSelection : redSelection);
            // Update the SOS counter labels
            blueSosLabel.setText(String.format("SOS Count: %d", game.getBlueSosList().size()));
            redSosLabel.setText(String.format("SOS Count: %d", game.getRedSosList().size()));
            // Update the turn label
            switch (game.getGameState()) {
            case PLAYING:
              turnLabel.setText((game.getTurn() == Turn.BLUE) ? "Blue's Turn" : "Red's Turn");
              turnLabel.setForeground((game.getTurn() == Turn.BLUE) ? Color.BLUE : Color.RED);
              break;
            case BLUE_WON:
              turnLabel.setText("Blue Wins!!!");
              turnLabel.setForeground(Color.BLUE);
              break;
            case RED_WON:
              turnLabel.setText("Red Wins!!!");
              turnLabel.setForeground(Color.RED);
              break;
            case DRAW:
              turnLabel.setText("It's a draw...");
              turnLabel.setForeground(Color.BLACK);
              break;
            default:
              break;
            }
          }
          repaint();
        }
      });
    } 

    // Which option (S or O) blue player has selected
    private Cell blueSelection = Cell.BLUE_S;

    public void setBlueSelection (Cell selection){
      blueSelection = selection;
    }

    // Which option (S or O) red player has selected
    private Cell redSelection = Cell.RED_S;

    public void setRedSelection (Cell selection){
      redSelection = selection;
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      setBackground(Color.WHITE);
      drawGridLines(g);
      drawBoard(g);

    }

    // Draw grid lines between cells
    private void drawGridLines(Graphics g) {
      g.setColor(Color.LIGHT_GRAY);
      for (int row = 0; row < game.getBoardSize() + 1; ++row) {
        g.fillRect(0, cellSize * row - 4, cellSize * game.getBoardSize(), 8);
      }
      for (int col = 0; col < game.getBoardSize() + 1; ++col) {
        g.fillRect(cellSize * col - 4, 0, 8, cellSize * game.getBoardSize());
      }

    }

    // Draw S/O symbols and SOS completion lines on board
    private void drawBoard(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setStroke(new BasicStroke(8, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
      // S/O symbols
      for (int row = 0; row < game.getBoardSize(); ++row) {
        for (int col = 0; col < game.getBoardSize(); ++col) {
          int x1 = col * cellSize + cellPadding;
          int y1 = row * cellSize + cellPadding;

          switch (game.getCell(row, col)) {
          case Cell.BLUE_S:
            g2d.setColor(Color.BLUE);
            g2d.drawArc(x1 + cellPadding, y1, symbolWidth / 2, symbolWidth / 2, 0, 270);
            g2d.drawArc(x1 + cellPadding, y1 + cellSize / 3, symbolWidth / 2, symbolWidth /2 , 90, -270);
            break;
          case Cell.BLUE_O:
            g2d.setColor(Color.BLUE);
            g2d.drawOval(x1, y1, symbolWidth, symbolWidth);
            break;
          case Cell.RED_S:
            g2d.setColor(Color.RED);
            g2d.drawArc(x1 + cellPadding, y1, symbolWidth / 2, symbolWidth / 2, 0, 270);
            g2d.drawArc(x1 + cellPadding, y1 + cellSize / 3, symbolWidth / 2, symbolWidth /2 , 90, -270);
            break;
          case Cell.RED_O:
            g2d.setColor(Color.RED);
            g2d.drawOval(x1, y1, symbolWidth, symbolWidth);
            break;
          default:
            break;
          }
        }
      }
      // SOS completion lines
      for (int[] sosPair : game.getBlueSosList()) {
        int x1 = sosPair[0] * cellSize + cellSize / 2;
        int y1 = sosPair[1] * cellSize + cellSize / 2;
        int x2 = sosPair[2] * cellSize + cellSize / 2;
        int y2 = sosPair[3] * cellSize + cellSize / 2;
        g2d.setColor(new Color(3, 211, 255));
        g2d.drawLine(x1, y1, x2, y2);
      }
      for (int[] sosPair : game.getRedSosList()) {
        int x1 = sosPair[0] * cellSize + cellSize / 2;
        int y1 = sosPair[1] * cellSize + cellSize / 2;
        int x2 = sosPair[2] * cellSize + cellSize / 2;
        int y2 = sosPair[3] * cellSize + cellSize / 2;
        g2d.setColor(new Color(255, 122, 51));
        g2d.drawLine(x1, y1, x2, y2);
      }
    }
  }


  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new SosGui(); 
      }
    });
  }
}