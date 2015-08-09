package csc143.sudoku;
/**
 * SudokuModel adds methods that evaluate the values in rows, columns, and 
 * regions to test for the proper state.  
 * 
 * @author Bob McHenry
 * @version 2015-07-19
 *
 */
public class SudokuModel extends SudokuCore {
    /**
     * SudokuModel constructor. Builds model with the appropriate number of
     * rows and columns for the board. 
     * 
     * @param r number of rows
     * @param c number of columns
     */
    public SudokuModel(int r, int c) {
    	// r = rows, c = columns
        super(r, c);
    }
    /**
     * Accessor method that checks a row of values and returns the appropriate 
     * state. 
     * 
     * @param n Row number to be checked.
     * @return State of given row. 
     */
    public State getRowState(int n) { 
    	// Store row values for evaluation
    	int[] tempRow = new int[getSize()];
    	
    	// i = column index, n = row number
    	for (int i = 0; i < tempRow.length;i++ ){
    		//Check for zero values - mark incomplete if found
    		if (getValue(n , i) == 0){
    			return State.INCOMPLETE;
    		} 
    		// If no zero values, add to array
    		tempRow[i] = getValue(n , i);    			 
    		}
    	// scan array for duplicate values
    	for (int i = 0; i < tempRow.length; i++){
    		for (int j = 0; j < tempRow.length; j++){
    			if (tempRow[i] == tempRow[j]  && i != j){
    				return State.ERROR;
    			}
    		}
    	}
    	// If no zero values and no duplicate values
        return State.COMPLETE; 
    }
    
    /**
     * Accessor method that checks a column of values and returns the 
     * appropriate state.
     * 
     * @param n Column number to be checked. 
     * @return State of given column. 
     */
    public State getColumnState(int n) { 
    	// Store row values for evaluation
    	int[] tempCol = new int[getSize()];
    	
    	// i = row index, n = column number
    	for (int i = 0; i < tempCol.length;i++ ){
    		//Check for zero values - mark incomplete if found
    		if (getValue(i , n) == 0){
    			return State.INCOMPLETE;
    		} 
    		// If no zero values, add to array
    		tempCol[i] = getValue(i , n);    			 
    		}
    	// scan array for duplicate values
    	for (int i = 0; i < tempCol.length; i++){
    		for (int j = i; j < tempCol.length-i; j++){
    			if (tempCol[i] == tempCol[j] && i != j){
    				return State.ERROR;
    			}
    		}
    	}
    	// If no zero values and no duplicate values
        return State.COMPLETE; 
    }
    
    /**
     * Accessor method that checks a region of values and returns the 
     * appropriate state. 
     * 
     * @param n Region number to be checked. 
     * @return State of given region. 
     */
    public State getRegionState(int n) { 
    	// Calculate row and column from region
    	int row = n / getColumns() * getRows();
    	int col = n % getColumns() * getColumns();
    			
    	// Store row values for evaluation
    	int[] tempReg = new int[getSize()];
    		
    	// i used for array index only. 
    	for (int i = 0; i < tempReg.length;i++ ){
    	
    		//Check for zero values - mark incomplete if found
    		if (getValue(row , col) == 0){
    			return State.INCOMPLETE;
    		}
    		
    		// If no zero values, add to array
    		tempReg[i] = getValue(row , col);
    		
    		// increment col, then check for region bounds. Reset col and 
    		// increment row when col passes out of region.
    		col++;
    		if (col % getColumns() == 0){
    			col = n % getColumns() * getColumns();
    			row++;
    		}
    	}

    	// scan array for duplicate values
    	for (int i = 0; i < tempReg.length; i++){
    		for (int j = i; j < tempReg.length-i; j++){
    			if (tempReg[i] == tempReg[j] && i != j){
    				return State.ERROR;
    			}
    		}
    	}
    	// If no zero values and no duplicate values
        return State.COMPLETE; 
    }    
}