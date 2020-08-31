package model;

import java.util.LinkedList;

//  Represent a MonthStatement having a year, month, list of transaction and the sum of income and cost values
public class MonthStatement {
    private final int year;
    private final int month;
    private final LinkedList<Transaction> transactions;


    /*
     * EFFECTS: construct a MonthStatement with zero costSum,
     *          incomeSum and a list of transaction, and assign
     *          the year and month with values of passed year and
     *          month
     */
    public MonthStatement(int year, int month) {
        this.year = year;
        this.month = month;
        transactions = new LinkedList<>();

    }

    // getters
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getSize() {
        return transactions.size();
    }

    public LinkedList<Transaction> getTransactions() {
        return transactions;
    }


    /*
     * MODIFIES: this
     * EFFECTS: add a transaction at the end of the transactions
     */
    public void addTransaction(Transaction t) {
        transactions.addLast(t);
    }

    /*
     * MODIFIES: this
     * EFFECTS: sum up the income in the list of transaction,
     *          return it
     */
    public double sumIncome() {
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
     *          return it
     */
    public double sumCost() {
        double costSum = 0.0;
        for (Transaction t : transactions) {
            if (!t.type) {
                costSum += t.amount;
            }
        }
        return costSum;
    }


}
