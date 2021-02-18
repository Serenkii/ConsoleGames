package chess;

public class Pawn extends Piece {

	public Pawn(Color color) {
		super(color);
		if(this.color == Color.BLACK) {
			//symbol = '\u265F';
			symbol = 'P';
		}
		else if (this.color == Color.WHITE) {
			//symbol = '♙';	
			symbol = 'p';
		}
		name = name + " pawn";
		value = 1;
	}
}
