package chess;

public class Bishop extends Piece {

	public Bishop(Color color, Position position) {
		super(color, position);
		setSymbol("b");		//♝  & ♗
		if(this.color == Color.BLACK) {
			//symbol = '♝';	
			symbol = 'B';
		}
		else if (this.color == Color.WHITE) {
			//symbol = '♗';
			symbol = 'b';
		}
		name = name + " bishop";
		value = 3;
		moves = new int[4][2];
		steps = 8;
		generateMoves();
	}
	
	public void generateMoves() {
		//moves[moveNumber][move]
		moves[0][0] = 1;		//x
		moves[0][1] = 1;		//y
		
		moves[1][0] = 1;
		moves[1][1] = -1;
		
		moves[2][0] = -1;
		moves[2][1] = 1;
		
		moves[3][0] = -1;
		moves[3][1] = -1;
	}
}
