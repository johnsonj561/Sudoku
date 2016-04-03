import java.util.Random;

/**
 * Sudoku Cell objects will populate the 9x9 Sudoku Grid
 * Sudoku Cell objects will be used to simplify the generation of Sudoku puzzle boards
 * by providing access to the cell's value, row, column, quadrant, and alternative values available
 * @author Justin J KB Software Solutions
 *
 */
public class SudokuCell {

	/**
	 * Constructor that allows caller to assign initial value of cell
	 * @param row
	 * @param col
	 * @param value
	 */
	public SudokuCell(int row, int col, int value){
		this.row = row;
		this.col = col;
		this.value = value;
		//every cell begins with 9 available values (solutions)
		//this count is incremented/decremented as values become available/unavailable
		this.availableValueCount = 9;
		setQuadrant();
	}
	/**
	 * Constructor that assigns the value of cell to 0
	 * @param row
	 * @param col
	 */
	public SudokuCell(int row, int col){
		this.row = row;
		this.col = col;
		this.value = 0;	//value is initialized to 0 - 0 will represent cell who is not yet assigned a value
		this.availableValueCount = 9;
		setQuadrant();
	}
	
	/**
	 * Returns the row that cell lives in
	 * @return the row that cell lives in
	 */
	public int getRow() {
		return row;
	}
	/**
	 * Returns the column that cell lives in
	 * @return the column that cell lives in
	 */
	public int getCol() {
		return col;
	}
	/**
	 * Returns cell's current value
	 * @return
	 */
	public int getValue() {
		return value;
	}
	/**
	 * Assigns new value to cell
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * Set SudokuCell to a random value
	 * Skip all unavailable values to ensure cell is valid
	 * @param value
	 */
	public void setRandomValue(){
		Random ran = new Random();
		int n = ran.nextInt(100) + 1;
		int i;
		int k = -1;				//initialized to -1 so first iteration will have k = 0
		for(i = 0; i < n; i++){
			k++;
			//if we've reached end of array, reset to 0
			if(k == 9){		
				k = 0;
			}
			//skip over all of the unavailable values
			while(availableValues[k] == 0){
				k = (k == 8)? 0 : k + 1;	//increment i to skip over unavailable values, reset to 0 if needed
			}
			//k is now assigned an available value
		}
		setValue(k+1);			//add 1 because we're working with arrays base 0
		availableValues[k] = 0;
		availableValueCount--;
	}
	
	/**
	 * Get the quadrant that cell lives in
	 * @return Quadrant that cell lives in
	 */
	public int getQuadrant() {
		return quadrant;
	}
	/**
	 * Determine the cell's quadrant based off of it's row and value 
	 */
	public void setQuadrant() {
		if(row < 3 && col < 3){
			this.quadrant = 0;
		}
		else if(row < 3 && col < 6){
			this.quadrant = 1;
		}
		else if(row < 3 && col < 9){
			this.quadrant = 2;
		}
		else if( row < 6 && col < 3){
			this.quadrant = 3;
		}
		else if(row < 6 && col < 6){
			this.quadrant = 4;
		}
		else if(row < 6 && col < 9){
			this.quadrant = 5;
		}
		else if(row < 9 && col < 3){
			this.quadrant = 6;
		}
		else if(row < 9 && col < 6){
			this.quadrant = 7;
		}
		else if(row < 9 && col < 9){
			this.quadrant = 8;
		}
		else{
			System.out.println("ERROR SETTING QUADRANT");
			this.quadrant = -1;
		}
	}
	
	/**
	 * Returns array of cell's available values (potential solutions)
	 * @return
	 */
	public int[] getAvailableValues() {
		return availableValues;
	}
	
	/**
	 * Mark the given index as an available value
	 * @param i
	 */
	public void setAvailableValue(int i) {
		if(availableValues[i] == 0){
			availableValueCount++;
			availableValues[i] = 1;
		}
	}
	
	/**
	 * Mark the given index as an unavailable value
	 * @param i
	 */
	public void setUnavailableValue(int i){
		if(availableValues[i] == 1){
			availableValueCount--;
			availableValues[i] = 0;
		}
	}
	
	/**
	 * Returns the total number of available values (solutions) that exist for current cell
	 * @return
	 */
	public int getAvailableValueCount(){
		return availableValueCount;
	}
	
	/**
	 * Tests the array of availableValues and returns true if there are no available values for current cell
	 * @return true if there are no available values to assign to current cell
	 */
	public boolean isBrokenCell(){
		for(int i = 0; i < availableValues.length; i++){
			if(availableValues[i] == 1){
				return false;
			}
		}
		return true;
	}
	
	private int availableValueCount;
	private int row;
	private int col;
	private int value;
	private int quadrant;
	//available values for a cell is initialized to all 1s, to be updated by grid class
	private int[] availableValues = {1, 1, 1, 1, 1, 1, 1, 1, 1};
}
