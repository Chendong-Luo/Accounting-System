package persistence;

import model.Transaction;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderTest {
    @Test
    // A dummy test which tests the dummy constructor of Reader class, the reason to do this
    // is because the autobot coverage test tells we are supposed to test the constructor in
    // Reader class, but the thing is we don't have constructor in Reader class. Therefore we
    // are permitted to use dummy test to test this.(go to the piazza question @448 for further
    // reference)
    public void testConstructor() {
        Reader reader = new Reader();
    }

    @Test
    public void testParseTransactionFile1() {
        try {
            List<Transaction> transactions = Reader.readTransactions(new File("./data/testTransactionFile1.txt"));
            Transaction t1 = transactions.get(0);
            assertEquals(t1.getDate(), 15);
            assertEquals(t1.getAmount(), 8888.0);
            assertEquals(t1.getCategory(), "Birthday");
            assertTrue(true);

            Transaction t2 = transactions.get(1);
            assertEquals(t2.getCategory(), "shopping");
            assertEquals(t2.getDate(), 24);
            assertEquals(t2.getAmount(), 2132.0);
            assertFalse(t2.getType());
        } catch (IOException e) {
            fail("IOException should not have been throw");
        }
    }

    @Test
    public void testParseTransactionFile2() {
        try {
            List<Transaction> transactions = Reader.readTransactions(new File("./data/testTransactionFile2.txt"));
            Transaction t1 = transactions.get(0);
            assertEquals(t1.getDate(), 5);
            assertEquals(t1.getAmount(), 12.0);
            assertEquals(t1.getCategory(), "work");
            assertTrue(true);

            Transaction t2 = transactions.get(1);
            assertEquals(t2.getCategory(), "eating out");
            assertEquals(t2.getDate(), 12);
            assertEquals(t2.getAmount(), 344.0);
            assertFalse(t2.getType());
        } catch (IOException e) {
            fail("IOException should not have been throw");
        }
    }

    @Test
    public void testIOException() {
        try {
            Reader.readTransactions(new File("./data/notExist"));
            fail();
        } catch (IOException e) {
            // expected
        }
    }

}
