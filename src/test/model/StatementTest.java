package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StatementTest {
    private Statement statement;
    private Transaction t1;
    private Transaction t2;
    private Transaction t3;
    private Transaction t4;
    private Transaction t5;
    private Transaction t6;
    private Transaction t7;
    private Transaction t8;
    private Transaction t9;
    private Transaction t10;
    private Transaction t11;

    @BeforeEach
    public void runBefore() {
        statement = new Statement();
        t1 = new Income(2018, 1, 21, 100, "work");
        t2 = new Income(2019, 2, 22, 230, "allowance");
        t3 = new Cost(2020, 1, 26, 123, "study");
        t4 = new Cost(2017, 1, 27, 134, "study");
        t5 = new Income(2020, 5, 21, 1000, "study");
        t6 = new Cost(2020, 5, 26, 123, "study");
        t7 = new Income(2020, 1, 26, 123, "study");
        t8 = new Income(2020, 0, 26, 123, "partTime");
        t9 = new Cost(2020, 31, 25, 123, "study");
        t10 = new Cost(2020, 1, -12, 123, "study");
        t11 = new Cost(2020, 1, 43, 123, "study");


    }


    @Test
    public void testConstructor() {
        assertEquals(statement.getSize(), 0);
    }

    @Test
    public void testGetters() {
        assertEquals(statement.getSize(), 0);
        assertEquals(statement.getTransactions().size(), 0);

    }

    @Test
    public void testAddTransactionWithEmptyTransactions() {
        assertEquals(statement.getSize(), 0);
        try {
            statement.addTransaction(t1);
        } catch (MonthDateOutOfRange monthDateOutOfRange) {
            fail();
        }
        assertEquals(statement.getSize(), 1);

    }

    @Test
    public void testAddTransactionWithTransactions() {
        assertEquals(statement.getSize(), 0);
        try {
            statement.addTransaction(t1);
        } catch (MonthDateOutOfRange monthDateOutOfRange) {
            fail();
        }
        try {
            statement.addTransaction(t2);
        } catch (MonthDateOutOfRange monthDateOutOfRange) {
            fail();
        }
        assertEquals(statement.getSize(), 2);
        try {
            statement.addTransaction(t3);
        } catch (MonthDateOutOfRange monthDateOutOfRange) {
            fail();
        }
        assertEquals(statement.getSize(), 3);
    }

    @Test
    public void testSearchYearWithEmptyTransactions() {
        assertEquals(statement.getSize(), 0);


    }

    @Test
    public void testSearchYearWithSomeEntriesInTransactions() {
        assertEquals(statement.getSize(), 0);
        try {
            statement.addTransaction(t1);
            statement.addTransaction(t2);
            statement.addTransaction(t3);
            statement.addTransaction(t4);
            statement.addTransaction(t5);
        } catch (MonthDateOutOfRange monthDateOutOfRange) {
            fail();
        }
        assertEquals(statement.getSize(), 5);
        assertEquals(statement.searchYear(2020).getSize(), 2);
        assertEquals(statement.searchYear(2000).getSize(), 0);
        assertEquals(statement.searchYear(2017).getSize(), 1);

    }

    @Test
    public void testSearchMonthWithEmptyTransactions() {
        assertEquals(statement.searchMonth(2020, 1).getSize(), 0);
    }

    @Test
    public void testSearchMonthWithSomeEntriesInTransactions() {
        assertEquals(statement.getSize(), 0);
        try {
            statement.addTransaction(t1);
            statement.addTransaction(t2);
            statement.addTransaction(t3);
            statement.addTransaction(t4);
            statement.addTransaction(t5);
            statement.addTransaction(t6);
            statement.addTransaction(t7);
        } catch (MonthDateOutOfRange monthDateOutOfRange) {
            fail();
        }

        assertEquals(statement.getSize(), 7);
        assertEquals(statement.searchMonth(2023, 1).getSize(), 0);
        assertEquals(statement.searchMonth(2020, 1).getSize(), 2);
        assertEquals(statement.searchMonth(2020, 5).getSize(), 2);
    }

    @Test
    public void testRemoveTransactionInEmptyList() {
        assertEquals(statement.getSize(), 0);
        statement.removeTransaction(t1);
        assertEquals(statement.getSize(), 0);

    }

    @Test
    public void testRemoveTransactionInNotEmptyList() {
        try {
            statement.addTransaction(t1);
            statement.addTransaction(t2);
            statement.addTransaction(t3);
            statement.removeTransaction(t4);
        } catch (MonthDateOutOfRange monthDateOutOfRange) {
            fail();
        }
        assertEquals(statement.getSize(), 3);
        statement.removeTransaction(t3);
        assertEquals(statement.getSize(), 2);
    }

    @Test
    public void testAddOneTransactionExceptionNotExpected() {
        assertEquals(statement.getSize(), 0);
        try {
            statement.addTransaction(t1);
        } catch (MonthDateOutOfRange monthDateOutOfRange) {
            fail();
        }
        assertEquals(statement.getSize(), 1);

    }

    @Test
    public void testAddMultipleTransactionExceptionNotExpected() {
        assertEquals(statement.getSize(), 0);
        try {
            statement.addTransaction(t1);
            statement.addTransaction(t2);
            statement.addTransaction(t3);
        } catch (MonthDateOutOfRange monthDateOutOfRange) {
            fail();
        }
        assertEquals(statement.getSize(), 3);
    }

    @Test
    public void testAddOneTransactionExceptionExpected() {
        assertEquals(statement.getSize(), 0);
        try {
            statement.addTransaction(t8);
            fail();
        } catch (MonthDateOutOfRange monthDateOutOfRange) {
            // Expected
        }
        assertEquals(statement.getSize(), 0);
        try {
            statement.addTransaction(t9);
            fail();
        } catch (MonthDateOutOfRange monthDateOutOfRange) {
            // Expected
        }
        assertEquals(statement.getSize(), 0);
        try {
            statement.addTransaction(t10);
            fail();
        } catch (MonthDateOutOfRange monthDateOutOfRange) {
            // Expected
        }
        assertEquals(statement.getSize(), 0);
        try {
            statement.addTransaction(t11);
            fail();
        } catch (MonthDateOutOfRange monthDateOutOfRange) {
            // Expected
        }
        assertEquals(statement.getSize(), 0);
    }

    @Test
    public void testAddMultipleTransactionExceptionExpected() {
        assertEquals(statement.getSize(), 0);
        try {
            statement.addTransaction(t1);
            statement.addTransaction(t2);
            statement.addTransaction(t9);
            fail();
        } catch (MonthDateOutOfRange monthDateOutOfRange) {
            // Excepected
        }
        assertEquals(statement.getSize(), 2);

    }
}

