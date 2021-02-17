package chess;

public class Empty extends Piece {
	
	public Empty(Color color) {
		this.color = color;
		if(this.color == Color.BLACK) {
			symbol = '■';		//U+25A0 ■ https://en.wikipedia.org/wiki/List_of_Unicode_characters
			//symbol = '∎';
		}
		else if (this.color == Color.WHITE) {
			//symbol = '□';		//U+25A1 □ 
			symbol = '⬜';		//U+2B1C
			//symbol = '∏';
		}
	}
	
}
