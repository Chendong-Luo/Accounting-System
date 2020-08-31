package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncomeTest {
    private Transaction t1;
    private Transaction t2;
    private Transaction t3;


    @BeforeEach
    public void runBefore() {
        t1 = new Income(2017, 4, 26, 123, "study");
        t2 = new Income(2000, 3, 27, 134, "study");
        t3 = new Income(2020, 8, 21, 1000, "work");

    }

    @Test
    public void testConstructor() {
        assertEquals(t1.getType(), true);
        assertEquals(t2.getAmount(), 134);
        assertEquals(t3.getCategory(), "work");
        assertEquals(t1.getDate(), 26);
    }
}
