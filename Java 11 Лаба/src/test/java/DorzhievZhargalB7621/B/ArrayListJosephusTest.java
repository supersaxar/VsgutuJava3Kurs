package DorzhievZhargalB7621.B;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayListJosephusTest {
    @Test
    public void testValidInput() {
        assertEquals(1, JosephusProblem.solveWithArrayList(1));
        assertEquals(1, JosephusProblem.solveWithArrayList(8));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInput() {
        JosephusProblem.solveWithArrayList(0);
    }
}