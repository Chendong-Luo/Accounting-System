package ui;


import model.MonthDateOutOfRange;
import model.Statement;
import model.Transaction;
import persistence.Reader;
import persistence.Writer;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.List;


import static ui.PlayButtonMusic.*;

// Represent a transactionRecorder app
public class TransactionRecorder implements ActionListener {


    private JFrame frame;

    private JTable table;

    private JOptionPane popup = new JOptionPane();

    private JScrollPane scrollPane;

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("Click here to add");
    private JMenuItem ai = new JMenuItem("Add Income");
    private JMenuItem ac = new JMenuItem("Add Cost");

    private JLabel space = new JLabel("   ");

    private JPanel panelButton;
    private JPanel panelTable;

    private JButton reload;
    private JButton delete;
    private JButton showYearBill;
    private JButton showMonthBill;
    private JButton save;
    private JButton quit;
    private JButton back;

    private Statement statement = new Statement();

    String[] columnNames = {"Number", "Type", "Year", "Month", "Date", "Amount", "Description"};
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

    // EFFECTS: set up basic elements for gui
    public TransactionRecorder() {
        super();
        setupObjects();
        setupActionToButtons();
        setupPanels();
        setupMenu();
        setupFrame();

        new PlayButtonMusic(TINYBUTTON_FILE);
        popup.showMessageDialog(frame, "Click the Reload button before you do any operation, "
                + "or you may lose the previous data saved.");


    }

    // EFFECTS: initializes objects of basic elements for gui
    public void setupObjects() {
        frame = new JFrame();
        table = new JTable(tableModel);
        panelButton = new JPanel();
        panelTable = new JPanel();
        scrollPane = new JScrollPane(table);
        reload = new JButton("Reload");
        delete = new JButton("Delete");
        showMonthBill = new JButton("Month Bill");
        showYearBill = new JButton("Year Bill");
        save = new JButton("SaveQuit");
        quit = new JButton("Quit");
        back = new JButton("Back");
    }

    // EFFECTS: set up actions to all buttons
    public void setupActionToButtons() {
        reload.addActionListener(this);
        reload.setActionCommand("Reload");
        showMonthBill.addActionListener(this);
        showMonthBill.setActionCommand("ShowMonthBill");
        showYearBill.addActionListener(this);
        showYearBill.setActionCommand("ShowYearBill");
        save.addActionListener(this);
        save.setActionCommand("Save");
        quit.addActionListener(this);
        quit.setActionCommand("Quit");
        delete.addActionListener(this);
        delete.setActionCommand("Delete");
        back.addActionListener(this);
        back.setActionCommand("Back");
        back.setVisible(false);
    }

    // EFFECTS: set up panels
    public void setupPanels() {
        panelButton.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panelButton.setLayout(new GridLayout(1, 0));
        panelButton.add(reload);
        panelButton.add(space);
        panelButton.add(showMonthBill);
        panelButton.add(showYearBill);
        panelButton.add(delete);
        panelButton.add(back);
        panelButton.add(save);
        panelButton.add(quit);

        panelTable.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panelTable.setLayout(new GridLayout(1, 0));

    }

    // EFFECTS: set up the menu bar
    public void setupMenu() {
        ac.addActionListener(this);
        ac.setActionCommand("AddCost");
        ai.addActionListener(this);
        ai.setActionCommand("AddIncome");
        menu.add(ac);
        menu.add(ai);
        menuBar.add(menu);
    }

    // EFFECTS: set up the frame
    public void setupFrame() {
        frame.setJMenuBar(menuBar);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panelButton, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Transaction Recorder");
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 500);
    }

    // MODIFIES: this
    // EFFECTS: performs different actions when different buttons clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("AddIncome")) {
            addIncomeAction();
        } else if (e.getActionCommand().equals("AddCost")) {
            addCostAction();
        } else if (e.getActionCommand().equals("Reload")) {
            reloadAction();
        } else if (e.getActionCommand().equals("ShowMonthBill")) {
            showMonthBillAction();
        } else if (e.getActionCommand().equals("Delete")) {
            deleteAction();
        } else if (e.getActionCommand().equals("ShowYearBill")) {
            showYearBillAction();
        } else if (e.getActionCommand().equals("Back")) {
            backAction();
        } else if (e.getActionCommand().equals("Save")) {
            saveAction();
        } else if (e.getActionCommand().equals("Quit")) {
            quitAction();
        }
    }

    // MODIFIES: this
    // EFFECTS: performs the addIncome action
    public void addIncomeAction() {
        new PlayButtonMusic(BUTTON_FILE);
        reload.setVisible(false);
        new AddIncome(statement, this);
    }

    // MODIFIES: this
    // EFFECTS: performs the addCost action
    public void addCostAction() {
        new PlayButtonMusic(BUTTON_FILE);
        reload.setVisible(false);
        new AddCost(statement, this);
    }

    // MODIFIES: this
    // EFFECTS: performs the reload action
    public void reloadAction() {
        new PlayButtonMusic(DISCORDJOIN_FILE);
        loadTransactions();
        fillTableModel(statement.getTransactions());
        reload.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: performs the showMonthBill action
    public void showMonthBillAction() {
        new PlayButtonMusic(BUTTON_FILE);
        new MonthBill(this);
        reload.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: performs the delete action
    public void deleteAction() {
        new PlayButtonMusic(BUTTON_FILE);
        boolean isSelected = false;
        for (int i = 0; i < table.getRowCount(); i++) {
            boolean isSingleSelected = table.isRowSelected(i);
            isSelected = isSingleSelected || isSelected;

        }
        if (!isSelected) {
            new PlayButtonMusic(ERRO_FILE);
            popup.showMessageDialog(frame, "please click and select one row in the table and delete!");
        } else {
            int number = table.getSelectedRow();
            Transaction transactionDeleted = statement.getTransactions().get(number);
            removeTable();
            statement.removeTransaction(transactionDeleted);
            fillTableModel(statement.getTransactions());
        }
    }

    // MODIFIES: this
    // EFFECTS: performs the showYearBill action
    public void showYearBillAction() {
        new PlayButtonMusic(BUTTON_FILE);
        reload.setVisible(false);
        new YearBill(this);
    }

    // MODIFIES: this
    // EFFECTS: performs the back action
    public void backAction() {
        new PlayButtonMusic(DISCORDJOIN_FILE);
        menuBar.setVisible(true);
        delete.setVisible(true);
        quit.setVisible(true);
        save.setVisible(true);
        showMonthBill.setVisible(true);
        showYearBill.setVisible(true);
        back.setVisible(false);
        int count = tableModel.getRowCount();

        for (int i = 0; i < count; i++) {
            tableModel.removeRow(0);
        }
        fillTableModel(statement.getTransactions());
    }

    // MODIFIES: this
    // EFFECTS: performs the save action
    public void saveAction() {
        new PlayButtonMusic(CSGOPLANT_FILE);
        saveTransaction();
        popup.showMessageDialog(frame, new JLabel(new ImageIcon("./data/nice.png")));
        frame.dispose();
    }

    // MODIFIES: this
    // EFFECTS: performs the quit action
    public void quitAction() {
        new PlayButtonMusic(MARIO_FILE);
        popup.showMessageDialog(frame, "Keep recording bills is a good habit.");
        frame.dispose();
    }


    // MODIFIES: this
    // EFFECTS: remove transactions from the table
    public void removeTable() {
        for (int i = 0; i < statement.getTransactions().size(); i++) {
            tableModel.removeRow(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads transactions from TRANSACTIONS_FILE, if that file exists;
    //          otherwise initializes transactions with default values
    // COMMENTS: the hints of building this method come from
    //           https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    private void loadTransactions() {
        try {

            List<Transaction> transactions = Reader.readTransactions(new File(TRANSACTIONS_FILE));
            for (Transaction t : transactions) {
                statement.addTransaction(t);
            }
        } catch (IOException e) {
            statement = new Statement();
        } catch (MonthDateOutOfRange monthDateOutOfRange) {
            monthDateOutOfRange.printStackTrace();
        }
    }

    // EFFECTS: saves transactions in the statement to TRANSACTIONS_FILE
    // COMMENTS: the hints of building this method come from
    //           https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    private void saveTransaction() {
        try {
            Writer writer = new Writer(new File(TRANSACTIONS_FILE));
            for (Transaction t : statement.getTransactions()) {
                writer.write(t);
            }
            writer.close();
            System.out.println("Transactions saved to file " + TRANSACTIONS_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save Transactions to " + TRANSACTIONS_FILE);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    // MODIFIES: this
    // EFFECTS: print transactions on the table
    public void fillTableModel(List<Transaction> transactions) {
        int counter = 1;

        for (int i = 0; i < transactions.size(); i++) {
            int year = transactions.get(i).getYear();
            int month = transactions.get(i).getMonth();
            int date = transactions.get(i).getDate();
            double amount = transactions.get(i).getAmount();
            String description = transactions.get(i).getCategory();
            boolean type = transactions.get(i).getType();

            if (type) {
                Object[] dataIncome = {counter, "Income", year, month, date, amount, description};
                tableModel.addRow(dataIncome);
                counter += 1;
            } else {
                Object[] dataCost = {counter, "Cost", year, month, date, amount, description};
                tableModel.addRow(dataCost);
                counter += 1;
            }
        }
    }


    // getters
    public JButton getDelete() {
        return delete;
    }

    public JButton getQuit() {
        return quit;
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public JButton getSave() {
        return save;
    }

    public JButton getBack() {
        return back;
    }

    public JButton getShowMonthBill() {
        return showMonthBill;
    }

    public JButton getShowYearBill() {
        return showYearBill;
    }

    public Statement getStatement() {
        return statement;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }


}
