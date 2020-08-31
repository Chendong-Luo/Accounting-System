package ui;

import model.YearStatement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

import static ui.PlayButtonMusic.BUTTON_FILE;

// represent a popup window to collect "year" from users
public class YearBill extends Bill {
    String[] columnNames = {"Year"};
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 1);
    JLabel incomeLabel;
    JLabel costLabel;

    // EFFECT: set up elements in the frame
    public YearBill(TransactionRecorder transactionRecorder) {
        super(transactionRecorder);
        this.setupObjects();
        this.setupTables();
        this.setupPanels();
        this.addToPanel();
        this.setupFrame();

    }

    // EFFECTS: add elements ot panel
    @Override
    public void addToPanel() {
        panel.add(costLabel, BorderLayout.NORTH);
        panel.add(incomeLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);

    }

    // EFFECTS: set up objects
    @Override
    public void setupObjects() {
        super.setupObjects();
        costLabel = new JLabel("The Total Cost: $0.0");
        incomeLabel = new JLabel("The Total Income: $0.0 ");
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
    }

    // EFFECTS: set up frame
    @Override
    public void setupFrame() {
        super.setupFrame();
        frame.setTitle("Year Bill");
    }

    // MODIFIES: this
    // EFFECTS: shows the total income and cost for a year
    @Override
    public void actionPerformed(ActionEvent e) {
        new PlayButtonMusic(BUTTON_FILE);
        int year = Integer.parseInt(String.valueOf(table.getValueAt(0, 0)));
        YearStatement ys = statement.searchYear(year);
        incomeLabel.setText("The Total Income: $" + ys.sumIncome());
        costLabel.setText("The Total Cost: $" + ys.sumCost());

    }
}
