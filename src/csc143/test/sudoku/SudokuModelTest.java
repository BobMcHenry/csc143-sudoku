package csc143.test.sudoku;

public class SudokuModelTest {
    
    public static void randomTest(int rows, int cols) {
        
        // create a board of the given layout
        csc143.sudoku.SudokuModel model = new csc143.sudoku.SudokuModel(rows, cols);
        int size = model.getSize();
        
        // put random values into the board
        for(int i = 0; i < size * size; i++) {
            int row = (int)(Math.random() * size);
            int col = (int)(Math.random() * size);
            int value = (int)(Math.random() * (size) + 1);
            model.setValue(row, col, value);
        }
        
        // print out the board
        System.out.println("Random board: " + rows + "x" + cols);
        System.out.println(model);
        
        // report state
        for(int i = 0; i < size; i++)
            System.out.println("Row " + i + " state: " + model.getRowState(i));
        for(int i = 0; i < size; i++)
            System.out.println("Column " + i + " state: " + model.getColumnState(i));
        for(int i = 0; i < size; i++)
            System.out.println("Region " + i + " state: " + model.getRegionState(i));
        System.out.println();
        
    }
    
    public static void controlBoard(){
    	csc143.sudoku.SudokuModel model = new csc143.sudoku.SudokuModel(3, 3);
        int size = model.getSize();
        int[] testVal = {
        				 1,2,3, 4,5,6, 7,8,9,
        				 4,5,6, 5,6,7, 8,9,1,
        				 7,8,9, 6,7,8, 9,1,2,
        				 
        				 2,3,6, 7,8,9, 1,2,3,
        				 5,6,7, 8,0,1, 2,3,4,
        				 8,9,8, 9,1,2, 3,4,5,
        				 
        				 3,1,9, 1,2,3, 4,5,1,
        				 6,4,1, 2,3,4, 2,3,9,
        				 9,7,2, 3,4,5, 6,7,8
        				 };
        
        
        for(int i = 0; i < size * size; i++) {
        	model.setValue(i / size, i % size, testVal[i]);
        }
        System.out.println("Control 3x3 board");
        System.out.println(model);
        
        // report state
        for(int i = 0; i < size; i++)
            System.out.println("Row " + i + " state: " + model.getRowState(i));
        System.out.println();
        for(int i = 0; i < size; i++)
            System.out.println("Column " + i + " state: " + model.getColumnState(i));
        System.out.println();
        for(int i = 0; i < size; i++)
            System.out.println("Region " + i + " state: " + model.getRegionState(i));
        System.out.println();
    }
    
    public static void main(String[] args) {
        //randomTest(2,2);
        randomTest(2,3);
        //randomTest(3,3);
    	//controlBoard();
    }
}
