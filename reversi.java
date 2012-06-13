import java.util.*;

class reversi {
	
	// Piece enumerated types with names 
	// represented by their "on screen" characters
	public enum Piece {
		EMPTY(" "), WHITE("O"), BLACK("@");
		private Piece(String name) {
			this.name = name;
		}
		private final String name;
		public String toString() {
			return name;
		}
	}
	
	// Piece objects
	private final static Piece white = Piece.WHITE;
	private final static Piece black = Piece.BLACK;
	private final static Piece empty = Piece.EMPTY;
	
	// game board 
	public static Piece[][] board = new Piece[8][8]; // 2d array of pieces

	// input scanner
	public static Scanner scanner = new Scanner(System.in); 
	
	// other global variables
	private static boolean gameOver = false; // true when game is quit
	
	
	// functions
	public static void initializeBoard() {
		// all empty...
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				board[i][j] = empty;
			}
		}
		
		// except for center four squares
		board[3][3] = white;
		board[3][4] = black;
		board[4][3] = black;
		board[4][4] = white;
	}
	
	public static void printBoard() {
		// clear screen
		for(int i=0; i<50; i++) {
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
		// if isValidMove(column, row) {
			board[column][row] = white;
		// } else {
			// System.out.println("Invalid Move");
			// maybe also print a reason why
		// }
		
		// print updated board;
		printBoard();
	}
	
	public static void main(String args[]) {
		initializeBoard();
		System.out.println("Board initialized.");
		printBoard();
		while (!gameOver) {
			// loop until quit
			getInput();
		}
		System.out.println("Goodbye!");
	}
}

// * end comment * =D
// * test * //