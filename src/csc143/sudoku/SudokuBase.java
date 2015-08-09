package csc143.sudoku;
/**
 * SudokoBase provides the dimensions to create a sudoku board. 
 * 
 * @author Bob McHenry based on provided code. 
 * @version 2015-07-11
 */
public abstract class SudokuBase extends java.util.Observable {
    
    private final int rows;
    private final int columns;
    private final int size;
    // Values for region state. 
    public enum State {COMPLETE, INCOMPLETE, ERROR};
    
    /**
     * 
     * @param layoutRows number of rows to build
     * @param layoutColumns number of columns to build
     */
    public SudokuBase(int layoutRows, int layoutColumns) {
        rows = layoutRows;
        columns = layoutColumns;
        size = columns * rows;
    }
    
    /**
     * Accessor for rows field. 
     * @return Number of rows in board.
     */
    public int getRows() {
        return rows;
    }
    
    /**
     * Accesor for columns field. 
     * @return Number of columns in board.
     */
    public int getColumns() {
        return columns;
    }
    
    /**
     * Accesor for size field. 
     * @return Number of cells per region.
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Gets the value of the cell at the given intersection.
     * 
     * @param row Row of desired cell. 
     * @param col Col of queried cell. 
     * @return Numerical value of desired cell.
     */
    public abstract int getValue(int row, int col);
    
    /**
     * Mutator for cell's value. 
     * 
     * @param row Row number of desired cell. 
     * @param col Col number of desired cell. 
     * @param value New value to be assigned to cell. 
     */
    public abstract void setValue(int row, int col, int value);
    
    /**
     * Checks a cell to see if the value is a given value used to enter the 
     * board or a player entered value.
     * 
     * @param row Row number of desired cell.
     * @param col Col number of desired cell. 
     * @return True if number is given value, false if empty or player provided. 
     */
    public abstract boolean isGiven(int row, int col);
    
    /**
     * Sets the given values to set up the play area. 
     */
    public abstract void fixGivens();
    
    /**
     * Accessor method that checks a row of values and returns the appropriate 
     * state. 
     * 
     * @param n Row number to be checked.
     * @return State of given row. 
     */
    public abstract State getRowState(int n);
    
    /**
     * Accessor method that checks a column of values and returns the 
     * appropriate state.
     * 
     * @param n Column number to be checked. 
     * @return State of given column. 
     */
    public abstract State getColumnState(int n);
    
    /**
     * Accessor method that checks a region of values and returns the 
     * appropriate state. 
     * 
     * @param n Region number to be checked. 
     * @return State of given region. 
     */
    public abstract State getRegionState(int n);
    
}
