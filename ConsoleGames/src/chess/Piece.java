package chess;

public class Piece {
	String name;
	char symbol;
	int[] position;
	boolean alive;
	Color color;
	int value;
	
	public Piece() {
		position = new int[2];
	}
	
	public void print() {
		System.out.print(symbol);
	}
	
	public void setPosition(char x, char y) {
		position[0] = x - 97;		//https://de.wikipedia.org/wiki/American_Standard_Code_for_Information_Interchange
		position[1] = y - 48;
	}
}
