package chess;

import java.util.Scanner;

public class Chess {
	Chessboard board;
	Scanner userInput;
	String userInputString;
	boolean validUserInput;
	Color turn;
	
	 public Chess() {
		 board = new Chessboard();
		 userInput = new Scanner(System.in);
		 validUserInput = false;
		 setup();
		 //playLoop();
	 }
	 
	 public void setup() {
		 board.createAllPieces();
		 board.fillBoard();
		 //board.drawEnhancedBoard();
		 turn = Color.WHITE;
	 }
	 
	 /**
	  * A loop containing the game. It returns 0 when the game is ended by someone typing "stop". It returns 1 if WHITE won
	  * and 2 if BLACK won.
	  * @return interruption: 0; WHITE-win: 1; BLACK-win: 2
	  */
	 public int playLoop() {
		 while (true) {
			 play();
			 if (userInputString.equals("stop")) {
				 return 0;
			 }
			 for (int i = 0; i < board.pieces.length; i++) {
				 if (board.pieces[i] instanceof King && board.pieces[i].alive == false) {
					if (board.pieces[i].color == Color.WHITE) {
						 return 1;
					 }
					 else if (board.pieces[i].color == Color.BLACK) {
						 return 2;
					 }
					else {
						 System.err.println("A king somehow is captured and has no color.");
						 return -1;
					}	 
				 }
			 }
		 }
	 }
	 
	 public void play() {
		 //board.debugPiecePrint();
		 board.drawEnhancedBoard();
		 if(userInputFromConsole()) {		//true if the input kinda makes sense
			 if (userInputString.equals("stop")) {
				 return;
			 }
			 char[] inputArray = userInputString.toCharArray();
			 Position oldPosition = new Position(inputArray[0], inputArray[1]);
			 Position newPosition = new Position(inputArray[2], inputArray[3]);
			 int objectNumber = board.findLivePiece(oldPosition, turn);
			 
			 if(objectNumber >= 0 && objectNumber < 32) {	//true if the piece exists
				 //System.out.println("A piece was found.");
				 int status = board.pieces[objectNumber].move(newPosition, board.pieces);
				 if (status >= -1 && status < 32) {
					 if (status != -1) {
						 //System.err.println("DEBUG: Method capture() is used now.");
						 board.pieces[status].capture();
					 }
					 changeTurn();
					 //System.out.println("\nIt's your turn now, " + turn + ".");
				 }
				 else if (status == -2) {
					 System.out.println("The move is invalid.");
				 }
				 else {
					 System.err.println("The status ∉ [-2; 31]");
				 } 
			 }
			 
			 else if (objectNumber == -1) {
				 System.out.println("We couldn't find the piece you want to move. Please try again.");
			 }
			 else if (objectNumber == -2) {
				 System.out.println("You can't move your enemy's pieces!");
			 }
			 else {
				 System.err.println("The objectNumber has an impossible value. -> " + objectNumber);
			 }
		 } 
		 
		 else {
			 System.out.println("Your input doesn't seem right. Maybe it's too long, too short or anything else, but please check it.");
		 }
		 board.fillBoard();
		 //board.drawEnhancedBoard();
	 }
	 
	 public boolean userInputFromConsole() {
		 System.out.print("Your move, " + turn + " -> ");
		 userInputString = userInput.nextLine();
		 userInputString = userInputString.strip();
		 userInputString = userInputString.replace(" ", "");
		 if(userInputString.length() != 4) {
			 return false;
		 }
		 userInputString = userInputString.toLowerCase();
		 return true;
	 }
	
	 public void changeTurn() {
		 if (turn == Color.WHITE) {
			 turn = Color.BLACK;
		 }
		 else if (turn == Color.BLACK) {
			 turn = Color.WHITE;
		 }
		 else {
			 System.err.println("Class variable \"turn\" (ENUM: Color) has no value.");
		 }
	 }
}


//https://en.wikipedia.org/wiki/Chess_symbols_in_Unicode ♔♚