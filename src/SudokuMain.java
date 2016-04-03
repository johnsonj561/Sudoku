import java.util.Random;


public class SudokuMain {

	public static void main(String[] args) {
		//create a sudoku board and print to console
		SudokuBoard mSudokuBoard = new SudokuBoard();
		SudokuCell[][] mBoard = mSudokuBoard.getSudokuBoard();
		SudokuUtils mUtils = new SudokuUtils();
		
		mUtils.printSudokuBoard(mSudokuBoard);
		System.out.println("\nUPDATING AVAILABLE VALUES\n");
		mUtils.updateBoardsAvailableValues(mSudokuBoard);
		mUtils.updateBoardsAvailableValues(mSudokuBoard);
		mUtils.printAvailableValues(mSudokuBoard);
		
		Random ran = new Random();
		System.out.println("REMOVING CELLS FROM GRID RANDOMLY TO CREATE NEW PUZZLE");
		
		boolean puzzleComplete = false;
		int blanks = 0;
		//while puzzle is not complete
		while(!puzzleComplete){
			for(int i = 0; i < 81 && blanks < 50; i++){
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
			}		
			
			if(blanks == 40){
				puzzleComplete = true;
			}
		}
		
		mUtils.printAvailableValues(mSudokuBoard);
		mUtils.printSudokuBoard(mSudokuBoard);
		

	}

}
