package model;

import java.util.LinkedList;
import java.util.List;

// Represent a statement object having a list of transaction
public class Statement {
    private final LinkedList<Transaction> transactions;   // a lost of transaction

    // EFFECTS: construct a statement with a list of transaction
    public Statement() {
        transactions = new LinkedList<>();
    }

    // getters
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public int getSize() {
        return transactions.size();
    }


    // MODIFIES: this
    // EFFECTS: add a transaction at the end of the list of transactions
    public void addTransaction(Transaction t) throws MonthDateOutOfRange {
        if (t.getMonth() < 1 || t.getMonth() > 12 || t.getDate() < 1 || t.getDate() > 31) {
            throw new MonthDateOutOfRange();
        }
        transactions.addLast(t);
    }

    /*
     * MODIFIES: this
     * EFFECTS: create a new YearStatement based on the year passed in
     *          and search and put all transaction in same year into it,
     *          and sum up income and cost in yearStatement separately and
     *          record values, and finally return it
     */
    public YearStatement searchYear(int year) {
        YearStatement ys = new YearStatement(year);
        for (Transaction t : transactions) {
            if (year == t.year) {
                ys.addTransaction(t);
            }
        }

        return ys;
    }

    /*
     * MODIFIES: this
     * EFFECTS: create a new MothStatement based on the year and month
     *          passed in and search and put all transaction in same year
     *          and month into it from lowest date to highest date, and
     *          sum up income and cost in yearStatement separately and
     *          record values, and finally return it
     */
    public MonthStatement searchMonth(int year, int month)  {
        MonthStatement ms = new MonthStatement(year, month);
        for (int i = 1; i <= 31; i++) {
            for (Transaction t : transactions) {
                if (year == t.year && month == t.month && t.day == i) {
                    ms.addTransaction(t);
                }
            }
        }

        return ms;
    }

    // MODIFIES: this
    // EFFECTS: remove a transaction in the transactions
    public void removeTransaction(Transaction t) {
        transactions.remove(t);
    }

}








