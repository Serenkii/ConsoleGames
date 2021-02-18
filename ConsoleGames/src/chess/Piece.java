package chess;

public class Piece {
	String name;
	char symbol;
	int[] position;
	boolean alive;
	Color color;
	int value;
	int[][] moves;
	int steps;
	
	public Piece(Color color) {
		this.color = color;
		position = new int[2];
		if(this.color == Color.BLACK) {
			name = "black";
		}
		if(this.color == Color.WHITE) {
			name = "white";
		}
		alive = true;
	}
	
	/*
	 * public boolean move(char x, char y) {
	 * 
	 * }
	 */
	
	public void print() {
		System.out.print(symbol);
	}
	
	/**
	 * 
	 * @param x is a letter from a to h
	 * @param y is a number from 1 to 8
	 */
	public void setPosition(char x, char y) {
		position[0] = x - 97;		//https://de.wikipedia.org/wiki/American_Standard_Code_for_Information_Interchange
		position[1] = y - 49;
		System.out.println(name + " is now at	" + x + "" + y + " (" + position[0] + ", " + position[1] + ")");
	}
	
	public int getX() {
		return position[0];
	}
	public int getY() {
		return position[1];
	}
}
