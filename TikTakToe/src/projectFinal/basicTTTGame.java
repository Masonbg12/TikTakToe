// Mason Gillespie
// CS 2336.006

/* Analysis: basicTTTGame is for readability and seperation of methods for the entire game to be more organized. Every method
 * that is called from this class can be moved to the Board class, however this class was created for the isWinner method. The TTT
 * games logic for being won is held here. */

/* Design: All basic method such as isAvailable(), makeMove(), isFull(), and printRow(), will call the Board class to handle, while the
 * isWinner() will check to see if the TTT game has been won by using the helping methods checkRow(), checkCol(), checkDiaglRL(), checkDiaglLR(). */

/* Testing: Testing included ensuring that the makeMove positioning was translated into a column and row correctly and that the isWinner() correctly
 * identified when a Board has been won with the player marks. */

package projectFinal;

// preprocessor directives
import projectFinal.Board;
import projectFinal.Player;

public class basicTTTGame {
	    // describing variables for a TTTGame
		private Board board;
		private int row = 3;
		private int col = 3;
		private int scoreToWin = 3;
		
		// overloaded constructor that will initiate a completely random game of Tic-Tac-Toe
		public basicTTTGame() {
			board = new Board();
		}
		
		// makeMove() recieves move and translates the move position into row and column for Board makeMove
		public void makeMove(String player, int move) {
			int row = 0, col = 0;
			
			// move is in first row
			if (move >= 0 && move <= 2) {
				row = 0;
				col = move;
			}
			// move is in second row
			else if (move >= 3 && move <= 5) {
				row = 1;
				col = move - 3;
			}
			// move is in third row
			else if (move >= 6 && move <= 8) {
				row = 2;
				col = move - 6;
			}
			else
				System.out.println("invalid position");
			
			// calling Board makeMove()
			board.makeMove(player, row, col);
		}
		
		// calling Board isAvailable()
		public boolean isAvailable(int move) {
			return board.isAvailable(move);
		}
		
		// calling Board isFull()
		public boolean isFull() {
			return board.isFull();
		}
		
		// isWinner() calls all of the possible combinations a player can win in their row of three marks
		public boolean isWinner(Player player, int board) {
			if(checkRow(player) || checkCol(player) || checkDiaglLR(player) || checkDiaglRL(player)) {
				System.out.println(player.getName() + " wins on board " + board + "!");
				return true;
			}
			else 
				return false;
		}
		
		// checkRow() looks to see if any marks are three in any row
		private boolean checkRow(Player player) {
			int count = 0;
			
			// nested loop moves through each row
			for(int col = 0; col < this.col; col++) {
				for(int row = 0; row < this.row; row++) {
					if(board.getMark(row, col).equals(player.getMark()))
						count++;
				}
				// if count is three return true
				if (count == this.scoreToWin)
					return true;
				count = 0;
			}
			
			return false;
		}
		
		// checkRow() looks to see if any marks are three in any column
		private boolean checkCol(Player player) {
			int count = 0;
			
			// nested loop moves through each column
			for(int row = 0; row < this.row; row++) {
				for(int col = 0; col < this.col; col++) {
					if(board.getMark(row, col).equals(player.getMark()))
						count++;
				}
				// if count is 3 then return true
				if(count == this.scoreToWin)
					return true;
				count = 0;
			}
			
			return false;
		}
		
		// checkDiaglLR() checks the three spots from the top left to the bottom right
		private boolean checkDiaglLR(Player player) {
			int count = 0;
			
			// loop moves in a diagonal line moving downwards from left to right
			for(int x = 0; x < this.row; x++)
				if(board.getMark(x, x).equals(player.getMark()))
					count++;
			
			// comparing mark counts to the score to win
			if(count == this.scoreToWin)
				return true;
			
			return false;
		}
		
		// checkDiaglRL() check the three spots from the bottom left to the top right
		private boolean checkDiaglRL(Player player) {
			int count = 0;
			
			// loop moves in a diagonal line moving upwards from left to right
			for(int x = 0; x > 3; x++)
				if(board.getMark(x, this.col - x - 1).equals(player.getMark()))
					count++;
			
			// comparing mark counts to the score to win
			if(count == this.scoreToWin)
				return true;
			
			return false;
		}
		
		// calling Board print method
		public void printRow(int row) {
			board.print(row);
		}
}
