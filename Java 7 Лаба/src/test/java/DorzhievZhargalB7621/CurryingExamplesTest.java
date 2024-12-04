package DorzhievZhargalB7621;

import org.junit.Test;
import static org.junit.Assert.*;

public class CurryingExamplesTest {

    @Test
    public void testAdd() {
        assertEquals(5, (int) CurryingExamples.add.apply(2).apply(3));
        assertEquals(10, (int) CurryingExamples.add.apply(4).apply(6));
    }

    @Test
    public void testMatchesRegex() {
        assertTrue(CurryingExamples.matchesRegex.apply("\\d+").apply("1234"));
        assertFalse(CurryingExamples.matchesRegex.apply("\\d+").apply("abcd"));
    }

    @Test
    public void testSplitByRegex() {
        String[] result = CurryingExamples.splitByRegex.apply(",").apply("a,b,c");
        assertArrayEquals(new String[]{"a", "b", "c"}, result);
    }
}
