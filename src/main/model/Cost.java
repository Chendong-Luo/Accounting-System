package model;


// Represent a cost object having a year, month, day, amount (in dollars), category, and type
public class Cost extends Transaction {

    /*
     * EFFECTS: construct a cost object with a false type,
     *          and assign a year, month, day, amount (in dollars), and
     *          category to it
     */
    public Cost(int year, int month, int day, double amount, String incomeCategory) {
        super(year, month, day, amount, incomeCategory);
        type = false;
    }

}
