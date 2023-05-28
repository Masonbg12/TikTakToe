// Mason Gillespie
// CS 2336.006

/* Analysis: The ultimate TTT game needed a player object to define each player for printing out messages when a board or
 * the game has been won, to notify each player when their turn is up, and to place marks on the board. */

/* Design: The Player object is simply defined as a name and a mark (X or O). */

/* Testing: Testing only included making sure the correct input was stored in the objects. */

package projectFinal;

public class Player {
	
	// describing variables for a Player object
	private String name;
	private String mark;
	
	// overloaded constructor
	public Player(String name, String marks) {
		this.name = name;
		this.mark = marks;
	}
	
	// name accessor
	public String getName() {
		return name;
	}
	
	// name mutator
	public void setName(String name) {
		this.name = name;
	}
	
	// mark accessor
	public String getMark() {
		return mark;
	}
	
	// mark mutator
	public void setMark(String mark) {
		this.mark = mark;
	}
}
