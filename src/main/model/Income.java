package model;

// Represent an income object having a year, month, day, amount (in dollars), category, and type
public class Income extends Transaction {


    /*
     * EFFECTS: construct an Income object with a true type,
     *          and assign a year, month, day, amount (in dollars), and
     *          category to it
     */
    public Income(int year, int month, int day, double amount, String incomeCategory) {
        super(year, month, day, amount, incomeCategory);
        type = true;

    }


}
