package csc143.sudoku;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Creates the outline for a playable sudoku board. 
 * 
 * @author Bob McHenry
 * @version 2015-07-12
 */
public class CopyOfSudokuBoard_v2 extends JComponent implements SelectedCell{
	// TODO Implement Coordinate Transfer
	// TODO Fix border spacing
	// TODO Finetune listeners and setup keyboard control. 
	
	// Save reference to Base class provided in constructor
	SudokuBase base;
	
	// Set color constants
	final Color grey = new Color(220,220,220);
	final Color white = Color.white;
	
	// Array of cell-populated regions. 
	Region[] regions;
	Cell[] cells;
	
	// Selected Cell
	int selRow;
	int selCol;
	
	@Override
	public void setSelected(int row, int col) {
		selRow = row;
		selCol = col;
	}

	@Override
	public int getSelectedRow() {
		return selRow;
	}

	@Override
	public int getSelectedColumn() {
		return selCol;
	}
	
	/**
	 * Board constructor
	 * 
	 * @param b SudokuBase class that provides the width and height of board as
	 * well as cell state. 
	 */
    public CopyOfSudokuBoard_v2(SudokuBase b) {
    	// Store reference to base object
    	base = b;
    	
    	// set board layout and border. 
    	GridLayout board = new GridLayout(base.getColumns(), base.getRows());
    	
    	setLayout(board);
    	
    	setBorder(BorderFactory.createLineBorder(Color.black));
    	
    	
    	//Set up regions and add to board. Cells added in Region constructor.
    	regions = new Region[base.getSize()];
    	cells = new Cell[base.getSize() * base.getSize()];
    	    	
    	// initial region color
    	Color c = white;
    	
    	
    	//For loop to build regions. 
    	for (int i = 0; i < regions.length; i++){
    		// Construct and add regions to board. 
    		regions[i] = new Region(base.getColumns(), base.getRows(), i, c);
    		add(regions[i]);
    		
    		// Odd column count color pattern
    		// Alternate every pass
    		if (base.getRows() % 2 != 0){
    			if (c.equals(white)){
    				c = grey;
    			} else {
    				c = white;
    			}
    		} else {
    			// even pattern is W,G,G,W,W,G,G,W,W
    			//				   0,1,2,3,4,5,6,7,8
    			// Swap colors on the even i's
    			if (i % 2 == 0){
    				if (c == white)
    					c = grey;
    				else
    					c = white;
    			}
    		}
    	}
    	
    	//for loop to build cells. 
    	for (int i = 0; i < cells.length; i++){
    		
    		
    		//Correct
    		int rowL = i / base.getSize();
    		int colL = i % base.getSize();
    		
    		//correct
    		int ordL = rowL % base.getRows() * base.getColumns() + colL % base.getColumns();
    		int regL =  ((rowL / base.getRows()) * base.getRows()) + (colL / base.getColumns());
    		
    		Color col = regions[regL].getBackground();
    		
    		cells[i] = new Cell(regL, ordL, rowL, colL, col);
    		
    		
    		//cells[i].add(new JLabel("ID: " + i));
    		//cells[i].add(new JLabel("ID: " + i));
    		//cells[i].add(new JLabel("" + regL + " : " + ordL ));
			//cells[i].add(new JLabel("(" + rowL + ", " + colL + ")"));

    		
			regions[regL].add(cells[i]);
    	}
    }
    
    /**
     * Accessor for provided base object.
     * 
     * @return SudokuBase object used in building board. 
     */
    SudokuBase getBase() {
    	return base;
    }
    
    ///////////////////////////////////////////////////////////////////////////
    /**
     * Custom region JPanel, contains cell panels.  
     * 
     * @author Bob McHenry
     *
     */
    class Region extends JPanel{
    	// Region index from SudokuBoard.regions array.
    	int index;
    	
    	/**
    	 * Region constructor. Formats Region panel and adds cells. 
    	 * 
    	 * @param row  Number of rows from base
    	 * @param col  Number of columns from base
    	 * @param ind  Index of region cell belongs to. 
    	 * @param c    Color value of cell. 
    	 */
    	Region(int row, int col, int ind, Color c){
    		// index from main region array. 
    		index = ind;
    		
    		// Setup region layout and background. 
    		GridLayout regGrid = new GridLayout(col, row);
    		regGrid.setHgap(1);
    		regGrid.setVgap(1);
    		
    		setLayout(regGrid);
    		setBackground(c);
    	}
    }

///////////////////////////////////////////////////////////////////////////////
    /**
     * Custom cell panels. Contains region and ordinal values, and implements
     * event listeners. 
     * 
     * Row Col conversions coming soon. 
     * 
     * @author Bob McHenry
     *
     */
    class Cell extends JPanel 
    	implements MouseListener, KeyListener, FocusListener {
    	
    	Color cellColor;
    	
    	int reg;
    	int ordinal; 
    	int row;
    	int col;
    	
    	/** 
    	 * Constructor for cell panel. 
    	 * 
    	 * @param region Region cell belongs to. 
    	 * @param ord    Position of cell in region. 
    	 * @param c      Color of cell background. 
    	 */
    	Cell(int region, int ord, int row, int col, Color c){
    		//Set up cell color and coords. 
    		cellColor = c;
    		reg = region;
    		ordinal = ord;
    		this.row = row;
    		this.col = col;
    		
    		// Set up cell borders. 
    		Border line = BorderFactory.createLineBorder(Color.black);
    		Border clear = BorderFactory.createEmptyBorder(1,1,1,1);
    		Border b = BorderFactory.createCompoundBorder(clear, line);
    		
    		// Set up cell attributes. 
        	setFocusable(true);
        	setBackground(cellColor);
        	setPreferredSize(new Dimension(50,50));
        	setBorder(b);
        	
        	// Set up listeners. 
        	addMouseListener(this);
        	addKeyListener(this);
        	addFocusListener(this);
        	
    	}
    	
		@Override
		/**
		 * Click event. Sets focus of clicked cell. 
		 */
		public void mouseClicked(MouseEvent e) {
			grabFocus();
		}

		// No other mouselistener methods used. 
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		
		// KeyListener methods not yet implemented. 
		public void keyTyped(KeyEvent e) {
			
		}
		public void keyPressed(KeyEvent e) {
			
		}
		public void keyReleased(KeyEvent e){}
		
		// Focus listeners. 
		@Override
		public void focusGained(FocusEvent e) {
			setBackground(Color.yellow);
			setSelected(this.row, this.col);
			paint(getGraphics());
		}

		@Override
		public void focusLost(FocusEvent e) {
			setBackground(cellColor);
		}
	}
}
