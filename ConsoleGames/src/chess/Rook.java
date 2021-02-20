package chess;

public class Rook extends Piece {

	public Rook(Color color, Position position) {
		super(color, position);
		setSymbol("r");		//♜  & ♖
		name = name + " rook";
		value = 5;
		moves = new int[4][2];
		steps = 8;
		generateMoves();
	}
	
	public void generateMoves() {
		//moves[moveNumber][move]
		moves[0][0] = 0;		//x
		moves[0][1] = 1;		//y
		
		moves[1][0] = 1;
		moves[1][1] = 0;
		
		moves[2][0] = -1;
		moves[2][1] = 0;
		
		moves[3][0] = 0;
		moves[3][1] = -1;
	}
}
