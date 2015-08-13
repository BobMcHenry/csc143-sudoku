package csc143.sudoku;
import javax.swing.*;

import java.awt.*;

public class SudokuBoard_v2 extends JComponent implements SelectedCell {
    
	// Save reference to Base class provided in constructor
	SudokuBase base;
	
	// Set color constants
	final Color grey = new Color(220,220,220);
	final Color white = Color.white;
	
	// Selected Cell
	int selRow;
	int selCol;
	//board size in pixels
	int size; 
	
    public SudokuBoard_v2(SudokuBase b) {
    	// Store reference to base object
    	base = b;
    	size = 50*base.getSize() + 3*(base.getSize() + 1) + 1;
    	
    	
    	setPreferredSize(new Dimension(size,size));
    	this.add(new JButton("Test"));
    }
    
    SudokuBase getBase() {
    	return base;
    }
	
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
	
    public void paintComponent(Graphics g){
    	super.paintComponent(g);
    	//int cellPad;
    	Color c = white;
    	//board background
    	g.setColor(c);
    	g.fillRect(0, 0, size, size);
    	//board border
    	g.setColor(Color.black);
    	g.drawRect(0, 0, size-1, size-1);
    	
    	//cells
    	int x = 3;
    	int y = 3;
    	for (int i = 0; i < base.getSize() ; i++){
    		drawRegion(g,x,y,c);

    		x += 53*base.getColumns();

    		if (x > size - 50){
    			x = 3;
    			y += 53*base.getRows();
    		}
    		
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
    }
    private void drawRegion(Graphics g, int x, int y, Color c){
    	int row = base.getRows();
    	int col = base.getColumns();
    	
    	int adjX = 0;
    	int adjY = 0;
    	
    	 for (int i = 0; i < row*col; i++){
    		g.setColor(c);
        	g.fillRect(x + adjX, y + adjY, 50, 50);
        		
        	g.setColor(Color.black);
        	g.drawRect(x + adjX, y + adjY, 50, 50);
        	
        	if (i % col == col - 1){
        		adjX = 0;
        		adjY += 53;
        	} else {
        		adjX += 53;
        	}
    	 } 
    	
    	
    }
}