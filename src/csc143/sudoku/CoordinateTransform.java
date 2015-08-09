package csc143.sudoku;

/**
 * This interface supports the transform of between
 * row/column and region/ordinal coordinates for a 
 * cell in SudokuBoard. Needless to say, these transforms
 * depend on the current value of rows and columns in the
 * underlying SudokuBase.
 */
public interface CoordinateTransform {
    
    /**
     * Transform the region/ordinal coordinates for a
     * cell into row/column coordinates.
     * @param coord The region/ordinal coordinates
     * @return The row/column coordinates
     */
    public RowCol toRowCol(RegOrd coord);
    
    /**
     * Transform the row/column coordinates for a
     * cell into region/ordinal coordinates.
     * @param coord row/column The coordinates
     * @return The region/ordinal coordinates
     */
    public RegOrd toRegOrg(RowCol coord);
    
}

/**
 * This is a "helper class" to support the coordinate
 * transformation methods of CoordinateTransform.
 */
class RowCol {

    /**
     * The row for the cell. This field is package 
     * private since there is no value in having
     * getter and setter methods for this value.
     */
    int row;
    
    /**
     * The column for the cell. This field is package 
     * private since there is no value in having
     * getter and setter methods for this value.
     */
    int column;

}

/**
 * This is a "helper class" to support the coordinate
 * transformation methods of CoordinateTransform.
 */
class RegOrd {

    /**
     * The region for the cell. This field is package 
     * private since there is no value in having
     * getter and setter methods for this value.
     */
    int region;
    
    /**
     * The ordinal for the cell. This field is package 
     * private since there is no value in having
     * getter and setter methods for this value.
     */
    int ordinal;

}
