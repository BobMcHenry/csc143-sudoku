package csc143.test.sudoku;

import java.awt.event.*;
import javax.swing.*;
import csc143.sudoku.*;

public class SudokuViewTest2 {
    
    private SudokuBase board;
    private SudokuView view;
    private NumericSupport toggle;
    private JCheckBox numeric;
    private JButton animate;
    
    public SudokuViewTest2() {
        JFrame win = new JFrame("Test board");
        win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        board = new SudokuModel(2, 3);
        view = new SudokuView(board);
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
        board.setValue(1, 0, 6);
        board.setValue(3, 1, 5);
        view.setSelected(3, 1);
        win.add(view);
        JPanel toolbar = new JPanel();
        win.add(toolbar, java.awt.BorderLayout.SOUTH);
        animate = new JButton("Update");
        toolbar.add(animate);
        animate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                        animate.setEnabled(false);
                        set(1, 0, 0);
                        set(1, 1, 6);
                        set(0, 4, 4);
                        set(5, 4, 3);
                        set(3, 1, 0);
                        set(0, 4, 0);
                        set(5, 4, 0);
                        set(1, 1, 0);
                        set(1, 0, 6);
                        set(3, 1, 5);
                        animate.setEnabled(true);
                    }
                }).start();
            }
        });
        if(view instanceof NumericSupport) {
            toggle = (NumericSupport)view;
            numeric = new JCheckBox("Numeric");
            toolbar.add(numeric);
            numeric.setSelected(toggle.showsNumeric());
            numeric.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    toggle.setNumeric(numeric.isSelected());
                }
            });
        }
        win.setVisible(true);
        win.pack();

        // create a second view, same model
        win = new JFrame("Second board");
        win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        win.setLocation(320, 0);
        win.add(new SudokuView(board));
        win.setVisible(true);
        win.pack();
    }
    
    public static void main(String[] args) {
        new SudokuViewTest2();
    }
    
    private void set(int row, int col, int value) {
        view.setSelected(row, col);
        pause();
        board.setValue(row, col, value);
        view.repaint();
        pause();
    }
    
    public static void pause() {
        try {
            Thread.sleep(50);
        } catch(InterruptedException e) {
        }
    }
    
}
