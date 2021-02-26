package chess;

public class Chessboard {
	Piece[][] board;
	Piece[] pieces;

	public Chessboard() {
		board = new Piece[8][8];
		pieces = new Piece[32];
	}
	
	/**
	 * 
	 * @param position of the piece you want to search
	 * @return If there is a piece in pieces[] with the required position the index of such, otherwise -1.
	 */
	public int findPiece(Position position) {
//		System.out.println(position.toString());
		for (int i = 0; i < pieces.length; i++) {
//			System.out.println(i + " -> " +pieces[i].position.toString());
			if (position.equals(pieces[i].position)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * @param position of the piece you want to search
	 * @param color of the piece you want to search
	 * @return If there is a piece in pieces[] with the required position and color, the index of such. If the piece does not exist
	 * at all, -1 is returned. If the piece has the wrong color, -2.
	 */
	public int findPiece(Position position, Color color) {
		for (int i = 0; i < pieces.length; i++) {
			if (position.equals(pieces[i].position)) {
				if(pieces[i].color.equals(color)) {
					return i;
				}
				else {
					return -2;
				}
			}
		}
		return -1;
	}
	
	public int findLivePiece(Position position, Color color) {
		for (int i = 0; i < pieces.length; i++) {
			if (position.equals(pieces[i].position) && pieces[i].alive) {
				if(pieces[i].color.equals(color)) {
					return i;
				}
				else {
					return -2;
				}
			}
		}
		return -1;
	}

	
	public void drawBoard() {
		System.out.println("\n   a b c d e f g h\n");
		for(int y = 0; y < 8; y++) {
			System.out.print(y + 1 + "  ");
			for(int x = 0; x < 8; x++) {
				if(board[x][y] != null) {
					board[x][y].print();
				}
				else {
					System.out.print("/");
				}
				System.out.print(" ");
			}
			System.out.println(" " + (y + 1));
		}
		System.out.println();
		System.out.println("   a b c d e f g h\n");
	}
	
	public void drawEnhancedBoard() {
		System.out.println("\n    | a | b | c | d | e | f | g | h |");
		System.out.println(" ---+---+---+---+---+---+---+---+---+---");
		for(int y = 0; y < 8; y++) {
			System.out.print(" " + (y + 1) + "  | ");
			for(int x = 0; x < 8; x++) {
				if(board[x][y] != null) {
					board[x][y].print();
				}
				else {
					System.out.print(" ");
				}
				System.out.print(" | ");
			}
			System.out.println(" " + (y + 1));
			System.out.println(" ---+---+---+---+---+---+---+---+---+---");
		}
		System.out.println("    | a | b | c | d | e | f | g | h |\n");
	}
	
	public void fillBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++ ) {
				board[i][j] = null;
			}
		}
		for (int i = 0; i < pieces.length; i++) {
			if (pieces[i].alive) {
				board[pieces[i].getX()][pieces[i].getY()] = pieces[i];
			}
		}
	}
	
	public void createAllPieces() {
		for (int i = 0; i < 8; i++) {
			pieces[i + 24] = new Pawn(Color.BLACK, new Position(i, 6));
		}
		pieces[8] = new Rook(Color.BLACK, new Position(0, 7));
		pieces[9] = new Knight(Color.BLACK, new Position(1, 7));
		pieces[10] = new Bishop(Color.BLACK, new Position(2, 7));
		pieces[11] = new Queen(Color.BLACK, new Position(3, 7));		//new Position(3, 7)
		pieces[12] = new King(Color.BLACK, new Position(4, 7));
		pieces[13] = new Bishop(Color.BLACK, new Position(5, 7));
		pieces[14] = new Knight(Color.BLACK, new Position(6, 7));
		pieces[15] = new Rook(Color.BLACK, new Position(7, 7));
		
		
		for (int i = 0; i < 8; i++) {
			pieces[i + 16] = new Pawn(Color.WHITE, new Position(i, 1));
		}
		pieces[0] = new Rook(Color.WHITE, new Position(0, 0));
		pieces[1] = new Knight(Color.WHITE, new Position(1, 0));
		pieces[2] = new Bishop(Color.WHITE, new Position(2, 0));
		pieces[3] = new Queen(Color.WHITE, new Position(3, 0));			//new Position(3, 0)
		pieces[4] = new King(Color.WHITE, new Position(4, 0));
		pieces[5] = new Bishop(Color.WHITE, new Position(5, 0));
		pieces[6] = new Knight(Color.WHITE, new Position(6, 0));
		pieces[7] = new Rook(Color.WHITE, new Position(7, 0));
	}
	
	public void debugPiecePrint() {
		for (int i = 0; i < pieces.length; i++) {
			System.err.println(pieces[i].toString());
		}
	}
	
	/**
	 * @param columnX is a letter from a to h
	 * @param lineY is a number from 1 to 8
	 * @return The position of the Piece in piece[] if it was found. Else the output is -1.
	 * @deprecated
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
}
