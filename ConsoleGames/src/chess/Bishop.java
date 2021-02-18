package chess;

public class Bishop extends Piece {

	public Bishop(Color color) {
		super(color);
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
	}
}
