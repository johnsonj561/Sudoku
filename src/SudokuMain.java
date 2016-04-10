import java.util.Random;


public class SudokuMain {

	public static void main(String[] args) {
		//create a sudoku board and print to console
		SudokuBoard mSudokuBoard = new SudokuBoard();
		SudokuCell[][] mBoard = mSudokuBoard.getSudokuBoard();
		SudokuUtils mUtils = new SudokuUtils();
		System.out.println("\nNew Sudoku Board Initialized\n");
		//mUtils.printSudokuBoard(mSudokuBoard);
		Random ran = new Random();
		System.out.println("\nREMOVING CELLS FROM GRID RANDOMLY TO CREATE NEW PUZZLE\n");
		
		boolean puzzleComplete = false;
		int blanks = 0;
		//while puzzle is not complete
		while(!puzzleComplete){
			for(int i = 0; i < 200000 && blanks < 50; i++){
				int randomRow = ran.nextInt(9);
				int randomColumn = ran.nextInt(9);
				//if cell is not already blank and if it has only 1 possible solution - then set it to 0 (blank)
				if(mBoard[randomRow][randomColumn].getValue() != 0 && mBoard[randomRow][randomColumn].getAvailableValueCount() == 1){
					mBoard[randomRow][randomColumn].setValue(0);
					System.out.println("Removing value from cell " + randomRow + " | " + randomColumn + " with availableValueCount = " + 
							mBoard[randomRow][randomColumn].getAvailableValueCount());
					blanks++;
				}
				mUtils.updateBoardsAvailableValues(mSudokuBoard);
				mUtils.printAvailableValues(mSudokuBoard);
				mUtils.printSudokuBoard(mSudokuBoard);
			}		
			System.out.println("Blanks created: " + blanks);
			if(blanks > 45){
				System.out.println("SUCCESS - New Board Generated!");
				puzzleComplete = true;
			}
			//else we need to start over, board failed!
			else{
				System.out.println("Board Failed - Starting over with new board now...");
				mSudokuBoard = new SudokuBoard();
				mBoard = mSudokuBoard.getSudokuBoard();
				System.out.println("\nNew Sudoku Board Initialized\n");
		//		mUtils.printSudokuBoard(mSudokuBoard);;
				System.out.println("\nREMOVING CELLS FROM GRID RANDOMLY TO CREATE NEW PUZZLE\n");
				blanks = 0;	//reset blanks to 0
			}
		}
		
		mUtils.printAvailableValues(mSudokuBoard);
		mUtils.printSudokuBoard(mSudokuBoard);
		

	}

}
