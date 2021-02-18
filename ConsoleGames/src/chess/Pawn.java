package chess;

public class Pawn extends Piece {

	public Pawn(Color color) {
		super(color);
		name = name + " pawn";
		value = 1;
		moves = new int[1][2];
		steps = 1;
		if(this.color == Color.BLACK) {
			//symbol = '\u265F';
			symbol = 'P';
			moves[0][0] = 0;
			moves[0][1] = 1;
		}
		else if (this.color == Color.WHITE) {
			//symbol = '♙';	
			symbol = 'p';
			moves[0][0] = 0;
			moves[0][1] = -1;
		}
		
	}
}
