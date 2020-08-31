package model;

import java.util.LinkedList;

// Represent a YearStatement having a year, list of transaction and the sum of income and cost values
public class YearStatement {
    private final int year;                              // the year of statement referring to
    private final LinkedList<Transaction> transactions;  // the list of transaction in same year


    /*
     * EFFECTS: construct a YearStatement with zero costSum,
     *          incomeSum and a list of transaction, and assign
     *          the year with the values of passed year and month
     */
    public YearStatement(int year) {
        this.year = year;
        transactions = new LinkedList<>();
    }

    // getters
    public int getYear() {
        return year;
    }


    public LinkedList<Transaction> getTransactions() {
        return transactions;
    }

    public int getSize() {
        return transactions.size();
    }


    // MODIFIES: this
    // EFFECTS: add the transaction at the end of the list of transactions
    public void addTransaction(Transaction t) {
        transactions.addLast(t);
    }

    /*
     * MODIFIES: this
     * EFFECTS: sum up the income in the list of transaction,
     *          and return it
     */

    public Double sumIncome() {
        double incomeSum = 0.0;
        for (Transaction t : transactions) {
            if (t.type) {
                incomeSum += t.amount;
            }

        }
        return incomeSum;
    }

    /*
     * MODIFIES: this
     * EFFECTS: sum up the cost in the list of transaction,
     *          and return it
     */
    public Double sumCost() {
        double costSum = 0.0;
        for (Transaction t: transactions) {
            if (!t.type) {
                costSum += t.amount;
            }
        }
        return costSum;
    }

}
