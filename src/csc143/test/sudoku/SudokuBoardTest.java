package csc143.test.sudoku;

import javax.swing.*;
import csc143.sudoku.*;
import java.awt.event.*;

public class SudokuBoardTest implements ActionListener {
    
    JFrame win;
    SudokuBase base;
    SudokuBoard_v3 board;
    SelectedCell selected;
    JLabel output;
    JLabel error;
    JButton query;
    JButton set;
    JTextField txtRow;
    JTextField txtCol;
    JPanel controls;
    
    public SudokuBoardTest(int row, int col) {
        String title = row + "x" + col + " Board";
        win = new JFrame(title);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        base = new SudokuModel(row, col);
        board = new SudokuBoard_v3(base);
        JPanel pnl = new JPanel();
        pnl.add(board);
        pnl.setBackground(java.awt.Color.cyan);
        win.add(pnl);
        controls = new JPanel();
        win.add(controls, java.awt.BorderLayout.SOUTH);
        pnl = new JPanel();
        controls.add(pnl);
        output = new JLabel();
        if(board instanceof SelectedCell) {
            selected = (SelectedCell) board;
            pnl.add(query = new JButton("Query"));
            pnl.add(output);
            query.addActionListener(this);
            controls.add(pnl = new JPanel());
            pnl.add(new JLabel("row:"));
            pnl.add(txtRow = new JTextField(2));
            pnl.add(new JLabel("col:"));
            pnl.add(txtCol = new JTextField(2));
            pnl.add(set = new JButton("Set"));
            pnl.add(error = new JLabel());
            error.setForeground(java.awt.Color.red);
            set.addActionListener(this);
            controls.setLayout(new java.awt.GridLayout(2, 1));
        } else {
            pnl.add(output);
            output.setText("SelectedCell not supported");
        }
        win.setVisible(true);
        win.pack();
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == query) {
        	
            output.setText("Selected cell: " +
                           selected.getSelectedRow() + ", " +
                           selected.getSelectedColumn());
            error.setText("");
           // win.repaint();
            
            
            
        } else if(e.getSource() == set) {
            output.setText("");
            try {
                int row = Integer.parseInt(txtRow.getText());
                int col = Integer.parseInt(txtCol.getText());
                selected.setSelected(row, col);
                error.setText("");
            } catch(NumberFormatException ex) {
                error.setText("Non-integer input: " + ex.getMessage());
            } catch(IllegalArgumentException ex) {
                error.setText("Error: " + ex.getMessage());
            }
        }
        win.repaint();
        //win.pack();
    }
    
    public static void main(String[] args) {
        new SudokuBoardTest(2, 2);
        new SudokuBoardTest(2, 3);
//        new SudokuBoardTest(2, 4);
//        new SudokuBoardTest(2, 5);
//        new SudokuBoardTest(3, 2);
//        new SudokuBoardTest(3, 3);
//        new SudokuBoardTest(3, 4);
    }
    
}
