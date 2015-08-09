package csc143.sudoku;

import java.awt.*;
import java.awt.Event.*;
import java.util.*;

import javax.swing.*;

public class SudokuView extends SudokuBoard implements Observer{

	public SudokuView(SudokuBase sb){
		super(sb);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	public void setSelected(int i, int j) {
		// TODO Auto-generated method stub
		
	}
}

class Symbols implements SymbolRenderer{
	
	Color given = new Color(0,128,0);
	Color entered = Color.black;
	Color blank = new Color(140,140,140);
	
	@Override
	public void drawSymbol(Graphics g, int x, int y, int value) {
		g.drawLine(x+6, y+8, x+6, y+20);
		
	}
	
	
}
