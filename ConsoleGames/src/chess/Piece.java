package chess;

public abstract class Piece {
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
	

	public boolean capture() {
		if (this.alive) {
			this.alive = false;
			setPosition(new Position(-1, -1));
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * spaghetti carbonara
	 * TODO: Break up in smaller methods
	 * @param newPosition is the position where you want to move to
	 * @param pieceList an array with all pieces to check for collisions
	 * @return The index of the piece in array pieceList that is captured if one is captured ...<|>...
	 * -1 if the move was succesful without capturing a piece ...<|>...
	 * -2 if the move was not succesful
	 */
	public int move(Position newPosition, Piece[] pieceList) {
		//if the move is out of bound of the board, the move should be impossible
		if (move_enforceBoundries(newPosition) == false) {
			return -2;
		}
		
		boolean pieceInWay;
		//goes through every possible move
		for (int i = 0; i < moves.length; i++) {
			pieceInWay = false;
			
			//goes through all steps for each move
			for (int j = 1; j <= steps; j++) {
				
				//true if the configuration results in possible move to newPosition
				if (((moves[i][0] * j) + position.xPos) == newPosition.xPos && ((moves[i][1] * j) + position.yPos) == newPosition.yPos) {
					
					//If there is no piece in the way
					if (!pieceInWay) {
						
						//checks whether there is a piece at newPosition
						for (int id = 0; id < pieceList.length; id++) {
							if (new Position(((moves[i][0] * j) + position.xPos), ((moves[i][1] * j) + position.yPos)
																							).equals(pieceList[id].position)) {
								
								//if there is a piece at that position and has the same color as the moving piece, then:
								if (pieceList[id].color.equals(this.color)) {
									System.err.println("DEBUG: You can't capture your own pieces.");
									return -2;		//You can't capture your own pieces
								}
								else {
									System.err.println("DEBUG: A piece is being captured: id == " + id);
									setPosition(newPosition);
									return id;	//returns the position of the piece to capture
								}
							}
						}
						setPosition(newPosition);
						System.err.println("DEBUG: You moved succesfully.");
						return -1;	//return true --> -1 means that the move was succesful, but no piece was captured
					}
					else {
						System.err.println("DEBUG: There is a piece in the way.");
						return -2;		//There is a piece in the way
					}
				}
				
				for (int z = 0; z < pieceList.length; z++) {
					if (new Position(((moves[i][0] * j) + position.xPos), ((moves[i][1] * j) + position.yPos)
																					).equals(pieceList[z].position)) {
						pieceInWay = true;
						break;
					}
				}
			}
		}
		System.err.println("DEBUG: You can't move there.");
		return -2;		//There is no way to move there
	}
	
	 protected boolean move_enforceBoundries(Position newPosition) {
		if (newPosition.xPos > 7 || newPosition.xPos < 0 || newPosition.yPos > 7 || newPosition.yPos < 0) {
			return false;
		}
		return true;
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
	
	@Override
	public String toString() {
		return "Piece [name=" + name + ", position=" + position + ", alive=" + alive + "]";
	}
	
	public abstract void generateMoves();
	
	/*
	 * DEPRECATED MOVE-METHODS
	 */

	/**
	 * Looks for collision but can't give you information about the piece you want to capture
	 * @param newPosition the place the piece tries to move to
	 * @param pieceColor the color of the player who's turn it is | could be removed, instead use color of @this
	 * @param board 2-dimensional array representing all the pieces on the chessboard
	 * @return true if the piece could move there, false if it was not succesful
	 * @deprecated
	 * @since 2021.02.21
	 */
	public boolean move(Position newPosition, Color pieceColor, Piece[][] board) {
		//if the move is out of bound of the board, the move should be impossible
		if (newPosition.xPos > 7 || newPosition.xPos < 0 || newPosition.yPos > 7 || newPosition.yPos < 0) {
			return false;
		}
		for (int i = 0; i < moves.length; i++) {
			for (int j = 1; j <= steps; j++) {
				if (((moves[i][0] * j) + position.xPos) == newPosition.xPos && ((moves[i][1] * j) + position.yPos) == newPosition.yPos) {
					if (board[(moves[i][0] * j) + position.xPos][(moves[i][1] * j) + position.yPos] != null) {
						if (board[(moves[i][0] * j) + position.xPos][(moves[i][1] * j) + position.yPos].color == pieceColor) {
							return false;
						}
						else {
							//capture piece e.g. by returning the number of the piece and then setting alive to false
							System.err.println("I didn't implement capturing pieces yet... Sorry^^");
						}
					}
					setPosition(newPosition);
					return true;
				}
				
				//The move is out of bound in x-direction, so it can't check for a piece (out of bound exception)
				if ((moves[i][0] * j) + position.xPos < 8 && (moves[i][0] * j) + position.xPos >= 0) {
					//The move is out of bound in y-direction, so it can't check for a piece (out of bound exception)
					if ((moves[i][1] * j) + position.yPos < 8 && (moves[i][1] * j) + position.yPos >= 0) {
						//If there is a piece at this specific position, a piece is in the way so there is no point going 
						//further in that direction to test for that specific move.
						if (board[(moves[i][0] * j) + position.xPos][(moves[i][1] * j) + position.yPos] != null) {
							break;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * The method only checks if the general direction of the move is possible and then moves. Not whether there is a piece in the way, 
	 * whether it took a piece or anything else.
	 * @param newPosition is the position the piece tries to move to
	 * @return true true if the piece can move to that position, otherwise false
	 * @deprecated
	 * @since 2021.02.20
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
	
	/**
	 * The method only checks if the general direction of the move is possible and then move. Not whether there is a piece in the way, 
	 * whether it took a piece or anything else.
	 * @param x is a letter from a to h
	 * @param y is a number from 1 to 8
	 * @return true if the piece can move to that position, otherwise false
	 * @deprecated
	 * @since 2021.02.18
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
