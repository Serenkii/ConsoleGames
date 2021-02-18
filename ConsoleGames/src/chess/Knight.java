package chess;

public class Knight extends Piece {

	public Knight(Color color) {
		super(color);
		if(this.color == Color.BLACK) {
			//symbol = '♞';
			symbol = 'N';
		}
		else if (this.color == Color.WHITE) {
			//symbol = '♘';	
			symbol = 'n';
		}
		name = name + " knight";
		value = 3;
	}
}
