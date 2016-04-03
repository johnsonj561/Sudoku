
/**
 * Utility functions to display and format SudokuBoard objects
 * @author Justin J KB Software Solutions
 *
 */
public class SudokuUtils {
	
	/**
	 * Print the passed Sudoku Board to console for display
	 * @param SudokuGrid to be printed to console
	 */
	public void printSudokuBoard(SudokuBoard board) {
		SudokuCell[][] mSudokuBoard = board.getSudokuBoard();
		System.out.println("\n\n-------------------------------------------------------------------------");
		for (int i = 0; i < COL_LENGTH; i++) {
			for (int j = 0; j < ROW_LENGTH; j++) {
				if(mSudokuBoard[i][j].getValue() == 0){
					System.out.print("|       ");
				}
				else{
					System.out.print("|   " + mSudokuBoard[i][j].getValue() + "   ");
				}
			}
			System.out.println("|\n-------------------------------------------------------------------------");
		}
	}
	
	/**
	 * Prints all available solutions (values) for every individual cell
	 */
	public void printAvailableValues(SudokuBoard board){
		SudokuCell[][] mSudokuBoard = board.getSudokuBoard();
		for(int i = 0; i < COL_LENGTH; i++){
			for(int j = 0; j < ROW_LENGTH; j++){
				//get the array of availableValues for current cell
				System.out.println("Cell " + i + " | " + j);
				int a[] = mSudokuBoard[i][j].getAvailableValues();
				for(int k = 0; k < a.length; k++){
					if(a[k] == 1){
						System.out.print(k+1 + "\t");
					}
				}
				System.out.println();
			}
		}
	}

	/**
	 * Traverses SudokuBoard and updates every cell's array of availableValues[][]
	 * @param board Sudoku Board to be updated
	 */
	public void updateBoardsAvailableValues(SudokuBoard board){
		SudokuCell[][] mSudokuBoard = board.getSudokuBoard();
		//first reset all availableValues[i] to 1 (available)
		for(int i = 0; i < COL_LENGTH; i++){
			for(int j = 0; j < ROW_LENGTH; j++){
				for(int k = 0; k < ROW_LENGTH; k++){
					mSudokuBoard[i][j].setAvailableValue(k);
				}
			}
		}
		//for every cell
		for(int i = 0; i < COL_LENGTH; i++){
			for(int j = 0; j < ROW_LENGTH; j++){
				int mRow = i;
				int mCol = j;
				int mQuadrant = mSudokuBoard[i][j].getQuadrant();
				int mValue = mSudokuBoard[i][j].getValue();
				//mark the unavailable values in the cells that fall into current row, column, or quadrant
				if(mValue != 0){
					for(int m = 0; m < COL_LENGTH; m++){
						for(int n = 0; n < ROW_LENGTH; n++){
							//assign the current cell a 1 for available value, because it will be a possible
							//solution later when solving the puzzle
							if (m == mRow && n == mCol) {
								mSudokuBoard[m][n].setAvailableValue(mValue - 1);
							} else {
								//mark all cells as available, unless below criteria are met
								// if in same row as cell being validated, check for duplicate values
								if (m == mRow) {
									mSudokuBoard[m][n].setUnavailableValue(mValue - 1);
								}
								// if in same column as cell being validated, check for duplicate values
								else if (n == mCol) {
									mSudokuBoard[m][n].setUnavailableValue(mValue - 1);
								}
								// if in same quadrant as cell being validated, check for duplicates
								else if (mSudokuBoard[m][n].getQuadrant() == mQuadrant) {
									mSudokuBoard[m][n].setUnavailableValue(mValue - 1);
								}
							}
						}
					}
				}
			}
		}
	}
	
	
	/**
	 * Update the availableValues[] for an individual cell
	 * @param cell SudokuCell to be updated
	 */
	public void updateCellsAvailableValues(SudokuCell[][] board, SudokuCell cell){
		//first reset all availableValues[i] to 1 (available)
		for(int i = 0; i < ROW_LENGTH; i++){
			cell.setAvailableValue(i);
		}
		int mRow = cell.getRow();
		int mCol = cell.getCol();
		int mQuadrant = cell.getQuadrant();
		//next traverse all cells and update current cells available values
		for(int i = 0; i < COL_LENGTH; i++){
			for(int j = 0; j < ROW_LENGTH; j++){
				//skip current cell
				if (i == mRow && j == mCol) {
					continue;
				} else {
					// if in same row, flag unavailableValues
					if (i == mRow) {
						cell.setUnavailableValue(board[i][j].getValue() - 1);
					}
					// if in same column as cell being validated, check for duplicate values
					else if (j == mCol) {
						cell.setUnavailableValue(board[i][j].getValue() - 1);
					}
					// if in same quadrant as cell being validated, check for duplicates
					else if (board[i][j].getQuadrant() == mQuadrant) {
						cell.setUnavailableValue(board[i][j].getValue() - 1);
					}
				}
			}
		}
		System.out.println("Cells availableValueCount is: " + cell.getAvailableValueCount());
	}
	
	
	
	/**
	 * Generates a comma separated list of Sudoku Board values
	 * @param board Sudoku Board being converted to CSV
	 * @return CSV - comma separated list of Sudoku Board Values
	 */
	public String generateSudokuBoardCVS(SudokuBoard board){
		SudokuCell[][] mSudokuBoard = board.getSudokuBoard();
		String csv = "";
		for(int i = 0; i < COL_LENGTH; i++){
			for(int j = 0; j < ROW_LENGTH; j++){
				if(i == COL_LENGTH-1 && j == ROW_LENGTH-1){
					csv += mSudokuBoard[i][j].getValue();
				}
				else{
					csv += mSudokuBoard[i][j].getValue() + ",";
				}
			}
		}
		return csv;
	}
	
	
	private static final int ROW_LENGTH = 9;
	private static final int COL_LENGTH = 9;
	
	
}
