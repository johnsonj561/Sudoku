public class SudokuBoard {

	/**
	 * Default constructor that builds a Sudoku Board
	 */
	public SudokuBoard(){
		mSudokuBoard = generateSudokuBoard();
	}


	/**
	 * Print the passed Sudoku board to console for display
	 * @param SudokuGrid to be printed to console
	 */
	public static void printSudokuGrid(SudokuCell SudokuGrid[][]) {
		for (int i = 0; i < COL_LENGTH; i++) {
			for (int j = 0; j < ROW_LENGTH; j++) {
				System.out.print("|\t" + SudokuGrid[i][j].getValue() + "\t");
			}
			System.out.println("|\n");
		}
	}

	/**
	 * Generates a comma separated list of Sudoku Grid values
	 * @param grid Sudoku Grid that is being formated
	 * @return Comma separated list of Sudoku Grid Values
	 */
	public static String generateSudokuBoardCVS(SudokuCell[][] grid){
		String csv = "";
		for(int i = 0; i < COL_LENGTH; i++){
			for(int j = 0; j < ROW_LENGTH; j++){
				if(i == COL_LENGTH-1 && j == ROW_LENGTH-1){
					csv += grid[i][j].getValue();
				}
				else{
					csv += grid[i][j].getValue() + ",";
				}

			}
		}
		return csv;
	}

	/**
	 * Generate a random, valid Sudoku Board as a 2 dimensional array
	 * @return 2 dimensional array of Sudoku Cells that represent a valid Sudoku Board
	 */
	private static SudokuCell[][] generateSudokuBoard(){
		boolean isValidGrid = false;
		int attempts = 0;
		while(!isValidGrid){
			System.out.println("Attempt #: " + attempts++);
			isValidGrid = true;
			SudokuCell[][] SudokuGrid = initializeSudokuGrid();
			for (int i = 0; i < COL_LENGTH && isValidGrid; i++) {
				for (int j = 0; j < ROW_LENGTH && isValidGrid; j++) {
					do {
						//first check that cell is valid - if there are no available values to insert then
						//assign cell's value to -1
						if(SudokuGrid[i][j].isBrokenCell()){
							SudokuGrid[i][j].setValue(-1);;
							isValidGrid = false;
						}
						//else assign a random number from the array of availableValues to cell's value
						else{
							SudokuGrid[i][j].setRandomValue();
						}
					} while (!validateCell(SudokuGrid, SudokuGrid[i][j]));
				}
			}
			if(isValidGrid){
				System.out.println("Sudoku Board Generated!");
				return SudokuGrid;
			}
		}
		return null;
	}

	/**
	 * Initialize the 9 x 9 Sudoku board with all 0s 0 represents a cell that is not yet set
	 * @return Sudoku Grid that contains all 0s
	 */
	private static SudokuCell[][] initializeSudokuGrid() {
		// declare grid that is 9 by 9
		SudokuCell[][] SudokuGrid = new SudokuCell[COL_LENGTH][ROW_LENGTH];
		for (int i = 0; i < COL_LENGTH; i++) {
			for (int j = 0; j < ROW_LENGTH; j++) {
				SudokuGrid[i][j] = new SudokuCell(i, j);
			}
		}
		return SudokuGrid;
	}

	/**
	 * Check that the given cell does not violate any Sudoku rules
	 * @param row of current cell
	 * @param col of current cell
	 * @return false if cell violates Sudoku rules
	 */
	private static boolean validateCell(SudokuCell[][] grid, SudokuCell cell) {
		int mRow = cell.getRow();
		int mCol = cell.getCol();
		int mQuadrant = cell.getQuadrant();
		int mValue = cell.getValue();
		// traverse grid and look for duplicate values in the same row, column, or quadrant
		for (int i = 0; i < COL_LENGTH; i++) {
			for (int j = 0; j < ROW_LENGTH; j++) {
				// skip over our current cell!
				if (i == mRow && j == mCol) {
					//System.out.println("grid position " + i + " | " + j + " is being skipped");
					continue;
				} else {
					// if in same row as cell being validated, check for duplicate values
					if (i == mRow && grid[i][j].getValue() == mValue) {
						return false;
					}
					// if in same column as cell being validated, check for duplicate values
					if (j == mCol && grid[i][j].getValue() == mValue) {
						return false;
					}
					// if in same quadrant as cell being validated, check for duplicates
					if (grid[i][j].getQuadrant() == mQuadrant
							& grid[i][j].getValue() == mValue) {
						return false;
					}
				}
			}
		}
		return true;
	}


	private static final int ROW_LENGTH = 9;
	private static final int COL_LENGTH = 9;
	SudokuCell[][] mSudokuBoard;
}
