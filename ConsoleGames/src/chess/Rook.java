package chess;

public class Rook extends Piece {

	public Rook(Color color) {
		super(color);
		if(this.color == Color.BLACK) {
			//symbol = '♜';	
			symbol = 'R';
		}
		else if (this.color == Color.WHITE) {
			//symbol = '♖';		
			symbol = 'r';
		}
		name = name + " rook";
		value = 5;
	}
}
