package chess;

public class Knight extends Piece {

	public Knight(Color color) {
		this.color = color;
		if(this.color == Color.BLACK) {
			//symbol = '♞';
			symbol = 'N';
		}
		else if (this.color == Color.WHITE) {
			//symbol = '♘';	
			symbol = 'n';
		}
	}
}
