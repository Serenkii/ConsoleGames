package chess;

public class Chessboard {
	Piece[][] board;
	
	public Chessboard() {
		board = new Piece[8][8];
	}
	
	public void print() {
		System.out.println("  a b c d e f g h");
		for(int i = 0; i < board.length; i++) {
			System.out.print(i + 1);
			System.out.print(' ');
			for(int j = 0; j < board.length; j++) {
				board[i][j].print();
				System.out.print(' ');
			}
			System.out.print(i + 1);
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	public void defaultPosition() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(i % 2 == 0 && j % 2 == 0 || i % 2 != 0 && j % 2 != 0) {
					board[i][j] = new Empty(Color.WHITE);
					
				}
				else {
					board[i][j] = new Empty(Color.BLACK);
				}
			}
		}
		setPieces(Color.BLACK, 0, 1);
		setPieces(Color.WHITE, 7, 6);
	}
	
	private void setPieces(Color color, int lineRest, int linePawns) {
		board[lineRest][0] = new Rook(color);
		board[lineRest][1] = new Knight(color);
		board[lineRest][2] = new Bishop(color);
		board[lineRest][3] = new Queen(color);
		board[lineRest][4] = new King(color);
		board[lineRest][5] = new Bishop(color);
		board[lineRest][6] = new Knight(color);
		board[lineRest][7] = new Rook(color);
		for(int i = 0; i < board.length; i++) {
			board[linePawns][i] = new Pawn(color);
		}
	}
}
