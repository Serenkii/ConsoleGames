package chess;

public class Piece {
	String name;
	char symbol;
	Position position;
	boolean alive;
	Color color;
	int value;
	int[][] moves;
	int steps;
	
	public Piece(Color color, Position position) {
		this.color = color;
		this.position = position;
		if(this.color == Color.BLACK) {
			name = "black";
		}
		if(this.color == Color.WHITE) {
			name = "white";
		}
		alive = true;
	}
	
	
	/**
	 * @param newPosition is the position the piece tries to move to
	 * @return true true if the piece can move to that position, otherwise false
	 */
	public boolean move(Position newPosition) {
		for (int i = 0; i < moves.length; i++) {
			for (int j = 1; j <= steps; j++) {
				if (((moves[i][0] * j) + position.xPos) == newPosition.xPos && ((moves[i][1] * j) + position.yPos) == newPosition.yPos) {
					setPosition(newPosition);
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
		position.setPosition(x, y);
		System.out.println(name + " is now at	" + position.characterX + "" + position.characterX 
																				+ " (" + position.xPos + ", " + position.yPos + ")");
	}
	
	
	public void setPosition(Position newPosition) {
		this.position = newPosition;
	}
	
	
	public void setSymbol(String symbolString) {
		if(this.color == Color.BLACK) {
			this.symbol = symbolString.toUpperCase().charAt(0);
		}
		if(this.color == Color.WHITE) {
			this.symbol = symbolString.toLowerCase().charAt(0);
		}
	}

	
	/**
	 * @return Returns the X-coordinate of the piece as integer
	 */
	public int getX() {
		return position.xPos;
	}
	
	/**
	 * @return Returns the Y-coordinate of the piece as integer
	 */
	public int getY() {
		return position.yPos;
	}
	
	
	/**
	 * The method only checks if the general direction of the move is possible and then move. Not whether there is a piece in the way, 
	 * whether it took a piece or anything else.
	 * @param x is a letter from a to h
	 * @param y is a number from 1 to 8
	 * @return true if the piece can move to that position, otherwise false
	 * @deprecated
	 */
	public boolean move(char x, char y) {
		for (int i = 0; i < moves.length; i++) {
			System.out.println("Move " + i);
			for (int j = 1; j <= steps; j++) {
				System.out.println("	Step " + j);
				
//				System.out.println("((moves[" + i + "][0]) + j) + position[0]) == ('" + x + "' - 97) && moves[" + i + "][1] * j + position[1] == ('" + y + "' - 49)");
//				System.out.println("((" + moves[i][0] + " * " + j + ") + " + position[0] + " == (" + (x - 97) + ") && ((" + moves[i][1] + " * " + j + ") + " + position[1] + ") == (" + (y - 49) + ")");
//				System.out.println(((moves[i][0] * j) + position[0]) + " == " + (x - 97) + " && " + ((moves[i][1] * j) + position[1]) + " == " + (y - 49));
//				System.out.println();
				
				if (((moves[i][0] * j) + position.xPos) == (x - 97) && ((moves[i][1] * j) + position.yPos) == (y - 49)) {
					System.out.println("The move is possible.");
					setPosition(x, y);
					System.out.println("The position was changed.");
					return true;
				}
			}
		}
		return false;
	}
}
