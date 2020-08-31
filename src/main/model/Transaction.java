package model;

import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;

// Represent a Transaction having an year, month, date, amount (in dollars) , type ,and category
public abstract class Transaction implements Saveable {
    protected int year;          // which year the transaction be made
    protected int month;         // which month the transaction be made
    protected int day;           // which date the transaction be made
    protected double amount;     // the amount of the transaction
    protected boolean type;      // true for income, and false for cost
    protected String category;   // category or description of the transaction

    // EFFECTS: construct a transaction, and assign year, month, date ,amount and category to it.
    public Transaction(int year, int month, int day, double amount, String category) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.amount = amount;
        this.category = category;

    }

    //getters
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return day;
    }

    public String getCategory() {
        return category;
    }

    public boolean getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    // COMMENTS: the hints of building this method come from
    //           https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(year);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(month);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(day);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(amount);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(category);
        printWriter.print(Reader.DELIMITER);
        printWriter.println(type);

    }


}
