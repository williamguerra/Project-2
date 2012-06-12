import java.util.*;

class reversi {
	
	// comment
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
	
	private final static Piece white = Piece.WHITE;
	private final static Piece black = Piece.BLACK;
	private final static Piece empty = Piece.EMPTY;
	
	public static Piece[][] board = new Piece[8][8];
	
	public static Scanner scanner = new Scanner(System.in);
	
	public static void initializeBoard() {
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				board[i][j] = empty;
			}
		}
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
		
		if (input.length() != 2) {
			System.out.println("Invalid input: " + input);
			return;
		}
		
		
	}
	
	public static void main(String args[]) {
		initializeBoard();
		System.out.println("Board initialized.");
		printBoard();
		getInput();
	}
}