package project_04;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class uses the CheckerPiece and CheckerBoard class to create a CheckerGame JFrame window.
 * @author jaize John Naizer
 * CSE 271
 */
@SuppressWarnings("serial")
public class CheckerGame extends JFrame implements ActionListener {

	// 2D char array that is only used to set up the board to start the game
	private char[][] boardStatus
	 = new char[][]{
	 {'e','b','e','b','e','b','e','b'},
	 {'b','e','b','e','b','e','b','e'},
	 {'e','b','e','b','e','b','e','b'},
	 {'e','e','e','e','e','e','e','e'},
	 {'e','e','e','e','e','e','e','e'},
	 {'r','e','r','e','r','e','r','e'},
	 {'e','r','e','r','e','r','e','r'},
	 {'r','e','r','e','r','e','r','e'}
	};
	
	// creates a CheckerBoard variable
	private CheckerBoard checkerBoard;
	
	// creates individual status bar labels
	public static JLabel statusTurnText;
	public static JLabel statusNumRedText;
	public static JLabel statusNumBlackText;
	private JLabel developedByText;
	
	// creates the status bar panel
	private JPanel statusBar;
	
	// creates the menu bar that will be shown at the top of the window
	private JMenuBar menuBar;
	
	// creates the menu bar tabs
	private JMenu game;
	private JMenu help;
	private JMenu themes;
	
	// creates the items that can be selected in the menu tabs
	private JMenuItem newGame;
	private JMenuItem exitGame;
	private JMenuItem checkerGameRules;
	private JMenuItem about;
	private JMenuItem note;
	private JMenuItem gameRoom;
	private JMenuItem aqua;
	private JMenuItem tournament;
	private JMenuItem light;
	
	/**
	 * This constructor creates a new CheckerGame window object.
	 */
	public CheckerGame() {
		
		// sets the title of the window
		this.setTitle("Checkers The Game");
		
		// sets the size of the window in pixels
		this.setSize(505, 585);
		
		// makes sure the program closes when user clicks on the x
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// sets the layout of the window to border layout
		this.setLayout(new BorderLayout());
		
		// creates a new checker board that is initialized by the 2D char array boardStatus 
		checkerBoard = new CheckerBoard(boardStatus);
		
		// initializes the JPanel status bar and sets the layout to border layout
		statusBar = new JPanel();
		statusBar.setLayout(new BorderLayout());
		
		// initializes the label that displays whose turn it is and aligns it to center
		statusTurnText = new JLabel("Black's Turn");
		statusTurnText.setHorizontalAlignment(JLabel.CENTER);
		
		// initializes the label that displays who developed the game and aligns it to center
		developedByText = new JLabel("This game was developed by John Naizer");
		developedByText.setHorizontalAlignment(JLabel.CENTER);
		
		// initializes the label that displays how many red checker are left and aligns it to center
		statusNumRedText = new JLabel("Red Checkers Left: 12");
		statusNumRedText.setHorizontalAlignment(JLabel.CENTER);
		
		// initializes the label that displays how many black checker are left and aligns it to center
		statusNumBlackText = new JLabel("Black Checkers Left: 12");
		statusNumBlackText.setHorizontalAlignment(JLabel.CENTER);
		
		// initializes the menu bar
		menuBar = new JMenuBar();
		
		// initializes the menus
		game = new JMenu("Game");
		help = new JMenu("Help");
		themes = new JMenu("Themes");
		
		// initializes the menu items for each menu
		newGame = new JMenuItem("New");
		exitGame = new JMenuItem("Exit");
		checkerGameRules = new JMenuItem("Checker Game Rules");
		about = new JMenuItem("About Checker Game App");
		note = new JMenuItem("Note from Developer");
		gameRoom = new JMenuItem("Game Room");
		aqua = new JMenuItem("Aqua");
		tournament = new JMenuItem("Tournament");
		light = new JMenuItem("Light");
		
		// adds action listener to all the JMenuItems so when the user clicks on them, they execute an action
		newGame.addActionListener(this);
		exitGame.addActionListener(this);
		checkerGameRules.addActionListener(this);
		about.addActionListener(this);
		note.addActionListener(this);
		gameRoom.addActionListener(this);
		aqua.addActionListener(this);
		tournament.addActionListener(this);
		light.addActionListener(this);
		
		// adds all the JMenuItems to the JMenus
		game.add(newGame);
		game.add(exitGame);
		help.add(checkerGameRules);
		help.add(about);
		help.add(note);
		themes.add(gameRoom);
		themes.add(aqua);
		themes.add(tournament);
		themes.add(light);
		
		// adds all the JMenus to the JMenuBar
		menuBar.add(game);
		menuBar.add(help);
		menuBar.add(themes);
		
		// adds the JMenuBar to the JPanel
		this.setJMenuBar(menuBar);
		
		// adds the checker board to the JPanel and aligns it to center
		this.add(checkerBoard, BorderLayout.CENTER);
		
		// adds the status bar JLabels to the status bar JPanel
		statusBar.add(statusTurnText, BorderLayout.CENTER);
		statusBar.add(statusNumBlackText, BorderLayout.EAST);
		statusBar.add(statusNumRedText, BorderLayout.WEST);
		statusBar.add(developedByText, BorderLayout.SOUTH);
		
		// adds the status bar JPanel to the JFrame
		this.add(statusBar, BorderLayout.SOUTH);
	}
	
	/**
	 * This main method initializes a new Checkers Game
	 * @param args
	 */
	public static void main(String[] args) {
		CheckerGame c1 = new CheckerGame();
		
		// makes sure the user doesn't distort the look of the game
		c1.setResizable(false);
		
		// lets the JFrame be visible
		c1.setVisible(true);
	}

	/**
	 * This method contains all the actions that the user wants to execute.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// if the user wants to start a new game, reset all the values to their initial values
		if (e.getSource() == newGame) {
			
			// resets all the CheckerPiece's statuses
			CheckerBoard.setBoardStatus(boardStatus);
			
			// repaints all CheckerPieces
			repaint();
			
			// makes sure black goes first
			CheckerPiece.blackTurn = true;
			
			// makes sure to reset the double jump in case a user resets mid double jump
			CheckerPiece.anotherJump = false;
			
			// lets the game be in progress if user resets after a win
			CheckerPiece.gameInProgress = true;
			
			// resets the text of the JLabels in the status bar
			CheckerGame.statusTurnText.setText("Black's Turn");
			CheckerGame.statusNumBlackText.setText("Black Checkers Left: 12");
			CheckerGame.statusNumRedText.setText("Red Checkers Left: 12");
		}
		
		// if the user wants to exit the program
		else if (e.getSource() == exitGame) {
			
			// terminates program
			System.exit(0);
		}
		
		// if user wants to view the link to the checker game rules
		else if(e.getSource() == checkerGameRules) {
			
			// displays a JOptionPane that displays the link
			JOptionPane.showMessageDialog(null, "https://www.wikihow.com/Play-Checkers\r\n");
		}
		
		// if the user wants to know about the app
		else if (e.getSource() == about) {
			
			// displays a JOptionPane that displays the about information containing the developer, the developer's email, and the
			// university they go to
			JOptionPane.showMessageDialog(null, "              John Naizer \n     naizerje@miamioh.edu \n          Miami University");
		}
		
		// if the user wants to see the developer's note regarding the purpose of this app
		else if (e.getSource() == note) {
			JOptionPane.showMessageDialog(null, "Please enjoy this fully tested and fully-functional Checkers game!\nThis game was developed for my CSE 271 class as a final project.");
		}
		
		// if the user wants to change the color scheme to Game Room colors
		else if (e.getSource() == gameRoom) {
			CheckerPiece.lightSquares = new Color(205, 133, 63);
			CheckerPiece.darkSquares = new Color(255, 222, 173);
			CheckerPiece.redPieces = new Color(200, 20, 60);
			CheckerPiece.blackPieces = new Color(0, 0, 0);
			CheckerPiece.redKingPieces = new Color(240,128,128);
			CheckerPiece.blackKingPieces = new Color(169, 169, 169);
			repaint();
		}
		
		// if the user wants to change the color scheme to Aqua colors
		else if (e.getSource() == aqua) {
			CheckerPiece.lightSquares = new Color(146, 210, 226);
			CheckerPiece.darkSquares = new Color(218, 251, 255);
			CheckerPiece.redPieces = new Color(255, 127, 80);
			CheckerPiece.blackPieces = new Color(30, 138, 112);
			CheckerPiece.redKingPieces = new Color(205, 77, 30);
			CheckerPiece.blackKingPieces = new Color(129, 204, 182);
			repaint();
		}
		
		// if the user wants to change the color scheme to Tournament colors
		else if (e.getSource() == tournament) {
			CheckerPiece.lightSquares = new Color(19, 98, 7);
			CheckerPiece.darkSquares = new Color(245, 245, 245);
			CheckerPiece.redPieces = new Color(206, 186, 125);
			CheckerPiece.blackPieces = new Color(0, 0, 0);
			CheckerPiece.redKingPieces = new Color(146, 126, 75);
			CheckerPiece.blackKingPieces = new Color(169, 169, 169);
			repaint();
		}
		
		// if the user wants to change the color scheme to Light colors
		else if (e.getSource() == light) {
			CheckerPiece.lightSquares = new Color(169,169,169);
			CheckerPiece.darkSquares = new Color(211,211,211);
			CheckerPiece.redPieces = new Color(255, 255, 255);
			CheckerPiece.blackPieces = new Color(105,105,105);
			CheckerPiece.redKingPieces = new Color(105,105,105);
			CheckerPiece.blackKingPieces = new Color(255, 255, 255);
			repaint();
		}
		
	}
}
