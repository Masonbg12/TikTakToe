// Mason Gillespie
// CS 2336.006

/* Analysis: The Ultimate Tic-Tac-Toe game involves 9 games of Tic-Tac-Toe being played on a larger Tic-Tac-Toe board. The
 * UltTTTGame needs to hold the ultimate rules on the big Tic-Tac-Toe board and should be the main driver for the entire game. */

/* Design: The UltTTTGame has 9 objects of basic TTT games, the total number of players, and which TTT games have been won. The class
 * will also hold the logic for allowing each player to make a move, printing out the whole board, showing all of the possible boards
 * and moves the current player can make, and when the game is over by the board being full or when a player has won. */

/* Testing: Testing was a very long process due to every input having to be read from the user one at a time. However the testing
 * mostly covered each edge cases such as being able to play anywhere on the board if the previous move is sent to a full board, when
 * the user inputs any invalid input, when a player wins the game, and when the game is brought to a cat (tie). */

package projectFinal;

// preprocessor directives
import java.util.Scanner;
import projectFinal.Player;
import projectFinal.basicTTTGame;

public class UltTTTGame {
	// describing attributes of a UltTTTGame
	private static basicTTTGame[] games = new basicTTTGame[9];
	private static Player[] players = new Player[2];
	private static char[] gamesWon = new char[9];
	
	// main driver
	public static void main(String[] args) {
		init();
		beginGame();
	}
	
	// init() intitializes the basicTTTGame array and the new players
	private static void init() {
		// basicTTTGame array
		for(int i = 0; i < 9; i++)
			games[i] = new basicTTTGame();
		
		// new players
		players[0] = new Player("Player1", "X");
		players[1] = new Player("Player2", "O");
	}
	
	// beginGame starts the ultimate TTT game
	private static void beginGame() {
		// declaration section
		int currPlayer = 1;
		int previousMove = -1;
		int numOfPlayers = 0;
		boolean playingAI = false;
		Scanner s = new Scanner(System.in);
		
		// prompting user for number of players for possible computer generated AI as player
		System.out.print("How many players (1 or 2)? ");
		numOfPlayers = s.nextInt();
		
		// player vs. AI
		if (numOfPlayers == 1)
			playingAI = true;
		
		// printing board
		printGame();
		
		// the game will continue until gameOver returns true
		while(!gameOver(players[currPlayer])) {
			
			// swapping players
			if (currPlayer == 0)
				currPlayer = 1;
			else
				currPlayer = 0;
			
			// calling makeMove() and storing the move into previousMove for next iteration
			previousMove = makeMove(players[currPlayer], previousMove, playingAI);
			
			// printing board
			printGame();
		}
	}
	
	// printGame() prints the ultimate TTT board
	private static void printGame() {
		int count = 0;
		
		// top row
		System.out.println("#####################");
		
		// nested for loops will print out the entire board row by row (meaning acessing each row
		// from the boards rather than printing out a whole basicTTTGame at a time)
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				games[i * 3 + j].printRow(count);
				
				// printing out two middle lines for seperation between boards
				if ((i * 3 + j) % 3 != 2)
					System.out.print("# ");
			}
			// increasing count for board access
			count++;
			
			if (count < 3)
				i--;
			else {
				// printing specifications
				count = 0;
				System.out.println();
				System.out.print("#####################");
			}
			System.out.println();
		}
	}
	
	// makeMove() will allow the user or the AI to make a selected move
	private static int makeMove(Player player, int previousMove, boolean playingAI) {
		// declaration section
		int board = -1;
		int move = -1;
		Scanner s = new Scanner(System.in);
		
		// announcing player's turn
		System.out.println("It is " + player.getName() + "'s turn!");
		
		// assuring that the board moved to is not full
		if (previousMove == -1 || games[previousMove].isFull())
			previousMove = -1;
		
		// if it is the AIs turn
		if (playingAI && player.getMark() == "O") {
			// random board will be selected if AI has choice
			if (previousMove == -1)
				do {
				board = (int) (Math.random() * 9);
				} while (games[board].isFull());
			else // else the board is the previousMove
				board = previousMove;
				
			// random move is produced
			do {
			move = (int) (Math.random() * 9);
			} while (!games[board].isAvailable(move));
				
			// printing out AI's choice for user readability
			System.out.println("AI chose board " + board + " and positon " + move + ".");
		}
		else {	// it is Users turn
			// calling showPossibleBoards() then prompting user for board selection if there is 2 or more choices
			// to choose from
			showPossibleBoards(previousMove);
			if (previousMove == -1) {
				do {
					System.out.print("board (0 - 8): ");
					board = s.nextInt();
				} while(!(board >= 0 && board <= 8 && !games[board].isFull()));
			}
			else	// there is only one board to play on so baord is chosen for user
				board = previousMove;
			
			// call showPossibleMoves() and prompt user for position on board while making sure the move isAvailable()
			showPossibleMoves(board);
			while (!(move >= 0 && move <= 8 && games[board].isAvailable(move))) {
				System.out.print("Position (0 - 8): ");
				move = s.nextInt();
			}
		}
		
		// calling makeMove()
		games[board].makeMove(player.getMark(), move);
		
		// testing to see if the board just played on has a winner
		if (gamesWon[board] == '\u0000' && games[board].isWinner(player, board))
			gamesWon[board] = player.getMark().charAt(0);
		
		// returning move
		return move;
	}
	
	// showPossibleBoards() prints out all the available boards the user can choose from
	private static void showPossibleBoards(int previousMove) {
		System.out.print("You can play on board(s): ");
		
		// there are multiple boards
		if (previousMove == -1) {
			for(int i = 0; i < games.length; i++)
				if (!games[i].isFull())
					System.out.print(i + " ");
		}
		else	// there is only one board based on previousMove
			System.out.print(previousMove);
		
		System.out.println();
		
	}
	
	// showPossibleMoves() prints out all the possible moves the user can choose from
	private static void showPossibleMoves(int board) {
		System.out.print("In board " + board + " you can play on spot: ");
		
		// checks all of the Boxes on the board to see if any are available
		for(int i = 0; i < 9; i++)
			if (games[board].isAvailable(i))
				System.out.print(i + " ");
		
		System.out.println();
	}
	
	// gameOver() checks to see if the ultimate TTT game is over
	private static boolean gameOver(Player player) {
		// if a player wins the whole game a message is printed out
		if (playerWins()) {
			System.out.print(player.getName() + " wins the game!");
			return true;
		}
		
		// if the whole board is full a message is printed out
		if (ultGameIsFull()) {
			System.out.println("It's a cat!");
			return true;
		}
		// continue the game
		return false;
	}
	
	// playerWins() checks to see if any player has won any combination on the UltTTTGame board
	private static boolean playerWins() {
		// winningMark holds possible winning mark
		char winningMark;
		
		// checking top left to bottom right diagonal
		winningMark = gamesWon[0];
		if (winningMark != '\u0000' && winningMark == gamesWon[4] && winningMark == gamesWon[8])
			return true;
		
		// checking top right to bottom left diagonal
		winningMark = gamesWon[2];
		if (winningMark != '\u0000' && winningMark == gamesWon[4] && winningMark == gamesWon[6])
			return true;
		
		// checking top row
		winningMark = gamesWon[0];
		if (winningMark != '\u0000' && winningMark == gamesWon[1] && winningMark == gamesWon[2])
			return true;
		
		// checking middle row
		winningMark = gamesWon[3];
		if (winningMark != '\u0000' && winningMark == gamesWon[4] && winningMark == gamesWon[5])
			return true;
		
		// checking bottom row
		winningMark = gamesWon[6];
		if (winningMark != '\u0000' && winningMark == gamesWon[7] && winningMark == gamesWon[8])
			return true;
		
		// checking left column
		winningMark = gamesWon[0];
		if (winningMark != '\u0000' && winningMark == gamesWon[3] && winningMark == gamesWon[6])
			return true;
		
		// checking middle column
		winningMark = gamesWon[1];
		if (winningMark != '\u0000' && winningMark == gamesWon[4] && winningMark == gamesWon[7])
			return true;
		
		// checking right column
		winningMark = gamesWon[2];
		if (winningMark != '\u0000' && winningMark == gamesWon[5] && winningMark == gamesWon[8])
			return true;
		
		return false;
	}
	
	// ultGameIsFull() checks to see if all of the basicTTTGame boards are full
	private static boolean ultGameIsFull() {
		for(basicTTTGame t : games)
			if (!t.isFull())
				return false;
		
		return true;
	}
}
