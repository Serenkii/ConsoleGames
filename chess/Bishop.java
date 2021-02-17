package chess;

public class Bishop extends Piece {

	public Bishop(Color color) {
		this.color = color;
		if(this.color == Color.BLACK) {
			//symbol = '♝';	
			symbol = 'B';
		}
		else if (this.color == Color.WHITE) {
			//symbol = '♗';
			symbol = 'b';
		}
	}
}
