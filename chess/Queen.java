package chess;

public class Queen extends Piece {

	public Queen(Color color) {
		this.color = color;
		if(this.color == Color.BLACK) {
			//symbol = '♛';
			symbol = 'Q';
		}
		else if (this.color == Color.WHITE) {
			//symbol = '♕';	
			symbol = 'q';
		}
	}
}
