import java.util.Random;
import java.util.Scanner;

/**
 * @author Serenki 
 * @version 2020/12/13 (first made 2020/08/17)
 */

public class RockPaperScissors {
	Random rnd;
	Scanner userScan;
	int wins;
	int defeats;
	int draws;
	
	public RockPaperScissors() {
		rnd = new Random();
		userScan = new Scanner(System.in);
		reset();
	}
	
	public void reset() {
		wins = 0;
		defeats = 0;
		draws = 0;
	}
	
	public void play() {
		String userInput;
		while(true) {
			userInput = userInput();
			
			if (userInput.equals("stop")) {
				boolean b = endGame();
				if (b) {
					break;
				}
			}
			if (!userInput.equals("stop")) {
				determineWinner(userInput);
			}
			System.out.println();
		}
		
	}
	
	
	public void determineWinner(String userInput) {
		String bot = "rock";
		int botChoice = rnd.nextInt(3);
		if (botChoice == 0) {
			bot = "paper";
		}
		else if (botChoice == 1) {
			bot = "scissors";
		}
		
		if (bot.equals(userInput)) {
			System.out.println("It's a draw. Both of you picked " + bot + ".");
			draws++;
		}
		else if (bot.equals("rock") && userInput.equals("scissors") || bot.equals("paper") && userInput.equals("rock") || bot.equals("scissors") && userInput.equals("paper")) {
			System.out.println("You chose " + userInput + ", your enemy " + bot + ". As " + bot + " beats " + userInput + ", you lost.");
			defeats++;
		}
		else {
			System.out.println("You chose " + userInput + ", your enemy " + bot + ". As " + userInput + " beats " + bot + ", you won.");
			wins++;
		}
		score();
	}
	
	
	public String userInput() {
		String userInput = "";
		while (true) {
			System.out.println("Rock, paper or scissors? (To end the game, type \"stop\")");
			userInput = userScan.nextLine();
			userInput = userInput.toLowerCase().strip();
			if (userInput.equals("rock") || userInput.equals("paper") || userInput.equals("scissors") || userInput.equals("r") || userInput.equals("p") || userInput.equals("s") || userInput.equals("scissor")) {
				if (userInput.equals("r")) {
					userInput = "rock";
				}
				else if (userInput.equals("p")) {
					userInput = "paper";
				}
				else if (userInput.equals("s") || userInput.equals("scissor")) {
					userInput = "scissors";
				}
				
				break;
			}
			else if (userInput.equals("help")) {
				help();
			}
			else if (userInput.equals("score")) {
				score();
			}
			else if (userInput.equals("stop")) {
				break;
			}
			else {
				System.out.println("\"" + userInput + "\"" + " is no valid input. Use \"rock\", \"paper\" or \"scissors\". (If you need help, type \"help\")");
			}
		}
		return userInput;
	}
	
	
	public boolean endGame() {
		System.out.println("Are you sure, you want to stop playing Rock, Paper, Scissors? Type \"Yes\" to stop. Otherwise we'll continue. ;)");
		String userInput = userScan.nextLine();
		userInput = userInput.toLowerCase().strip();
		if (!userInput.equals("yes")) {
			return false;
		}
		System.out.println("Do you want to keep the score while ConsoleGames is still running?");
		userInput = userScan.nextLine();
		userInput = userInput.toLowerCase().strip();
		if (!userInput.equals("yes")) {
			System.out.println("OK, the score is being reset.");
			reset();
		}
		else {
			System.out.println("OK, the score will be saved.");
		}
		return true;
	}
	
	public void help() {
		System.out.println("Here are the commands you can use:");
		System.out.println("You can use either \"rock\", \"paper\" or \"scissors\" to pick the tool of your choice. Only \"r\", \"p\" or \"s\" is also fine.");
		System.out.println("You can also user \"help\", which will make this appear. By typing \"stop\" you can end the rock, paper, scissors game.");
		System.out.println("The command \"score\" gives you the current score of the game.");
		System.out.println();
		System.out.println("This game works like this: You choose a tool and the enemy chooses a random one aswell. Then it compares.");
		System.out.println("Rock beats scissors, scissors beat paper and paper beats rock. That's all. Not that complicated.");
		System.out.println("");
	}
	
	public void score() {
		System.out.println("Your score: " + wins + "x won, " + defeats + "x lost, " + draws + "x draw");
	}
	
}
