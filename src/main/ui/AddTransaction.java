package ui;

import model.MonthDateOutOfRange;
import model.Statement;
import model.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represent a popup window to collect data of a transaction, and add it to the JTable
public abstract class AddTransaction implements ActionListener {
    protected JFrame frame;
    protected JPanel panel;
    protected Statement statement;
    protected JTable table;
    protected TransactionRecorder transactionRecorder;
    protected JButton addButton;
    protected JScrollPane scrollPane;
    protected Object[] object = {"", "", "", "", ""};
    private final String[] columnNames = {"Year", "Month", "Date", "Amount", "Description"};
    protected DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

    // EFFECTS: set up basic elements for the frame
    public AddTransaction(Statement statement, TransactionRecorder transactionRecorder) {
        frame = new JFrame();
        panel = new JPanel();
        this.statement = statement;
        this.transactionRecorder = transactionRecorder;

        tableModel.addRow(object);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        table.setPreferredScrollableViewportSize(new Dimension(10, 5));
        table.setFillsViewportHeight(true);

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(0, 1));

        //Add the scroll pane to this panel.
        panel.add(scrollPane);


        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Add Transaction");
        frame.pack();
        frame.setSize(400, 200);
        frame.setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: add the transaction into the JTable in TransactionRecorder
    public void actionPerformed(ActionEvent e) {
        int year = Integer.parseInt(String.valueOf(table.getValueAt(0, 0)));
        int month = Integer.parseInt(String.valueOf(table.getValueAt(0, 1)));
        int day = Integer.parseInt(String.valueOf(table.getValueAt(0, 2)));
        double amount = Double.parseDouble(String.valueOf(table.getValueAt(0, 3)));
        String category = String.valueOf(table.getValueAt(0, 4));
        Transaction t = constructTransaction(year, month, day, amount, category);

        transactionRecorder.removeTable();
        try {
            statement.addTransaction(t);
        } catch (MonthDateOutOfRange monthDateOutOfRange) {
            monthDateOutOfRange.printStackTrace();


        }
        transactionRecorder.fillTableModel(statement.getTransactions());
    }

    // EFFECTS: constructs a new Income or Cost
    public abstract Transaction constructTransaction(int year, int month, int day, double amount, String category);

}
