package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class YearStatementTest {
    private YearStatement yearStatement;
    private Transaction t1;
    private Transaction t2;
    private Transaction t3;
    private Transaction t4;
    private Transaction t5;

    @BeforeEach
    public void runBefore() {
        yearStatement = new YearStatement(2020);
        t1 = new Income(2020, 1, 21, 100, "work");
        t2 = new Income(2020, 5, 22, 230, "allowance");
        t3 = new Cost(2020, 1, 26, 123, "study");
        t4 = new Cost(2020, 3, 27, 134, "study");
        t5 = new Cost(2020, 4, 21, 1000, "study");

    }


    @Test
    public void testGetters() {
        assertEquals(yearStatement.getYear(), 2020);
        yearStatement.addTransaction(t1);
        assertEquals(yearStatement.getTransactions().size(), 1);

    }

    @Test
    public void testConstructor() {
        assertEquals(yearStatement.getYear(), 2020);

    }

    @Test
    public void testAddTransactionWithEmptyTransactions() {
        assertEquals(yearStatement.getSize(), 0);
        yearStatement.addTransaction(t1);
        assertEquals(yearStatement.getSize(), 1);
    }

    @Test
    public void testAddTransactionWithSomeEntriesInTransactions() {
        assertEquals(yearStatement.getSize(), 0);
        yearStatement.addTransaction(t1);
        yearStatement.addTransaction(t2);
        yearStatement.addTransaction(t3);
        assertEquals(yearStatement.getSize(), 3);
        yearStatement.addTransaction(t4);
        assertEquals(yearStatement.getSize(), 4);
    }

    @Test
    public void testSumIncomeAndSumCostWithEmptyTransactions() {
        double income = yearStatement.sumIncome();
        double cost = yearStatement.sumCost();
        assertEquals(income, 0);
        assertEquals(cost, 0);

    }

    @Test
    public void testSumIncomeAndSumCostWithSomeEntriesInTransactions() {
        yearStatement.addTransaction(t1);
        yearStatement.addTransaction(t2);
        yearStatement.addTransaction(t3);
        yearStatement.addTransaction(t4);
        yearStatement.addTransaction(t5);

        assertEquals(yearStatement.sumCost(), 1257);
        assertEquals(yearStatement.sumIncome(), 330);

    }
}
