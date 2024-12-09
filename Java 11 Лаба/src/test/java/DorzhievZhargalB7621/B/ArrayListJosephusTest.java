package DorzhievZhargalB7621.B;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class ArrayListJosephusTest {
    @Test
    public void testValidInput() {
        ArrayList<Integer> arrayListCircle = new ArrayList<>();
        arrayListCircle.add(1);
        assertEquals(1, JosephusProblem.solveJosephusProblem(arrayListCircle));

        arrayListCircle.clear();
        for (int i = 1; i <= 8; i++) {
            arrayListCircle.add(i);
        }
        assertEquals(1, JosephusProblem.solveJosephusProblem(arrayListCircle));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInput() {
        ArrayList<Integer> emptyList = new ArrayList<>();
        JosephusProblem.solveJosephusProblem(emptyList);
    }
}
