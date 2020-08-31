package ui;


import model.MonthStatement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;

import static ui.PlayButtonMusic.DISCORDJOIN_FILE;


// represents a popup window to collect "year" and "month" from users
public class MonthBill extends Bill {
    private String[] columnNamesForSearch = {"Year", "Month"};
    private DefaultTableModel tableModel = new DefaultTableModel(columnNamesForSearch, 1);

    public MonthBill(TransactionRecorder transactionRecorder) {
        super(transactionRecorder);
        this.setupObjects();
        this.setupTables();
        this.setupPanels();
        this.addToPanel();
        this.setupFrame();
    }


    // EFFECTS: add scrollPane and button to panel
    @Override
    public void addToPanel() {
        panel.add(scrollPane);
        panel.add(button);
    }

    // EFFECTS: initializes objects
    @Override
    public void setupObjects() {
        super.setupObjects();
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
    }

    // set up frame and its name
    @Override
    public void setupFrame() {
        super.setupFrame();
        frame.setTitle("Month Bill");
    }

    // MODIFIES: this
    // EFFECTS: shows transactions of a selected year and month
    @Override
    public void actionPerformed(ActionEvent e) {
        int year = Integer.parseInt(String.valueOf(tableModel.getValueAt(0, 0)));
        int month = Integer.parseInt(String.valueOf(tableModel.getValueAt(0, 1)));

        new PlayButtonMusic(DISCORDJOIN_FILE);

        transactionRecorder.getMenuBar().setVisible(false);
        transactionRecorder.getBack().setVisible(true);
        transactionRecorder.getDelete().setVisible(false);
        transactionRecorder.getQuit().setVisible(false);
        transactionRecorder.getSave().setVisible(false);
        transactionRecorder.getShowMonthBill().setVisible(false);
        transactionRecorder.getShowYearBill().setVisible(false);

        ms = statement.searchMonth(year, month);
        frame.dispose();
        transactionRecorder.removeTable();
        transactionRecorder.fillTableModel(ms.getTransactions());

    }


}




