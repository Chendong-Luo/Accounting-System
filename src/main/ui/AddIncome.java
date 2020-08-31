package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static ui.PlayButtonMusic.COINSFALL_FILE;

// represent a popup window to collect data of a Income, and add it to the JTable
public class AddIncome extends AddTransaction {

    JOptionPane popup = new JOptionPane();

    // EFFECTS: set up basic elements for the frame
    public AddIncome(Statement statement, TransactionRecorder transactionRecorder) {
        super(statement, transactionRecorder);
        addButton = new JButton("AddIncome");
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.addActionListener(this);
        panel.add(addButton);
    }

    // MODIFIES: this
    // EFFECTS: add the Income into the JTable in TransactionRecorder
    @Override
    public void actionPerformed(ActionEvent e) {
        new PlayButtonMusic(COINSFALL_FILE);
        super.actionPerformed(e);
        popup.showMessageDialog(frame, new JLabel(new ImageIcon("./data/coins.png")));
        frame.dispose();
    }

    // EFFECTS: construct and return a new Income
    @Override
    public Transaction constructTransaction(int year, int month, int day, double amount, String category) {
        return new Income(year, month, day, amount, category);
    }
}
