package csc143.sudoku;

import java.util.*;

public class SudokuView extends SudokuBoard_v3 implements Observer{
	
	
	public SudokuView(SudokuBase sb){
		super(sb);
		
		

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		for (int i = 0; i < cells.length; i++){
			
			cells[i].repaint();
		}	
	}
}

