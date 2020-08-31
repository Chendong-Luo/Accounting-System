package ui;

import model.Cost;
import model.MonthDateOutOfRange;
import model.Statement;
import model.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static ui.PlayButtonMusic.COINSFALL_FILE;

// represent a popup window to collect data of a Income, and add it to the JTable
public class AddCost extends AddTransaction {

    JOptionPane popup = new JOptionPane();

    // EFFECTS: set up basic elements for the frame
    public AddCost(Statement statement, TransactionRecorder transactionRecorder) {
        super(statement, transactionRecorder);
        addButton = new JButton("AddCost");
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.addActionListener(this);
        panel.add(addButton);
    }

    // MODIFIES: this
    // EFFECTS: add the Cost into the JTable in TransactionRecorder
    @Override
    public void actionPerformed(ActionEvent e) {
        new PlayButtonMusic(COINSFALL_FILE);
        super.actionPerformed(e);
        popup.showMessageDialog(frame, new JLabel(new ImageIcon("./data/coins.png")));
        frame.dispose();
    }

    // EFFECTS: construct and return a new Cost
    @Override
    public Transaction constructTransaction(int year, int month, int day, double amount, String category) {
        return new Cost(year, month, day, amount, category);
    }
}

