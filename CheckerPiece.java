package project_04;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
/**
 * This class creates a CheckerPiece component that can be displayed in a grid.
 * This class also contains the MouseListener actions that occur when playing the game.
 * @author jaize John Naizer
 * CSE 271
 */
@SuppressWarnings("serial")
public class CheckerPiece extends JComponent implements MouseListener {

	// instance variables that are used to create the CheckerPiece components
	char status;
	int row;
	int column;
	
	// int variables that store the rows and columns of the CheckerPieces that
	// the user clicks on when they want to move a CheckerPiece
	public static int tempRow;
	public static int tempColumn;
	public static int destRow;
	public static int destColumn;
	
	// boolean variable that keeps track of who's turn it is during the game
	public static boolean blackTurn = true;
	
	// three boolean variables that are used to make sure the first click the user
	// makes is the piece that needs to be moved and the second click is the destination
	// they want to move it to
	public static boolean destMove = false;
	public static boolean makeMove = false;
	public static boolean madeMove = false;
	
	// boolean variable that keeps track of if the move the user made requires
	// another consecutive jump called a "double jump" per game rules.
	public static boolean anotherJump = false;
	
	// boolean variable that keeps track of if the game is in progress or
	// a user won
	public static boolean gameInProgress = true;
	
	// boolean variable that makes sure to check to see if one of the colors
	// still can move, and if not then the other color wins
	public static boolean checkNull = false;

	// these color variables allow user to switch between board color themes
	public static Color lightSquares = new Color(205, 133, 63);
	public static Color darkSquares = new Color(255, 222, 173);
	public static Color redPieces = new Color(200, 20, 60);
	public static Color blackPieces = new Color(0, 0, 0);
	public static Color redKingPieces = new Color(240,128,128);
	public static Color blackKingPieces = new Color(169, 169, 169);
	
	
	/**
	 * This constructor creates a CheckPiece object
	 * @param row a row
	 * @param column a column
	 * @param status a status
	 * @throws Exception
	 */
	public CheckerPiece(int row, int column, char status) throws Exception {
		
		// adds a MouseListener that allows actions to be taken if user clicks on a CheckerPiece
		addMouseListener(this);
		
		// makes sure that the CheckerPieces that are created only are created in legal checkerboard spots
		// and if not, then throw an IllegalCheckerboardArgumentException
		if (row <= 7 && row >= 0 && column <= 7 && column >= 0) {
			if (status == 'e' || status == 'b' || status == 'r') {
				if ((row % 2 == 1 && column % 2 == 0) || (row % 2 == 0 && column % 2 == 1)) {
					if ((status == 'b' || status == 'r') && (row <= 2 || row >= 5)) {
						this.row = row;
						this.column = column;
						this.status = status;
					}
					else if (status == 'e' && (row > 2 && row < 5)) {
						this.row = row;
						this.column = column;
						this.status = status;
					}
					else {
						throw new IllegalCheckerboardArgumentException();
					}
				}
				else if (((row % 2 == 0 && column % 2 == 0) ||  (row % 2 == 1 && column % 2 == 1)) && status == 'e') {
					this.row = row;
					this.column = column;
					this.status = status;
				}
				else {
					throw new IllegalCheckerboardArgumentException();
				}
			}
			else {
				throw new IllegalCheckerboardArgumentException();
			}
		}
		else {
			throw new IllegalCheckerboardArgumentException();
		}
	}

	/**
	 * This method paints all CheckerPiece components based on their status values and locations.
	 */
	public void paintComponent(Graphics g) {
		
		// if row and column are even and odd or odd and even, paint the square lightSquares color
		if ((row % 2 == 1 && column % 2 == 0) || (row % 2 == 0 && column % 2 == 1)) {
			g.setColor(lightSquares);
			g.fillRect(0, 0, 63, 63);
		}
		
		// if row and column are both even or both odd, paint the square darkSquares color
		else if (((row % 2 == 0 && column % 2 == 0) ||  (row % 2 == 1 && column % 2 == 1)) && status == 'e') {
			g.setColor(darkSquares);
			g.fillRect(0, 0, 63, 63);
		}
		
		// if CheckerPiece status is a black piece or a black king, paint a blackPieces colored circle
		if (status == 'b' || status == 'k') {
			g.setColor(blackPieces);
			g.fillOval(10, 10, 40, 40);
			
			// if CheckerPiece status is a black king, paint a blackKingPieces colored crown (polygon and rectangle)
			if (status == 'k') {
				g.setColor(blackKingPieces);
				int[] xPoints = {30, 17, 14, 24, 30, 36, 46, 43};
				int[] yPoints = {35, 35, 27, 31, 20, 31, 27, 35};
				int numPoints = 8;
				g.fillPolygon(xPoints, yPoints, numPoints);
				g.fillRect(17, 37, 27, 3);
			}
		}
		
		// if CheckerPiece status is a red piece or a red king, paint a redPieces colored circle
		else if (status == 'r' || status == 'q') {
			g.setColor(redPieces);
			g.fillOval(10, 10, 40, 40);
			
			// if CheckerPiece status is a red king, paint a redKingPieces colored crown (polygon and rectangle)
			if (status == 'q') {
				g.setColor(redKingPieces);
				int[] xPoints = {30, 17, 14, 24, 30, 36, 46, 43};
				int[] yPoints = {35, 35, 27, 31, 20, 31, 27, 35};
				int numPoints = 8;
				g.fillPolygon(xPoints, yPoints, numPoints);
				g.fillRect(17, 37, 27, 3);
				
			}
		}
		
		
		// paints the borders of the checker squares with black lines
		g.setColor(new Color(0, 0, 0));
		g.drawLine(0, 0, 63, 0);
		g.drawLine(63, 0, 63, 63);
		g.drawLine(63, 63, 0, 63);
		g.drawLine(0, 63, 0, 0);
	}

	/**
	 * Executes events if mouse is clicked.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * Executes events if mouse is entered.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Executes events if mouse is exited.
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Executes events if mouse is pressed.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		
		// makes sure the click is a fresh turn
		makeMove = false;
		
		// if it's black's turn, then only store click values if user first clicks on
		// a black checker piece and then an empty checker square
		if (blackTurn) {
			if (this.status == 'b' || this.status == 'k') {
				tempRow = this.row;
				tempColumn = this.column;
				destMove = true;
			}
		}
		
		// if it's red's turn, then only store click values if user first clicks on
		// a red checker piece and then an empty checker square
		if (!blackTurn) {
			if (this.status == 'r' || this.status == 'q') {
				tempRow = this.row;
				tempColumn = this.column;
				destMove = true;
			}
		}
		
		// if the user first clicked on a colored checker, then store the values of
		// the empty square they clicked on next
		if (destMove && this.status == 'e') {
			destRow = this.row;
			destColumn = this.column;
			destMove = false;
			makeMove = true;
		}
		
		// if user successfully clicked on a checker piece and then a blank square, then allow the user
		// to make the move
		if (makeMove && gameInProgress) {
			
			// pulls all the legal moves for the color's turn from the instance of the checkerboard
			// and then creates a move from the user's inputs.
			// If the user's move is in the legal moves, then make the move. If not, then allow user to try again.
			// **Any move in general is a string with four int values. The first two ints are the row and column of the
			// checker piece and the last two ints are the destination row and column.**
			String[] legalMoves = CheckerBoard.legalMovesOverall();
			String tempRowString = Integer.toString(tempRow);
			String tempColumnString = Integer.toString(tempColumn);
			String destRowString = Integer.toString(destRow);
			String destColumnString = Integer.toString(destColumn);
			String move = tempRowString + tempColumnString + destRowString + destColumnString;
			
			// checks to see if user's move is in the legal moves
			for (String legalMove : legalMoves) {
				if (move.equalsIgnoreCase(legalMove)) {
					CheckerBoard.makeMove(tempRow, tempColumn, destRow, destColumn);

					// if the move is a jump, check to see if user can double jump
					if (Math.abs(tempRow - destRow) == 2) {
						anotherJump = CheckerBoard.canDoubleJump(destRow, destColumn);
					}
					
					// if user can't double jump, set anotherJump to false
					else {
						anotherJump = false;
					}
					
					// if no more jumps or no jumps in the first place, switch the turn
					if (!anotherJump) {
						checkNull = true;
						blackTurn = !blackTurn;
					}
					// if the user's move is legal, stop checking the other legal moves
					break;
				}
			}
			
			// checks to see if piece needs to be "kinged"
			CheckerBoard.isKing(destRow, destColumn);
			
			// puts the current checker counts on the status bar
			CheckerGame.statusNumBlackText.setText("Black Checkers Left: " + CheckerBoard.blackCount());
			CheckerGame.statusNumRedText.setText("Red Checkers Left: " + CheckerBoard.redCount());
			
			// if there are no pieces left in one of the colors, then stop the game
			if (CheckerBoard.redCount() == 0 || CheckerBoard.blackCount() == 0) {
				gameInProgress = false;
				
				// if no red checkers left, black wins
				if (CheckerBoard.redCount() == 0) {
					CheckerGame.statusTurnText.setText("Black Wins!");
				}
				
				// if no black checkers left, red wins
				else {
					CheckerGame.statusTurnText.setText("Red Wins!");
				}
			}
			
			// if no jumps need to be made and its black's turn, then display "Black's Turn" in status bar
			else if (blackTurn && !anotherJump) {
				CheckerGame.statusTurnText.setText("Black's Turn");
			}
			
			// if no jumps need to be made and its red's turn, then display "Red's Turn" in status bar
			else if (!blackTurn && !anotherJump){
				CheckerGame.statusTurnText.setText("Red's Turn");
			}
			
			// if another jump needs to be made, display "Double Jump" in status bar
			else if (anotherJump) {
				CheckerGame.statusTurnText.setText("Double Jump!");
				
			}
			
			// repaints all CheckerPiece components on the board
			repaintAll();
		}
		
		// makes sure that the upcoming color's turn can still move at all and if not,
		// then the color that just went wins
		if (checkNull) {
			String[] legalMoves = CheckerBoard.legalMovesOverall();
			if (legalMoves.length == 0) {
				if (blackTurn) {
					CheckerGame.statusTurnText.setText("Red Wins!");
				}
				else {
					CheckerGame.statusTurnText.setText("Black Wins!");
				}
			}
			checkNull = false;
		}
	}

	/**
	 * Executes events if mouse is released.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	// method that repaints all CheckerPieces on the CheckerBoard
	public static void repaintAll() {
		for (CheckerPiece[] ca : CheckerBoard.checkerPieces) {
			for (CheckerPiece c : ca) {
				c.repaint();
			}
		}
	}
}
