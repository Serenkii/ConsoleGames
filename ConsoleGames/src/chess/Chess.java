package chess;

import java.util.Scanner;

public class Chess {
	Chessboard board;
	Scanner userInput;
	String userInputString;
	boolean validUserInput;
	
	 public Chess() {
		 board = new Chessboard();
		 userInput = new Scanner(System.in);
		 validUserInput = false;
		 setup();
		 play();
		 play();
		 play();
		 play();
	 }
	 
	 public void setup() {
		 board.createAllPieces();
		 board.fillBoard();
		 board.drawBoard();
	 }
	 
	 public void play() {
		 if(userInputFromConsole()) {		//true if the input kinda makes sense
			 char[] inputArray = userInputString.toCharArray();
			 int objectNumber = board.findPiece(inputArray[0], inputArray[1]);
			 if(objectNumber != -1) {	//true if the piece exists
				 System.out.println("A piece was found.");
				 board.pieces[objectNumber].move(inputArray[2], inputArray[3]);
			 }
			 else {
				 System.out.println("We couldn't find the piece you want to move. Please try again.");
			 }
		 } 
		 else {
			 System.out.println("Your input doesn't seem right. Maybe it's too long, too short or anything else, but please check it.");
		 }
		 board.fillBoard();
		 board.drawBoard();
	 }
	 
	 public boolean userInputFromConsole() {
		 userInputString = userInput.nextLine();
		 userInputString = userInputString.strip();
		 userInputString = userInputString.replace(" ", "");
		 if(userInputString.length() != 4) {
			 return false;
		 }
		 userInputString = userInputString.toLowerCase();
		 return true;
	 }
	
}


//https://en.wikipedia.org/wiki/Chess_symbols_in_Unicode ♔♚