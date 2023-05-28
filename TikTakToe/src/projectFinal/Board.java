// Mason Gillespie
// CS 2336.006

/* Analysis: The Board class represents the collection of the 9 Boxes to create a TTT board. The object is
 * defined by an array of Boxes, a name, a maximum row number, and a maximum column number. */

/* Design: This class will hold the logic for instantiating the Boxes, checking if all of the boxes are filled with
 * marks, printing out rows, acessors and mutators for all of the data-fields, and changing the placeHolder in a box
 * when a player "makes a move". */

/* Testing: Testing included ensuring that when printing the rows that each board was printed correctly and that the
 * makeMove method placed the correct mark in the correct Box. */

package projectFinal;

// preprocessor directives
import projectFinal.Box;

class Board {
	
	// describing variables for a Board object
	private final int ROW_NUM;
	private final int COL_NUM;
	private Box boxes[];
	private String name;


	// default constructor constructs a 3X3 board
	public Board(){
		this(3, 3, "3x3 Board");
	}

	// overloaded constructor
	public Board(int rowSize, int colSize,String name){
		this.COL_NUM = colSize;
		this.ROW_NUM = rowSize;
		this.name = name;
		boxes = new Box[ROW_NUM*COL_NUM];
		init();
	}
	
	// name accessor
	public String getName() {
		return this.name;
	}
	
	// boxes accessor
	public Box[] getBoxes() {
		return boxes;
	}
	
	// column size accessor
	public int getColSize() {
		return this.COL_NUM;
	}

	// row size accessor
	public int getRowSize() {
		return this.ROW_NUM;
	}
		
	// mark accessor
	public String getMark(int row, int col) {
		return boxes[row * this.COL_NUM + col].getPlaceHolder();
	}
	
	// creates box objects and stores them in array
	public void init(){
		for(int i = 0; i< boxes.length; i++){
			Box b = new Box( i / ROW_NUM ,i % COL_NUM);
			boxes[i] =b;
		}
	}
	
	// prints what is inside each object in the array
	public void print(int row) {
		// determining which row to start on to print columns
		int startIndex = row * 3;
		
		// printing out a column
		System.out.print(boxes[startIndex].getPlaceHolder() + " " + boxes[++startIndex].getPlaceHolder() + " " + boxes[++startIndex].getPlaceHolder() + " ");
	}
	
	// isFull() checks to see if the board has any available places
	public boolean isFull() {
		for(Box b : boxes)
			if(b.isAvailable())
				return false;
		return true;
	}
	
	// isAvailable() calls box isAvailable() to check if particular box is available
	public boolean isAvailable(int move) {
		return boxes[move].isAvailable();
	}

	// moveMove changes the placeholder of the specified box to the players mark
	public boolean makeMove(String player, int row, int col) {
		return boxes[(row * COL_NUM) + col].setPlaceHolder(player);
	}
}
