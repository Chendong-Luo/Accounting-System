package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CostTest {

    private Transaction t1;
    private Transaction t2;
    private Transaction t3;


    @BeforeEach
    public void runBefore() {
        t1 = new Cost(2017, 4, 26, 123, "study");
        t2 = new Cost(2000, 3, 27, 134, "study");
        t3 = new Cost(2020, 8, 21, 1000, "shopping");

    }

    @Test
    public void testConstructor() {
        assertEquals(t1.getYear(), 2017);
        assertEquals(t1.getMonth(), 4);
        assertEquals(t1.getType(), false);
        assertEquals(t2.getAmount(), 134);
        assertEquals(t3.getCategory(), "shopping");
        assertEquals(t1.getDate(), 26);
    }


}