package chess;

public class Chessboard {
	Piece[][] board;
	Piece[] pieces;
	
	public Chessboard() {
		board = new Piece[8][8];
		pieces = new Piece[32];
	}
	
	/**
	 * @param columnX is a letter from a to h
	 * @param lineY is a number from 1 to 8
	 * @return The position of the Piece in piece[] if it was found. Else the output is -1.
	 */
	public int findPiece(char columnX, char lineY) {
		int x = columnX - 97;
		//System.out.println("x is now: " + x + " because columnX is " + columnX);
		int y = lineY - 49;
		//System.out.println("y is now: " + y + " because lineY is " + lineY);
		for (int i = 0; i < pieces.length; i++) {
			if(pieces[i].alive && pieces[i].getX() == x && pieces[i].getY() == y) {
				//System.out.println(i + " findPiece - found one");
				return i; 	//There is a piece at this position
			}
			//System.out.println(i + " findPiece - none found");
		}
		//System.out.println("findPiece - none found");
		return -1;	//There is no piece at this position
	}
	
	public void drawBoard() {
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				if(board[x][y] != null) {
					board[x][y].print();
				}
				else {
					System.out.print("/");
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public void fillBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++ ) {
				board[i][j] = null;
			}
		}
		for (int i = 0; i < pieces.length; i++) {
			board[pieces[i].getX()][pieces[i].getY()] = pieces[i];
		}
	}
	
	public void createAllPieces() {
		createPieces(Color.BLACK, 0); 
		createPieces(Color.WHITE, 16);
		for(int i = 0; i < 8; i++) {	//Black pieces
			pieces[i].setPosition((char) ('a' + i), '1');
		}
		for(int i = 0; i < 8; i++) {	//Black pawns
			pieces[i + 8].setPosition((char) ('a' + i), '2');
		}
		for(int i = 0; i < 8; i++) {	//White pieces
			pieces[i + 16].setPosition((char) ('a' + i), '8');
		}
		for(int i = 0; i < 8; i++) {	//White pawns
			pieces[i + 24].setPosition((char) ('a' + i), '7');
		}
	}
	
	private void createPieces(Color color, int start) {
		pieces[start + 0] = new Rook(color);
		pieces[start + 1] = new Knight(color);
		pieces[start + 2] = new Bishop(color);
		pieces[start + 3] = new Queen(color);
		pieces[start + 4] = new King(color);
		pieces[start + 5] = new Bishop(color);
		pieces[start + 6] = new Knight(color);
		pieces[start + 7] = new Rook(color);
		for(int i = 0; i < 8; i++) {
			pieces[start + 8 + i] = new Pawn(color);
		}
	}
}
