package csc143.sudoku;

import javax.swing.*;

import csc143.sudoku.SudokuBase.State;

import java.awt.*;
import java.awt.event.*;

public class SudokuController{

	SudokuModel model;
	SudokuView view;
	JFrame win;
	JButton[] buttons;
	JPanel buttonBar;
	JPanel checkBar;
	CheckPanel rowCheck;
	CheckPanel colCheck;
	CheckPanel regCheck;
	
	public SudokuController(){
		//model = new SudokuModel(rows, cols);
		model = (SudokuModel)makeBoard();
		int rows = model.getRows();
		int cols = model.getColumns();
		
		win = new JFrame("Sudoko " + rows + " x " + cols );

		view = new SudokuView(model);
		
		win.setPreferredSize(
				new Dimension(50 * (model.getSize()+1) + 100, 
						50 * (model.getSize()+1)+100));
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buttons = new JButton[model.getSize()+1];
		
		buttonBar = new JPanel();
				
		buttonBar.setLayout(new FlowLayout());
		for (int i = 0; i <= model.getSize(); i++){
			buttons[i] = new JButton("" + i);
			buttons[i].setPreferredSize(new Dimension(35,35));
			if (i !=0 ){
				buttonBar.add(buttons[i]);
			}
			int num = i;
			buttons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//System.out.println("Button " + num);
					model.setValue(view.getSelectedRow(), view.getSelectedColumn(), num);
					view.repaint();
					win.repaint();
				}
				});
		}

		buttonBar.add(buttons[0]);

		
		checkBar = new JPanel();
		checkBar.setPreferredSize(new Dimension(140, 120));
		checkBar.setLayout(new GridLayout(3,3));
		
		rowCheck = new RowPanel();
		colCheck = new ColPanel();
		regCheck = new RegPanel();
		rowCheck.setPreferredSize(new Dimension(40,120));
		colCheck.setPreferredSize(new Dimension(40,120));
		regCheck.setPreferredSize(new Dimension(40,120));

		checkBar.add(rowCheck);
		checkBar.add(colCheck);
		checkBar.add(regCheck);

		
		win.setLayout(new BorderLayout());
		win.add(buttonBar, BorderLayout.NORTH);
		win.add(view, BorderLayout.CENTER);
		win.add(checkBar, BorderLayout.EAST);
	
		win.setResizable(false);
		win.setVisible(true);
		win.pack();
		

	}
	
	
	public static SudokuBase makeBoard() {
		  SudokuBase board = new SudokuModel(4, 3);
		  board.setValue(0, 3, 6);
		  board.setValue(0, 5, 1);
		  board.setValue(1, 2, 4);
		  board.setValue(1, 4, 5);
		  board.setValue(1, 5, 3);
		  board.setValue(2, 3, 3);
		  board.setValue(3, 2, 6);
		  board.setValue(4, 0, 2);
		  board.setValue(4, 1, 3);
		  board.setValue(4, 3, 1);
		  board.setValue(5, 0, 6);
		  board.setValue(5, 2, 1);
		  board.fixGivens();
		  return board;
		}
	
	public static void main(String[] args) {
		new SudokuController();

	}
	abstract class CheckPanel extends JPanel{
		
		CheckPanel(){
			super();
			repaint();
		}
		
		Color getStateColor(SudokuModel m, int val){
			if (this instanceof RowPanel){
				if (m.getRowState(val) == State.COMPLETE){
					return Color.GREEN;
				} else if (m.getRowState(val) == State.INCOMPLETE){
					return Color.yellow;
				} else {
					return Color.red;
				}
			} else if (this instanceof ColPanel){
				if (m.getColumnState(val) == State.COMPLETE){
					return Color.GREEN;
				} else if (m.getColumnState(val) == State.INCOMPLETE){
					return Color.yellow;
				} else {
					return Color.red;
				}
			} else /*if (this instanceof RegPanel)*/{

				if (m.getRegionState(val) == State.COMPLETE){
					return Color.GREEN;
				} else if (m.getRegionState(val) == State.INCOMPLETE){
					return Color.yellow;
				} else {
					return Color.red;
				}
			}
		}
	}
	
	class RowPanel extends CheckPanel{

		public void paintComponent(Graphics g){
			super.paintComponent(g);
			drawRows(g);
		}
	
		private void drawRows(Graphics g) {
			int x = 0;
			int y = getHeight()/model.getSize();
			int w = getWidth()-5;
			int h = getHeight()/model.getSize();
			for (int i = 0; i < model.getSize();i++){
				g.setColor(getStateColor(model, i));
				g.fillRect( x,i*y,w,h);
				
				g.setColor(Color.black);
				g.drawRect(x,i*y,w,h);				
			}
		}
	}
	
	class ColPanel extends CheckPanel{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			drawCols(g);
		}
	
		private void drawCols(Graphics g) {
			int x = getWidth()/model.getSize();
			int y = 0;
			int w = (getWidth())/model.getSize();
			int h = getHeight() - 5;
			
			for (int i = 0; i < model.getSize();i++){
				
				g.setColor(getStateColor(model, i));
				g.fillRect( i*x,y,w,h);
				
				g.setColor(Color.black);
				g.drawRect(i*x,y,w,h);
				
			}
			
		}
	}
	class RegPanel extends CheckPanel{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			drawRegs(g);
		}
	
		private void drawRegs(Graphics g) {
			int x = 0;
			int y = 0;
			int w = getWidth()/model.getRows()-model.getRows()/2;
			int h = getHeight()/model.getColumns()-model.getColumns()/2;
			
			for (int i = 0; i < model.getSize();i++){
				
				g.setColor(getStateColor(model, i));
				//g.setColor(Color.green);
				g.fillRect(x,y,w,h);
				
				g.setColor(Color.black);
				g.drawRect(x,y,w,h);

				if ( x < w*(model.getRows()-1)){
					x += w;
				} else {
					x = 0;
					y += h;
				}
			}
		}
	}
}