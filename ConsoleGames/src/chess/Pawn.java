package chess;

public class Pawn extends Piece {
	int[][] captureMoves;
	public Pawn(Color color, Position position) {
		super(color, position);
		setSymbol("p");		//'\u265F' & ♙
		name = name + " pawn";
		value = 1;
		moves = new int[1][2];
		captureMoves = new int[2][2];
		steps = 2;
		generateMoves();
		
	}

	@Override
	public int move(Position newPosition, Piece[] pieceList) {
		if (super.move_enforceBoundries(newPosition) == false) {
			return -2;
		}
		boolean pieceInWay;
		
		//goes through every possible move
		for (int i = 0; i < moves.length; i++) {
			pieceInWay = false;
			
			//goes through all steps for each move
			for (int j = 1; j <= steps; j++) {
				
				for (int z = 0; z < pieceList.length; z++) {
					if (new Position(((moves[i][0] * j) + position.xPos), ((moves[i][1] * j) + position.yPos)
																				).equals(pieceList[z].position)) {
						pieceInWay = true;
						break;
					}
				}
				if (pieceInWay) {
					break;
				}
				if (new Position((moves[i][0] * j) + position.xPos, (moves[i][1] * j) + position.yPos).equals(newPosition)) {
					setPosition(newPosition);
					updateSteps();
					//System.err.println("DEBUG: You can move there.");
					return -1;
				}
			}
		}
		
		//System.err.println("DEBUG: Now " + this.name + " will try for capturing-moves");
		
		for (int i = 0; i < captureMoves.length; i++) {
//			System.err.println("DEBUG: i == " + i);
//			System.err.println("captureMoves[i][0] == " + captureMoves[i][0] + "; position.xPos ==" + position.xPos);
//			System.err.println("captureMoves[i][1] == " + captureMoves[i][1] + "; position.yPos ==" + position.yPos);
//			System.err.println("test: " + new Position(captureMoves[i][0] + position.xPos, captureMoves[i][1] + position.yPos));
//			System.err.println("goal: " + newPosition);
//			System.err.println(new Position(captureMoves[i][0] + position.xPos, captureMoves[i][1] + position.yPos).equals(newPosition));
			if (new Position((int) captureMoves[i][0] + position.xPos, (int) captureMoves[i][1] + position.yPos).equals(newPosition)) {
				//System.err.println("DEBUG: By capturing a piece this " + this.name + " is allowed to move there.");
				for (int id = 0; id < pieceList.length; id++) {
					if (new Position(captureMoves[i][0] + position.xPos, captureMoves[i][1] + position.yPos
																				).equals(pieceList[id].position)) {
						if (pieceList[id].color != this.color) {
							setPosition(newPosition);
							updateSteps();
							//System.err.println("DEBUG: A piece is being captured: id == " + id);
							return id;
						}
					}
				}
			}
		}
		//System.err.println("DEBUG: That move was not possible with " + this.name);
		return -2;
	}
	
	private void updateSteps() {
		this.steps = 1;
	}
	
	public void generateMoves() {
		if(this.color == Color.BLACK) {
			moves[0][0] = 0;
			moves[0][1] = 1;
			
			captureMoves[0][0] = -1;
			captureMoves[0][1] = 1;
			
			captureMoves[1][0] = 1;
			captureMoves[1][1] = 1;
		}
		else if (this.color == Color.WHITE) {
			moves[0][0] = 0;
			moves[0][1] = -1;
			
			captureMoves[0][0] = -1;
			captureMoves[0][1] = -1;
			
			captureMoves[1][0] = 1;
			captureMoves[1][1] = -1;
		}
		else {
			System.err.println("Somehow the Pawn has no color.");
		}
	}
}
//TODO: remember: super.method() works, so if you break up move(...) into smaller methods, these could be used here