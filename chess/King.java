package chess;

public class King extends Piece {

	public King(Color color) {
		this.color = color;
		if(this.color == Color.BLACK) {
			//symbol = '♚';
			symbol = 'K';
		}
		else if (this.color == Color.WHITE) {
			//symbol = '♔';
			symbol = 'k';
		}
	}
}
