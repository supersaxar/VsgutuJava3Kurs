package DorzhievZhargalB7621.B;

import org.junit.Test;
import static org.junit.Assert.*;

public class LinkedListJosephusTest {
    @Test
    public void testValidInput() {
        assertEquals(1, JosephusProblem.solveWithLinkedList(1));
        assertEquals(1, JosephusProblem.solveWithLinkedList(8));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInput() {
        JosephusProblem.solveWithLinkedList(0);
    }
}