package csc143.sudoku;

import java.awt.*;
import java.awt.Event.*;
import javax.swing.*;
import java.util.*;

public class SudokuView extends SudokuBoard implements Observer{

	public SudokuView(SudokuBase sb){
		super(sb);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}

class Symbols implements SymbolRenderer{
	
	Color given = new Color(0,128,0);
	Color entered = Color.black;
	Color blank = new Color(140,140,140);
	
	@Override
	public void drawSymbol(Graphics g, int x, int y, int value) {
		//  --   --
		// |  | |  |
		// |--| |--|
		// |  | |  |
		//  --   --
		
		// FIRST DIGIT
		// vertical LCD pipes. 
		//Left side
		g.drawLine(x+6, y+8, x+6, y+20);
		g.drawLine(x+6, y+20, x+6, y+32);
		//right side
		g.drawLine(x+14, y+8, x+14, y+20);
		g.drawLine(x+14, y+20, x+14, y+32);
		
		// Horizontal LCD pipes
		//top
		g.drawLine(x+8, y+ 8, x+14, y+8);
		//mid
		g.drawLine(x+8, y+19, x+14, y+19);
		//bottom
		g.drawLine(x+8, y+31, x+14, y+31);
		
		//SecondDigit
		// vertical LCD pipes. 
		//Left side
		g.drawLine(x+22, y+ 8, x+22, y+20);
		g.drawLine(x+22, y+20, x+22, y+32);
		//right side
		g.drawLine(x+32, y+ 8, x+32, y+20);
		g.drawLine(x+32, y+20, x+32, y+32);
		
		// Horizontal LCD pipes
		//top
		g.drawLine(x+24, y+8, x+32, y+8);
		//mid
		g.drawLine(x+24, y+19, x+32, y+19);
		//bottom
		g.drawLine(x+24, y+31, x+32, y+31);
				
		
	}
	
	
}
