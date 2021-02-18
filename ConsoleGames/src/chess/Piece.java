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
	
	
	public boolean move(char x, char y) {
		for (int i = 0; i < moves.length; i++) {
			System.out.println("Move " + i);
			for (int j = 1; j <= steps; j++) {
				System.out.println("	Step " + j);
				
				System.out.println("((moves[" + i + "][0]) + j) + position[0]) == ('" + x + "' - 97) && moves[" + i + "][1] * j + position[1] == ('" + y + "' - 49)");
				System.out.println("((" + moves[i][0] + " * " + j + ") + " + position[0] + " == (" + (x - 97) + ") && ((" + moves[i][1] + " * " + j + ") + " + position[1] + ") == (" + (y - 49) + ")");
				System.out.println(((moves[i][0] * j) + position[0]) + " == " + (x - 97) + " && " + ((moves[i][1] * j) + position[1]) + " == " + (y - 49));
				System.out.println();
				
				if (((moves[i][0] * j) + position[0]) == (x - 97) && ((moves[i][1] * j) + position[1]) == (y - 49)) {
					System.out.println("The move is possible.");
					setPosition(x, y);
					System.out.println("The position was changed.");
					return true;
				}
			}
		}
		return false;
	}
	 
	
	public void print() {
		System.out.print(symbol);
	}
	
	/**
	 * @param x is a letter from a to h
	 * @param y is a number from 1 to 8
	 */
	public void setPosition(char x, char y) {
		position[0] = x - 97;		//https://de.wikipedia.org/wiki/American_Standard_Code_for_Information_Interchange
		position[1] = y - 49;
		System.out.println(name + " is now at	" + x + "" + y + " (" + position[0] + ", " + position[1] + ")");
	}
	
	/**
	 * @return Returns position[0] aka the X-coordinate of the piece 
	 */
	public int getX() {
		return position[0];
	}
	/**
	 * @return Returns position[1] aka the Y-coordinate of the piece
	 */
	public int getY() {
		return position[1];
	}
}
