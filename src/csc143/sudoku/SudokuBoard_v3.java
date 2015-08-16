package csc143.sudoku;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Creates the outline for a playable sudoku board. 
 * 
 * @author Bob McHenry
 * @version 2015-07-12
 */
public class SudokuBoard_v3 extends JComponent implements SelectedCell{
	// Save reference to Base class provided in constructor
	SudokuBase base;
	int size;
	// Set color constants
	final Color grey = new Color(220,220,220);
	final Color white = Color.white;
	
	// Array of cell-populated regions. 
	Region[] regions;
	Cell[] cells;
	
	// Selected Cell
	int selRow;
	int selCol;
	Cell selCell;
	

	/**
	 * Board constructor
	 * 
	 * @param b SudokuBase class that provides the width and height of board as
	 * well as cell state. 
	 */
    public SudokuBoard_v3(SudokuBase b) {
    	// Store reference to base object
    	base = b;
    	size = 50*base.getSize() + 3*(base.getSize() + 3);
    	// set board layout and border. 
    	GridLayout board = new GridLayout(base.getColumns(), base.getRows());
    	
    	setLayout(board);
    	
    	setBorder(BorderFactory.createLineBorder(Color.black));
    	setPreferredSize(new Dimension(size, size));
    	
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
    			if (i % base.getColumns() == 0){
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
    		
    		cells[i] = new Cell(regL, ordL, rowL, colL, i, col);
    		
			regions[regL].add(cells[i]);
    	}
    	selCell = cells[0];
    }
    
    /**
     * Accessor for provided base object.
     * 
     * @return SudokuBase object used in building board. 
     */
    SudokuBase getBase() {
    	return base;
    }
	
    //SelectedCell methods
    @Override
	public void setSelected(int row, int col) {
		selRow = row;
		selCol = col;
		selCell = cells[row * base.getSize() + col];
		repaint();
	}

	@Override
	public int getSelectedRow() {
		return selRow;
	}

	@Override
	public int getSelectedColumn() {
		return selCol;
	}
	
///////////////////////////////////////////////////////////////////////////////
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
    class Cell extends JPanel implements MouseListener{
    	
    	final Color given = new Color(0,128,0);
    	final Color entered = Color.black;
    	final Color blank = new Color(230,230,230);
    	
    	Color cellColor;
    	
    	int reg;
    	int ordinal; 
    	int row;
    	int col;
    	int id;
    	Symbols s;
    	//JLabel val;

    	/** 
    	 * Constructor for cell panel. 
    	 * 
    	 * @param region Region cell belongs to. 
    	 * @param ord    Position of cell in region. 
    	 * @param c      Color of cell background. 
    	 */
    	Cell(int region, int ord, int row, int col, int id, Color c){
    		//Set up cell color and coords. 
    		cellColor = c;
    		reg = region;
    		ordinal = ord;
    		this.row = row;
    		this.col = col;
    		this.id = id;
    		s = new Symbols(this.row, this.col);

    		// Set up cell attributes. 
        	setFocusable(true);
        	setBackground(cellColor);
        	setPreferredSize(new Dimension(50,50));
        	
        	// Set up listeners. 
        	addMouseListener(this); 	
    	}
    	
    	public void paintComponent(Graphics g){
    		super.paintComponent(g);
    		
    		if (selCell == this){
    			g.setColor(Color.yellow);
    			g.fillRect(2, 2, getWidth()-4, getHeight()-4);
    		}
    		setBackground(cellColor);
    		
    		g.setColor(Color.black);
    		g.drawRect(2, 2, getWidth()-4, getHeight()-4);
    		
    		g.setColor(blank);
    		s.drawOutlines(g, 8, 8);
    		
    		if (base.isGiven(row, col)){
    			g.setColor(given);
    		} else {
    			g.setColor(entered);
    		}
			s.drawSymbol(g, 8, 8, base.getValue(row, col));
    		
    		
    	}
    	@Override
		/**
		 * Click event. Sets focus of clicked cell. 
		 */
		public void mouseClicked(MouseEvent e) {
    		System.out.println("Cell index: " + id + " Row: " + row + " Col: " + col);
    		System.out.println("Region: " + reg);
    		System.out.println("value: " + base.getValue(row, col));
    		System.out.println("given? " + base.isGiven(row,col));
			setSelected(this.row, this.col);
			selCell = this;
			repaint();
		}

		// No other mouselistener methods used. 
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		

    }
    class Symbols implements SymbolRenderer{
    	

    	boolean givenVal;
    	
    	Symbols(int row, int col){
    		super();
    		givenVal = isGivenVal(row, col);
    	}
    	private boolean isGivenVal(int row, int col){
    		return base.isGiven(row, col);
    	} 
    	
    	@Override
    	public void drawSymbol(Graphics g, int x, int y, int value) {
    		Graphics2D g2 = (Graphics2D)g;
    		g2.setStroke(new BasicStroke(3));

    		switch (value){
    			case 1:
    				topRight1(g2, x, y);
    				botRight1(g2, x, y);
    				break;
    			case 2:
    				// Top, bot, mid, topright, botLeft
    				top1(g2, x, y);
    				mid1(g2,x,y);
    				bot1(g2, x, y);
    				topRight1(g2, x, y);
    				botLeft1(g2, x, y);
     				break;
    			case 3:
    				// top, mid, bot, topR, botR
    				top1(g2, x, y);
    				mid1(g2,x,y);
    				bot1(g2, x, y);
    				topRight1(g2, x, y);
    				botRight1(g2, x, y);
    				break;
    			case 4:
    				//topL, topR, mid, botR
    				topLeft1(g2, x, y);
    				topRight1(g2, x, y);
    				mid1(g2, x, y);
    				botRight1(g2, x, y);
    				break;
    			case 5:
    				top1(g2, x, y);
    				mid1(g2,x,y);
    				bot1(g2, x, y);
    				botRight1(g2, x, y);
    				topLeft1(g2, x, y);
     				break;
    			case 6:
    				top1(g2, x, y);
    				mid1(g2,x,y);
    				bot1(g2, x, y);
    				botRight1(g2, x, y);
    				topLeft1(g2, x, y);
    				botLeft1(g2, x, y);
    				break;
    			case 7:
    				top1(g2, x, y);
    				topRight1(g2, x, y);
    				botRight1(g2, x, y);
    				break;
    			case 8:
    				top1(g2,x,y);
    				mid1(g2,x,y);
    				bot1(g2,x,y);
    				
    				topLeft1(g2, x, y);
    				botLeft1(g2, x, y);
    				topRight1(g2, x, y);
    				botRight1(g2, x, y);
    				break;
    			case 9:
    				top1(g2, x, y);
    				mid1(g2, x, y);
    				topRight1(g2, x, y);
    				topLeft1(g2,x,y);
    				botRight1(g2, x, y);
    				break;
    			case 10:
    				topRight10(g2, x, y);
    				botRight10(g2, x, y);
    				
    				top1(g2,x,y);
    				bot1(g2,x,y);
    				
    				topLeft1(g2, x, y);
    				botLeft1(g2, x, y);
    				topRight1(g2, x, y);
    				botRight1(g2, x, y);
    				break;
    			case 11:
    				topRight10(g2, x, y);
    				botRight10(g2, x, y);
    				
    				topRight1(g2, x, y);
    				botRight1(g2, x, y);
    				break;
    			case 12:
    				topRight10(g2, x, y);
    				botRight10(g2, x, y);
    				
    				top1(g2, x, y);
    				mid1(g2,x,y);
    				bot1(g2, x, y);
    				topRight1(g2, x, y);
    				botLeft1(g2, x, y);
    				break;
    			 }
    		
    	}

    	//first digit horizontal bars (10s place)
    	void top10(Graphics g, int x, int y){
    		//top
    		g.drawLine(x+8, y+ 8, x+14, y+8);
    	}
    	void mid10(Graphics g, int x, int y){
    		//mid
    		g.drawLine(x+8, y+20, x+14, y+20);
    	}
    	void bot10(Graphics g, int x, int y){
    		//bottom
    		g.drawLine(x+8, y+32, x+14, y+32);
    	}

    	//first digit vertical bars (10s place)
    	void topLeft10(Graphics g, int x, int y){
    		g.drawLine(x+6, y+8, x+6, y+20);
    	}
    	void topRight10(Graphics g, int x, int y){
    		g.drawLine(x+16, y+8, x+16, y+20);
    	}
    	void botLeft10(Graphics g, int x, int y){
    		g.drawLine(x+6, y+20, x+6, y+32);
    	}
    	void botRight10(Graphics g, int x, int y){
    		g.drawLine(x+16, y+20, x+16, y+32);
    	}
    	
    	//second digit horizontal bars (ones place)
    	void top1(Graphics g, int x, int y){
    		g.drawLine(x+22, y+8, x+32, y+8);
    	}
    	void mid1(Graphics g, int x, int y){
    		g.drawLine(x+22, y+20, x+32, y+20);
    	}
    	void bot1(Graphics g, int x, int y){
    		g.drawLine(x+22, y+32, x+32, y+32);
    	}
    	
    	//second digit vertical bars (ones place)
    	void topLeft1(Graphics g, int x, int y){
    		g.drawLine(x+22, y+ 8, x+22, y+20);
    	}
    	void topRight1(Graphics g, int x, int y){
    		g.drawLine(x+32, y+ 8, x+32, y+20);
    	}
    	void botLeft1(Graphics g, int x, int y){
    		
    		g.drawLine(x+22, y+20, x+22, y+32);
    	}
    	void botRight1(Graphics g, int x, int y){
    		g.drawLine(x+32, y+20, x+32, y+32);
    	}

		void drawOutlines(Graphics g, int x, int y){
			Graphics2D g2 = (Graphics2D)g;
    		g2.setStroke(new BasicStroke(3));
			top1(g,x,y);
			mid1(g,x,y);
			bot1(g,x,y);
			
			topLeft1(g, x, y);
			botLeft1(g, x, y);
			topRight1(g, x, y);
			botRight1(g, x, y);
			
			top10(g,x,y);
			mid10(g,x,y);
			bot10(g,x,y);
			
			topLeft10(g, x, y);
			botLeft10(g, x, y);
			topRight10(g, x, y);
			botRight10(g, x, y);
		}
		
    }
}
