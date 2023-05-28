// Mason Gillespie
// CS 2336.006

/* Analysis: The Box object is needed to define each box on the basicTTTGame's board. The Box class will hold the
 * logic for setting a placeHolder, checking to see if the Box is available to hold a mark, and printing out the box. */

/* Design: The Box will use the DASH object to mark if a Box does not contain a  mark on the board and will have acessors
 * and mutators for the placeholder along with a print method. The isAvailable() method will simply check to see if the placeHolder
 * contains a DASH for vacancy. */

/* Testing: Testing was simply making sure the print method was compatible with the print methods in other classes. */

package projectFinal;

public class Box{
	// arguments or instance variables
	private static final Object DASH = "-";
	private int row;
	private int col;
	private String placeholder = "-";

	// overloaded constructor
	public Box(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	// placeholder accessor
	public String getPlaceHolder() {
		return this.placeholder;
	}
	
	// placeholder mutator
	public boolean setPlaceHolder(String placeholder) {
		if(isAvailable()) {
			this.placeholder = placeholder;
			return true;
		}
		else
			return false;
	}
	
	// print method prints contents of placeholder in the current Box
	public void printHolder(int Maxcol){
		System.out.print(placeholder);
		
		// if the column being printed is at the maximum column of the board print new line
		if (col == Maxcol - 1)
			System.out.println();
	}
	
	// isAvailable() checks to see if the current placeholder is available for a mark to be placed
	public boolean isAvailable() {
		return this.placeholder.equals(Box.DASH);
	}

}
