import java.util.*;

class reversi {
	
	// Piece enumerated types with names 
	// represented by their "on screen" characters
	public enum Piece {
		EMPTY(" "), WHITE("O"), BLACK("@");
		private Piece(String name) {
			this.name = name;
		}
		// toString override
		private final String name;
		public String toString() {
			return name;
		}
		// 
		public Piece getOpposite() {
			if (this == Piece.WHITE) {
				return Piece.BLACK;
			} else if (this == Piece.BLACK) {
				return Piece.WHITE;
			} else {
				return Piece.EMPTY;
			}
		}
		//
		public Piece getColor() {
			if (this == Piece.WHITE) {
				return Piece.WHITE;
			} else if (this == Piece.BLACK) {
				return Piece.BLACK;
			} else {
				return Piece.EMPTY;
			}
		}
	}
	
	// Piece objects
	private final static Piece white = Piece.WHITE;
	private final static Piece black = Piece.BLACK;
	private final static Piece empty = Piece.EMPTY;
	private final static Piece player1 = Piece.WHITE;
	private final static Piece player2 = Piece.BLACK;
	
	// game board 
	public static Piece[][] board = new Piece[8][8]; // 2d array of pieces

	// input scanner
	public static Scanner scanner = new Scanner(System.in); 
	
	// other global variables
	private static boolean gameOver = false; // true when game is quit
	
	// alphabetical char array
	private final static char[] alpha = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
	
	// functions
	public static void initializeBoard() {
		// all empty...
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				board[i][j] = empty;
			}
		}
		
		// except for center four squares
		board[2][5] = black;
		board[3][4] = black;
		board[4][3] = black;
		board[5][2] = white;
		
		
	}
	
	public static void printBoard() {
		// clear screen
		for(int i=0; i<2; i++) {
			System.out.println("");
		}
		
		// display current state of board
		System.out.println("  a b c d e f g h ");
		System.out.println(" +-+-+-+-+-+-+-+-+");
		for(int i=0; i<8; i++) {
			System.out.print((i+1) + "|");
			System.out.print(board[0][i]);
			System.out.print('|');
			System.out.print(board[1][i]);
			System.out.print('|');
			System.out.print(board[2][i]);
			System.out.print('|');
			System.out.print(board[3][i]);
			System.out.print('|');
			System.out.print(board[4][i]);
			System.out.print('|');
			System.out.print(board[5][i]);
			System.out.print('|');
			System.out.print(board[6][i]);
			System.out.print('|');
			System.out.print(board[7][i]);
			System.out.println('|');
			System.out.println(" +-+-+-+-+-+-+-+-+");
		}
	}
	
	public static void getInput() {
		String input;
		int row, column;
		
		input = scanner.next();
		
		if (input.equalsIgnoreCase("quit")) {
			gameOver = true;
			return;
		}
		
		if (input.length() != 2) {
			System.out.println("Invalid input: " + input);
			return;
		}
		
		// interpret entry into row and column
		switch (input.charAt(0)) {
			case 'a':
				column = 0;
				break;
			case 'b':
				column = 1;
				break;
			case 'c':
				column = 2;
				break;
			case 'd':
				column = 3;
				break;
			case 'e':
				column = 4;
				break;
			case 'f':
				column = 5;
				break;
			case 'g':
				column = 6;
				break;
			case 'h':
				column = 7;
				break;
			default:
				System.out.println("Invalid Column");
				return;
		}
		switch (input.charAt(1)) {
			case '1':
				row = 0;
				break;
			case '2':
				row = 1;
				break;
			case '3':
				row = 2;
				break;
			case '4':
				row = 3;
				break;
			case '5':
				row = 4;
				break;
			case '6':
				row = 5;
				break;
			case '7':
				row = 6;
				break;
			case '8':
				row = 7;
				break;
			default:
				System.out.println("Invalid Row");
				return;
		}
		
		// perform move
		move(column, row);
		System.out.println("Column: " + column + "; Row: " + row);
	}
	
	public static void move(int column, int row) {
		// if valid move...
		if (isValidMove(column, row)) {
			board[column][row] = white; // TODO: player
		} else {
			System.out.println("Try again");
			//maybe also print a reason why
		}
		
		// print updated board;
		printBoard();
	}
	
	public static boolean checkNeighbors(int column, int row) {		
		int i = 1;
		Piece p = Piece.WHITE; // TODO: color of current player
		boolean anyValid = false;
		
		if (isInBounds(column, row-i)) { // North Side
			if(board[column][row-i] == p.getOpposite()) {
				System.out.println("North Side");
				i++; // neighbor is opposite
				while(isInBounds(column, row-i)) {
					if (board[column][row-i] == p.getOpposite()) {
						i++;
					} else if (board[column][row-i] == p.getColor()) {
						flipPieces(column,column,row,row-i);
						anyValid = true;
						break;
					} else { break; }
				}
			}
		}
		if (isInBounds(column+i, row)) { // East Side
			if(board[column+i][row] == p.getOpposite()) {
				System.out.println("East Side");
				i++;
				while(isInBounds(column+i, row)) {
					if (board[column+i][row] == p.getOpposite()) {
						i++;
					} else if (board[column+i][row] == p.getColor()) {
						flipPieces(column,column+i,row,row);
						anyValid = true;
						break;
					} else { break; }
				}
			}
		}
		if (isInBounds(column, row+i)) { // South Side
			if(board[column][row+i] == p.getOpposite()) {
				System.out.println("South Side");
				i++;
				while(isInBounds(column, row+i)) {
					if (board[column][row+i] == p.getOpposite()) {
						i++;
					} else if (board[column][row+i] == p.getColor()) {
						flipPieces(column,column,row,row+i);
						anyValid = true;
						break;
					} else { break; }
				}
			}
		}
		if (isInBounds(column-i, row)) { // West Side
			if(board[column-i][row] == p.getOpposite()) {
				System.out.println("West Side");
				i++;
				while(isInBounds(column-i, row)) {
					if (board[column-i][row] == p.getOpposite()) {
						i++;
					} else if (board[column-i][row] == p.getColor()) {
						flipPieces(column,column-i,row,row);
						anyValid = true;
						break;
					} else { break; }
				}
			}
		}
		return anyValid;
	}
	
	public static boolean checkDiagonals(int column, int row) {
		int i = 1;
		Piece p = Piece.WHITE; // TODO: color of current player
		boolean anyValid = false;
		if (isInBounds(column+i, row-i)) { // Northeast Side
			if(board[column+i][row-i] == p.getOpposite()) {
				System.out.println("Northeast");
				i++; // neighbor is opposite
				while(isInBounds(column+i, row-i)) {
					if (board[column+i][row-i] == p.getOpposite()) {
						i++;
					} else if (board[column+i][row-i] == p.getColor()) {
						flipPieces(column,column+i,row,row-i);
						anyValid = true;
						break;
					} else { break; }
				}
			}
		}
		if (isInBounds(column+i, row+i)) { // Southeast Side
			if(board[column+i][row+i] == p.getOpposite()) {
				System.out.println("Southeast");
				i++; // neighbor is opposite
				while(isInBounds(column+i, row+i)) {
					if (board[column+i][row+i] == p.getOpposite()) {
						i++;
					} else if (board[column+i][row+i] == p.getColor()) {
						flipPieces(column,column+i,row,row+i);
						anyValid = true;
						break;
					} else { break; }
				}
			}
		}
		if (isInBounds(column-i, row+i)) { // Southwest Side
			if(board[column-i][row+i] == p.getOpposite()) {
				System.out.println("Southwest");
				i++; // neighbor is opposite
				while(isInBounds(column-i, row+i)) {
					if (board[column+i][row-i] == p.getOpposite()) {
						i++;
					} else if (board[column+i][row-i] == p.getColor()) {
						flipPieces(column,column+i,row,row-i);
						anyValid = true;
						break;
					} else { break; }
				}
			}
		}
		if (isInBounds(column-i, row-i)) { // Northwest Side
			if(board[column-i][row-i] == p.getOpposite()) {
				System.out.println("Northwest");
				i++; // neighbor is opposite
				while(isInBounds(column-i, row-i)) {
					if (board[column-i][row-i] == p.getOpposite()) {
						i++;
					} else if (board[column-i][row-i] == p.getColor()) {
						flipPieces(column,column-i,row,row+i);
						anyValid = true;
						break;
					} else { break; }
				}
			}
		}
		return anyValid;
	}
	
	public static boolean isValidMove(int column, int row) { 
		if (board[column][row] != Piece.EMPTY) {
			System.out.println("Space (" + alpha[column] + "," + (row+1) + ") is not empty");
			return false;
		} else { return checkNeighbors(column, row) || checkDiagonals(column, row); }
	}
	
	public static boolean isInBounds(int column, int row) {
		if (column > 7 ||
			row > 7 ||
			column < 0 ||
			row < 0 ) {
				return false;
		} else { 
			return true; 
		}
	}		
	
	public static void flipPieces(int startCol, int endCol, int startRow, int endRow) {
		int temp;
		if (startCol == endCol) {
			if (startRow > endRow) {
				temp = startRow;
				startRow = endRow;
				endRow = temp;
			}
			System.out.println(":" + (startRow+1) + " -> " + endRow);
			for(int i=(startRow+1); i<endRow; i++) {
				board[startCol][i] = board[startCol][i].getOpposite();
			}
		} else if (startRow == endRow) {
			if (startCol > endCol) {
				temp = startCol;
				startCol = endCol;
				endCol = temp;
			}
			for(int i=(startCol+1); i<endCol; i++) {
				board[i][startRow] = board[i][startRow].getOpposite();
			}
		} else {
			if (startRow > endRow) {
				temp = startRow;
				startRow = endRow;
				endRow = temp;
			}if (startCol > endCol) {
				temp = startCol;
				startCol = endCol;
				endCol = temp;
			}
			
			System.out.println("StartCol: " + startCol);
			System.out.println("endCol: " + endCol);
			System.out.println("startRow: " + startRow);
			System.out.println("endRowl: " + endRow);
			
			int j=(startRow+1);
			for(int i=(startCol+1); i<endCol; i++, j++) {
				board[i][j] = board[i][j].getOpposite();
				j++;
			}
		}
	}
	
	
	public static void main(String args[]) {
		initializeBoard();
		System.out.println("Board initialized. Player1 is White");
		printBoard();
		while (!gameOver) {
			// loop until quit
			getInput();
		}
		System.out.println("Goodbye!");
	}
}
