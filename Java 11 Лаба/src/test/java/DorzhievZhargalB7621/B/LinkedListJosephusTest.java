package DorzhievZhargalB7621.B;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class LinkedListJosephusTest {
    @Test
    public void testValidInput() {
        LinkedList<Integer> linkedListCircle = new LinkedList<>();
        linkedListCircle.add(1);
        assertEquals(1, JosephusProblem.solveJosephusProblem(linkedListCircle));

        linkedListCircle.clear();
        for (int i = 1; i <= 8; i++) {
            linkedListCircle.add(i);
        }
        assertEquals(1, JosephusProblem.solveJosephusProblem(linkedListCircle));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInput() {
        LinkedList<Integer> emptyList = new LinkedList<>();
        JosephusProblem.solveJosephusProblem(emptyList);
    }
}
