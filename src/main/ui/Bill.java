package ui;

import model.MonthStatement;
import model.Statement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represent an abstract class which collects the duplication for MonthBill class and YearBill class
public abstract class Bill implements ActionListener {
    JFrame frame;
    JScrollPane scrollPane;
    JButton button;
    TransactionRecorder transactionRecorder;
    Statement statement;
    MonthStatement ms;
    JTable table;
    JPanel panel;

    // EFFECTS: construct a new Bill according to the transactionRecorder
    public Bill(TransactionRecorder transactionRecorder) {
        this.transactionRecorder = transactionRecorder;
        this.statement = transactionRecorder.getStatement();

    }

    // EFFECTS: initializes objects of basic elements
    public void setupObjects() {
        frame = new JFrame();
        panel = new JPanel();
        button = new JButton("Search");
        button.addActionListener(this);

    }

    // EFFECTS: set up tables
    public void setupTables() {
        table.setPreferredScrollableViewportSize(new Dimension(75, 75));
        table.setFillsViewportHeight(true);
    }

    // EFFECTS: add things to the panel
    public abstract void addToPanel();


    // EFFECTS: set up panels
    public void setupPanels() {
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(0, 1));
    }

    // EFFECTS: set up frame
    public void setupFrame() {
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(400, 200);
    }

    // EFFECTS: responds to the action event
    public abstract void actionPerformed(ActionEvent e);

}
