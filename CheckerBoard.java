package project_04;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * This class uses the CheckerPieces class to create a board layout for the Checkers Game.
 * @author jaize John Naizer
 * CSE 271
 */
@SuppressWarnings("serial")
public class CheckerBoard extends JPanel {
	
	// creates a 2D char array that contains the status values of all the checker pieces
	char[][] boardStatus = new char[8][8];
	
	// creates a 2D object array of type CheckerPiece that contains all the checker pieces
	public static CheckerPiece[][] checkerPieces = new CheckerPiece[8][8];
	CheckerPiece cp;
	
	/**
	 * This constructor creates a CheckerBoard with the input boardStatus values.
	 * @param boardStatus
	 */
	public CheckerBoard(char [][] boardStatus) {
		this.boardStatus = boardStatus;
		
		// creates a grid layout for the input CheckerPieces
		this.setLayout(new GridLayout(0, 8));
		
		// inputs the CheckerPieces into the CheckerBoard and the checkerPieces object array
		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				char status = boardStatus[i][j];
				try {
					cp = new CheckerPiece(i, j, status);
					checkerPieces[i][j] = cp;
					this.add(cp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * This method allows programmer to set the boardStatus of the checkerPieces object array.
	 * @param boardStatus
	 */
	public static void setBoardStatus(char[][] boardStatus) {
		int i = 0;
		
		// for loop that changes all the status values in the checkerPieces object array
		for (CheckerPiece[] ca : checkerPieces) {
			int j = 0;
			for (CheckerPiece c : ca) {
				c.status = boardStatus[i][j];
				j++;
			}
			i++;
		}
	}
	
	/**
	 * This method allows programmer to set an individual CheckerPiece's status in
	 * the checkerPieces object array.
	 * @param row a row
	 * @param column a column
	 * @param status a status
	 */
	public static void setCheckerPiece(int row, int column, char status) {
		for (CheckerPiece[] ca : checkerPieces) {
			
			// for loop that finds the desired CheckerPiece and then changes its status
			for (CheckerPiece c : ca) {
				if (c.row == row && c.column == column) {
					c.status = status;
				}
			}
		}
	}
	
	/**
	 * This method allows programmer to get a CheckerPiece's status.
	 * @param row
	 * @param column
	 * @return char
	 */
	public static char getCheckerPiece(int row, int column) {
		return checkerPieces[row][column].status;
	}
	
	/**
	 * This method determines if a CheckerPiece is able to move at all given where the CheckerPiece is
	 * and where its destination is.
	 * @param r1 a row
	 * @param c1 a column
	 * @param r2 a row
	 * @param c2 a column
	 * @return boolean
	 */
	public static boolean canMove(int r1,int c1,int r2,int c2) {
		
		// if the destination location is on the board and no other CheckerPiece is occupying it, return true
		if ((r2 >= 0 && r2 <= 7) && (c2 >= 0 && c2 <= 7)) {
			if (getCheckerPiece(r2, c2) == 'e') {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method determines if a CheckerPiece is able to jump at all given where the CheckerPiece is
	 * and where its destination is.
	 * @param r1 a row
	 * @param c1 a column
	 * @param r2 a row
	 * @param c2 a column
	 * @param r3 a row
	 * @param c3 a column
	 * @return boolean
	 */
	public static boolean canJump(int r1,int c1,int r2,int c2, int r3, int c3) {
		
		// if the destination location is on the board
		if ((r3 >= 0 && r3 <= 7) && (c3 >= 0 && c3 <= 7)) {
			
			// if the destination square is not occupied
			if (getCheckerPiece(r3, c3) == 'e') {
				
				// these if statements make sure that the piece being jumped is not the same color as the piece that is jumping
				if ((getCheckerPiece(r1, c1) == 'b' && getCheckerPiece(r2, c2) == 'r') || (getCheckerPiece(r1, c1) == 'r' && getCheckerPiece(r2, c2) == 'b')) {
					return true;
				}
				else if ((getCheckerPiece(r1, c1) == 'b' && getCheckerPiece(r2, c2) == 'q') || (getCheckerPiece(r1, c1) == 'q' && getCheckerPiece(r2, c2) == 'b')) {
					return true;
				}
				else if ((getCheckerPiece(r1, c1) == 'k' && getCheckerPiece(r2, c2) == 'r') || (getCheckerPiece(r1, c1) == 'r' && getCheckerPiece(r2, c2) == 'k')) {
					return true;
				}
				else if ((getCheckerPiece(r1, c1) == 'k' && getCheckerPiece(r2, c2) == 'q') || (getCheckerPiece(r1, c1) == 'q' && getCheckerPiece(r2, c2) == 'k')) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * This method returns all the legal moves of any of the checker pieces of either black's turn or red's turn, depending on
	 * whose turn it is.
	 * @return String[]
	 */
	public static String[] legalMoves() {
		
		// creates an ArrayList that will be used to contain all the legal moves.
		ArrayList<String> legalMoves = new ArrayList<String>();
		
		// nested for loop that goes through each CheckerPiece and sees if it can move and stores the moves in the legalMoves array list
		for (CheckerPiece[] ca : checkerPieces) {
			for (CheckerPiece c : ca) {
				
				// if it is black's turn
				if (CheckerPiece.blackTurn == true) {
					
					// if it is a black checker piece
					if (c.status == 'b' || c.status == 'k') {
						
						// if it can move to the lower right
						if (canMove(c.row, c.column, c.row + 1, c.column + 1)) {
							String row = Integer.toString(c.row);
							String column = Integer.toString(c.column);
							String destRow = Integer.toString(c.row + 1);
							String destColumn = Integer.toString(c.column + 1);
							String move = row + column + destRow + destColumn;
							legalMoves.add(move);
						}
						
						// if it can move to the lower left
						if (canMove(c.row, c.column, c.row + 1, c.column - 1)) {
							String row = Integer.toString(c.row);
							String column = Integer.toString(c.column);
							String destRow = Integer.toString(c.row + 1);
							String destColumn = Integer.toString(c.column - 1);
							String move = row + column + destRow + destColumn;
							legalMoves.add(move);
						}
						
						// if the black checker piece is a king
						if (c.status == 'k') {
							
							// if it can move to the upper left
							if (canMove(c.row, c.column, c.row - 1, c.column - 1)) {
								String row = Integer.toString(c.row);
								String column = Integer.toString(c.column);
								String destRow = Integer.toString(c.row - 1);
								String destColumn = Integer.toString(c.column - 1);
								String move = row + column + destRow + destColumn;
								legalMoves.add(move);
							}
							
							// if it can move to the upper right
							if (canMove(c.row, c.column, c.row - 1, c.column + 1)) {
								String row = Integer.toString(c.row);
								String column = Integer.toString(c.column);
								String destRow = Integer.toString(c.row - 1);
								String destColumn = Integer.toString(c.column + 1);
								String move = row + column + destRow + destColumn;
								legalMoves.add(move);
							}
						}
					}
				}
				
				// if it is red's turn
				else {
					
					// if it is a red checker piece
					if (c.status == 'r' || c.status == 'q') {
						
						// if it can move to the upper left
						if (canMove(c.row, c.column, c.row - 1, c.column - 1)) {
							String row = Integer.toString(c.row);
							String column = Integer.toString(c.column);
							String destRow = Integer.toString(c.row - 1);
							String destColumn = Integer.toString(c.column - 1);
							String move = row + column + destRow + destColumn;
							legalMoves.add(move);
						}
						
						// if it can move to the upper right
						if (canMove(c.row, c.column, c.row - 1, c.column + 1)) {
							String row = Integer.toString(c.row);
							String column = Integer.toString(c.column);
							String destRow = Integer.toString(c.row - 1);
							String destColumn = Integer.toString(c.column + 1);
							String move = row + column + destRow + destColumn;
							legalMoves.add(move);
						}
						
						// if the red checker piece is a king
						if (c.status == 'q') {
							
							// if it can move to the lower right
							if (canMove(c.row, c.column, c.row + 1, c.column + 1)) {
								String row = Integer.toString(c.row);
								String column = Integer.toString(c.column);
								String destRow = Integer.toString(c.row + 1);
								String destColumn = Integer.toString(c.column + 1);
								String move = row + column + destRow + destColumn;
								legalMoves.add(move);
							}
							
							// if it can move to the lower left
							if (canMove(c.row, c.column, c.row + 1, c.column - 1)) {
								String row = Integer.toString(c.row);
								String column = Integer.toString(c.column);
								String destRow = Integer.toString(c.row + 1);
								String destColumn = Integer.toString(c.column - 1);
								String move = row + column + destRow + destColumn;
								legalMoves.add(move);
							}
						}
					}
				}
			}
		}
		
		// copies all the moves in the legalMoves array list to an array
		String[] result = new String[legalMoves.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = legalMoves.get(i);
		}
		return result;
	}
	
	/**
	 * This method returns all the legal jumps of any of the checker pieces of either black's turn or red's turn, depending on
	 * whose turn it is.
	 * @return String[]
	 */
	public static String[] legalJumps() {
		
		// creates an ArrayList that will be used to contain all the legal moves.
		ArrayList<String> legalJumps = new ArrayList<String>();
		
		// nested for loop that goes through each CheckerPiece and sees if it can jump and stores the jumps in the legalMoves array list
		for (CheckerPiece[] ca : checkerPieces) {
			for (CheckerPiece c : ca) {
				
				// if it is black's turn
				if (CheckerPiece.blackTurn == true) {
					
					// if it is a black checker piece
					if (c.status == 'b' || c.status == 'k') {
						
						// if it can jump to the lower right
						if (canJump(c.row, c.column, c.row + 1, c.column + 1, c.row + 2, c.column + 2)) {
							String row = Integer.toString(c.row);
							String column = Integer.toString(c.column);
							String destRow = Integer.toString(c.row + 2);
							String destColumn = Integer.toString(c.column + 2);
							String move = row + column + destRow + destColumn;
							legalJumps.add(move);
						}
						
						// if it can jump to the lower left
						if (canJump(c.row, c.column, c.row + 1, c.column - 1, c.row + 2, c.column - 2)) {
							String row = Integer.toString(c.row);
							String column = Integer.toString(c.column);
							String destRow = Integer.toString(c.row + 2);
							String destColumn = Integer.toString(c.column - 2);
							String move = row + column + destRow + destColumn;
							legalJumps.add(move);
						}
						
						// if the black checker piece is a king
						if (c.status == 'k') {
							
							// if it can jump to the upper left
							if (canJump(c.row, c.column, c.row - 1, c.column - 1, c.row - 2, c.column - 2)) {
								String row = Integer.toString(c.row);
								String column = Integer.toString(c.column);
								String destRow = Integer.toString(c.row - 2);
								String destColumn = Integer.toString(c.column - 2);
								String move = row + column + destRow + destColumn;
								legalJumps.add(move);
							}
							
							// if it can jump to the upper right
							if (canJump(c.row, c.column, c.row - 1, c.column + 1, c.row - 2, c.column + 2)) {
								String row = Integer.toString(c.row);
								String column = Integer.toString(c.column);
								String destRow = Integer.toString(c.row - 2);
								String destColumn = Integer.toString(c.column + 2);
								String move = row + column + destRow + destColumn;
								legalJumps.add(move);
							}
						}
					}
				}
				
				// if it is red's turn
				else {
					
					// if the checker piece is red
					if (c.status == 'r' || c.status == 'q') {
						
						// if it can jump to the upper left
						if (canJump(c.row, c.column, c.row - 1, c.column - 1, c.row - 2, c.column - 2)) {
							String row = Integer.toString(c.row);
							String column = Integer.toString(c.column);
							String destRow = Integer.toString(c.row - 2);
							String destColumn = Integer.toString(c.column - 2);
							String move = row + column + destRow + destColumn;
							legalJumps.add(move);
						}
						
						// if it can jump to the upper right
						if (canJump(c.row, c.column, c.row - 1, c.column + 1, c.row - 2, c.column + 2)) {
							String row = Integer.toString(c.row);
							String column = Integer.toString(c.column);
							String destRow = Integer.toString(c.row - 2);
							String destColumn = Integer.toString(c.column + 2);
							String move = row + column + destRow + destColumn;
							legalJumps.add(move);
						}
						
						// if the red checker piece is a king
						if (c.status == 'q') {
							
							// if it can jump to the lower right
							if (canJump(c.row, c.column, c.row + 1, c.column + 1, c.row + 2, c.column + 2)) {
								String row = Integer.toString(c.row);
								String column = Integer.toString(c.column);
								String destRow = Integer.toString(c.row + 2);
								String destColumn = Integer.toString(c.column + 2);
								String move = row + column + destRow + destColumn;
								legalJumps.add(move);
							}
							
							// if it can jump to the lower left
							if (canJump(c.row, c.column, c.row + 1, c.column - 1, c.row + 2, c.column - 2)) {
								String row = Integer.toString(c.row);
								String column = Integer.toString(c.column);
								String destRow = Integer.toString(c.row + 2);
								String destColumn = Integer.toString(c.column - 2);
								String move = row + column + destRow + destColumn;
								legalJumps.add(move);
							}
						}
					}
				}
			}
		}
		
		// copies all the moves in the legalMoves array list to an array
		String[] result = new String[legalJumps.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = legalJumps.get(i);
		}
		return result;
	}
	
	/**
	 * This method either returns the legal jumps or legal moves under certain restraints
	 * @return String[]
	 */
	public static String[] legalMovesOverall() {
		String[] moves = legalMoves();
		String[] jumps = legalJumps();
		
		// if there are no jumps, then return moves
		if (jumps.length == 0) {
			return moves;
		}
		
		// if there are jumps, then only return the jumps because they are **mandatory**
		else {
			return jumps;
		}
	}
	
	/**
	 * This method executes a move assuming the move is legal.
	 * @param r1 a row
	 * @param c1 a column
	 * @param r2 a row
	 * @param c2 a column
	 */
	public static void makeMove(int r1, int c1, int r2, int c2) {
		
		// these three lines swap the status values of the two CheckerPiece statuses
		char tempCheckerPiece = checkerPieces[r1][c1].status;
		checkerPieces[r1][c1].status = checkerPieces[r2][c2].status;
		checkerPieces[r2][c2].status = tempCheckerPiece;
		
		// if the move was a jump, then make sure to change the CheckerPiece object that was "jumped" to empty
		// meaning it was captured
		if (Math.abs(r1 - r2) == 2) {
			
			// finding the row and column of the jumped CheckerPiece
			int middleRow = (r1 + r2) / 2;
			int middleColumn = (c1 + c2) /2;
			
			// changing the status to empty
			checkerPieces[middleRow][middleColumn].status = 'e';
		}
	}
	
	/**
	 * This method determines if a CheckerPiece that was just moved landed on the further most
	 * row of its starting position, meaning that it needs to be "kinged".
	 * @param r1 a row
	 * @param c1 a column
	 */
	public static void isKing(int r1, int c1) {
		
		// if the checker piece is black
		if (checkerPieces[r1][c1].status == 'b') {
			
			// if it landed on the further most row
			if (checkerPieces[r1][c1].row == 7) {
				
				// change the status to king
				checkerPieces[r1][c1].status = 'k';
			}
		}
		
		// if the checker piece is red
		else if (checkerPieces[r1][c1].status == 'r') {
			
			// if it landed on the further most row
			if (checkerPieces[r1][c1].row == 0) {
				
				// change the statsu to king
				checkerPieces[r1][c1].status = 'q';
			}
		}
	}
	
	/**
	 * This method just counts how many red pieces are still on the board.
	 * @return int
	 */
	public static int redCount() {
		int count = 0;
		
		// nested for loop that looks to see which CheckerPieces have the red or red king status
		for (CheckerPiece[] ca : checkerPieces) {
			for (CheckerPiece c : ca) {
				if (c.status == 'r' || c.status == 'q') {
					count++;
				}
			}
		}
		return count;
	}
	
	/**
	 * This method just counts how many black pieces are still on the board.
	 * @return int
	 */
	public static int blackCount() {
		int count = 0;
		
		// nested for loop that looks to see which CheckerPieces have the black or black king status
		for (CheckerPiece[] ca : checkerPieces) {
			for (CheckerPiece c : ca) {
				if (c.status == 'b' || c.status == 'k') {
					count++;
				}
			}
		}
		return count;
	}
	
	/**
	 * This method determines that if after one of the pieces jumping, it has another opportunity to
	 * jump, which according to the rules is **mandatory**.
	 * @param r1
	 * @param c1
	 * @return boolean
	 */
	public static boolean canDoubleJump(int r1, int c1) {
		
		// returns all legalJumps again after the player jumped the first time
		String[] legalJumps = legalJumps();
		
		// creating a String that contains the location of the just moved CheckerPiece
		String r1String = Integer.toString(r1);
		String r2String = Integer.toString(c1);
		String moveString = r1String + r2String;
		
		// searches through all legal jumps to see if the legalJumps contain any moves where the piece being
		// moved is the piece that the player just moved
		for (String legalJump : legalJumps) {
			if (legalJump.substring(0, 2).equalsIgnoreCase(moveString.substring(0, 2))) {
				return true;
			}
		}
		return false;
	}
}
