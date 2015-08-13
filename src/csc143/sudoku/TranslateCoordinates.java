package csc143.sudoku;

public class TranslateCoordinates implements CoordinateTransform {
/*
 * 
 * int ordL = rowL % base.getRows() * base.getColumns() + colL % base.getColumns();
 * 
 * int regL =  ((rowL / base.getRows()) * base.getRows()) + (colL / base.getColumns());
 */

	@Override
	public RowCol toRowCol(RegOrd coord) {
		RowCol rc = new RowCol();
		RegOrd ro = new RegOrd();

		ro.region = coord.region;
		ro.ordinal = coord.ordinal;
		
		rc.row = 0;
		rc.column = 0;
		return rc;
	}

	@Override
	public RegOrd toRegOrg(RowCol coord) {
		RowCol rc = new RowCol();
		RegOrd ro = new RegOrd();
		int row = coord.row % base.getRows() * base.getColumns() + colL % base.getColumns();

		return rc;
	}

}
