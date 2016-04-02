import java.util.Random;


public class SudokuMain {

	public static void main(String[] args) {
		//create a sudoku board and print to console
		SudokuBoard mBoard = new SudokuBoard();
		SudokuUtils mUtils = new SudokuUtils();
		
		mUtils.printSudokuBoard(mBoard);
		System.out.println("\nUPDATING AVAILABLE VALUES\n");
		mUtils.updateAvailableValues(mBoard);
		mUtils.updateAvailableValues(mBoard);
		mUtils.printAvailableValues(mBoard);
		
		Random ran = new Random();
		System.out.println("REMOVING CELLS FROM GRID RANDOMLY TO CREATE NEW PUZZLE");
		for(int i = 0; i < 30; i++){
			int randomRow = ran.nextInt(9);
			int randomColumn = ran.nextInt(9);
			//if cell is not already blank, set it to blank
			if(mBoard.mSudokuBoard[randomRow][randomColumn].getValue() != 0){
				mBoard.mSudokuBoard[randomRow][randomColumn].setValue(0);
				System.out.println("Removing value from cell " + randomRow + " | " + randomColumn);
			}
			//else, decrement i and find another cell to make blank
			else{
				i--;
			}
		}
		
		System.out.println("\nUPDATING AVAILABLE VALUES\n");
		mUtils.updateAvailableValues(mBoard);
		mUtils.printAvailableValues(mBoard);
		mUtils.printSudokuBoard(mBoard);
		

	}

}
