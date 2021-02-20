package chess;

public class Queen extends Piece {

	public Queen(Color color, Position position) {
		super(color, position);
		setSymbol("q");		//♛  & ♕
		name = name + " queen";
		value = 9;
		moves = new int[8][2];
		steps = 8;
		generateMoves();
	}
	
	public void generateMoves() {
		//moves[moveNumber][move]
		moves[0][0] = 0;		//x
		moves[0][1] = 1;		//y
		
		moves[1][0] = 1;
		moves[1][1] = 1;
		
		moves[2][0] = 1;
		moves[2][1] = 0;
		
		moves[3][0] = 1;
		moves[3][1] = -1;
		
		moves[4][0] = 0;
		moves[4][1] = -1;
		
		moves[5][0] = -1;
		moves[5][1] = -1;
		
		moves[6][0] = -1;
		moves[6][1] = 0;
		
		moves[7][0] = -1;
		moves[7][1] = 1;
	}
}
