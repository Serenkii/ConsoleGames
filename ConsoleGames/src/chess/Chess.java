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
		 while(true) {
			 play();
		 }
	 }
	 
	 public void setup() {
		 board.createAllPieces();
		 board.fillBoard();
		 board.drawBoard();
		 turn = Color.WHITE;
	 }
	 
	 public void play() {
		 if(userInputFromConsole()) {		//true if the input kinda makes sense
			 char[] inputArray = userInputString.toCharArray();
			 Position oldPosition = new Position(inputArray[0], inputArray[1]);
			 Position newPosition = new Position(inputArray[2], inputArray[3]);
			 int objectNumber = board.findPiece(oldPosition, turn);
			 
			 if(objectNumber >= 0 && objectNumber < 32) {	//true if the piece exists
				 //System.out.println("A piece was found.");
				 boolean succesful = board.pieces[objectNumber].move(newPosition);
				 if (succesful) {
					 changeTurn();
					 System.out.println("\nIt's your turn now, " + turn + ".");
				 }
				 else {
					 System.out.println("The move is invalid.");
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
		 board.drawBoard();
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