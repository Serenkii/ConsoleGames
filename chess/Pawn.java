package chess;

public class Pawn extends Piece {

	public Pawn(Color color) {
		this.color = color;
		if(this.color == Color.BLACK) {
			//symbol = '\u265F';
			symbol = 'P';
		}
		else if (this.color == Color.WHITE) {
			//symbol = '♙';	
			symbol = 'p';
		}
	}
}
