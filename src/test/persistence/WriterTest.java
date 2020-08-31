package persistence;

import model.Cost;
import model.Income;
import model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
    private static final String TEST_FILE = "./data/testTransaction.txt";
    private Writer testWriter;
    private Transaction incomeSample;
    private Transaction costSample;


    @BeforeEach
    public void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        incomeSample = new Income(2000, 1, 5, 8888, "Birthday");
        costSample = new Cost(2020, 1, 3, 1000, "gift");
    }

    @Test
    public void testWriteTransactions() {
        testWriter.write(incomeSample);
        testWriter.write(costSample);
        testWriter.close();

        try {
            List<Transaction> transactions = Reader.readTransactions(new File(TEST_FILE));
            Transaction t1 = transactions.get(0);
            assertEquals(t1.getDate(), 5);
            assertEquals(t1.getCategory(), "Birthday");
            assertEquals(t1.getAmount(), 8888);
            assertEquals(t1.getType(), true);

            Transaction t2 = transactions.get(1);
            assertEquals(t2.getDate(), 3);
            assertEquals(t2.getAmount(), 1000);
            assertEquals(t2.getType(), false);
            assertEquals(t2.getCategory(), "gift");
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
