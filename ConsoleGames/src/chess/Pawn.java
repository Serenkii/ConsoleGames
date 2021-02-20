package chess;

public class Pawn extends Piece {

	public Pawn(Color color, Position position) {
		super(color, position);
		setSymbol("p");		//'\u265F' & ♙
		name = name + " pawn";
		value = 1;
		moves = new int[1][2];
		steps = 1;
		if(this.color == Color.BLACK) {
			moves[0][0] = 0;
			moves[0][1] = 1;
		}
		else if (this.color == Color.WHITE) {
			moves[0][0] = 0;
			moves[0][1] = -1;
		}
		else {
			System.err.println("Somehow the Pawn has no color.");
		}
		
	}
}
