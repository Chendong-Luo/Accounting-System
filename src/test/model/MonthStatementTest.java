package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonthStatementTest {
    private MonthStatement monthStatement;
    private Transaction t1;
    private Transaction t2;
    private Transaction t3;
    private Transaction t4;
    private Transaction t5;

    @BeforeEach
    public void runBefore() {
        monthStatement = new MonthStatement(2020, 1);
        t1 = new Income(2020, 1, 21, 100, "work");
        t2 = new Income(2020, 1, 22, 230, "allowance");
        t3 = new Cost(2020, 1, 26, 123, "study");
        t4 = new Cost(2020, 1, 27, 134, "study");
        t5 = new Cost(2020, 1, 21, 1000, "study");
    }


    @Test
    public void testGetters() {
        assertEquals(monthStatement.getYear(), 2020);
        assertEquals(monthStatement.getMonth(), 1);
        monthStatement.addTransaction(t1);
        assertEquals(monthStatement.getTransactions().size(), 1);

    }

    @Test
    public void testConstructor() {
        assertEquals(monthStatement.getYear(), 2020);
        assertEquals(monthStatement.getMonth(), 1);
        assertEquals(monthStatement.getSize(), 0);

    }

    @Test
    public void testAddTransactionWithEmptyTransactions() {
        assertEquals(monthStatement.getSize(), 0);
        monthStatement.addTransaction(t1);
        assertEquals(monthStatement.getSize(), 1);
    }

    @Test
    public void testAddTransactionWithSomeEntriesInTransactions() {
        assertEquals(monthStatement.getSize(), 0);
        monthStatement.addTransaction(t1);
        monthStatement.addTransaction(t2);
        monthStatement.addTransaction(t3);
        assertEquals(monthStatement.getSize(), 3);
        monthStatement.addTransaction(t4);
        assertEquals(monthStatement.getSize(), 4);
    }

    @Test
    public void testSumIncomeAndSumCostWithEmptyTransactions() {
        double income = monthStatement.sumIncome();
        double cost = monthStatement.sumCost();
        assertEquals(income, 0);
        assertEquals(cost, 0);

    }

    @Test
    public void testSumIncomeAndSumCostWithSomeEntriesInTransactions() {
        monthStatement.addTransaction(t1);
        monthStatement.addTransaction(t2);
        monthStatement.addTransaction(t3);
        monthStatement.addTransaction(t4);
        monthStatement.addTransaction(t5);
        double cost = monthStatement.sumCost();
        double income = monthStatement.sumIncome();
        assertEquals(cost, 1257);
        assertEquals(income, 330);

    }


}
