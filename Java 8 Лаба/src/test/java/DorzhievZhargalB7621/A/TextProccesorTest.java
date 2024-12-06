package DorzhievZhargalB7621.A;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextProcessorTest {

    @Test
    void replaceKthLetter_regularCase() {
        String text = "hello world";
        int k = 2;
        char replacement = 'X';
        String expected = "hXllo wXrld";
        assertEquals(expected, TextProcessor.replaceKthLetter(text, k, replacement));
    }

    @Test
    void replaceKthLetter_kTooLarge() {
        String text = "hello world";
        int k = 10;
        char replacement = 'X';
        String expected = "hello world";
        assertEquals(expected, TextProcessor.replaceKthLetter(text, k, replacement));
    }

    @Test
    void replaceKthLetter_kIsOne() {
        String text = "hello world";
        int k = 1;
        char replacement = 'X';
        String expected = "Xello Xorld";
        assertEquals(expected, TextProcessor.replaceKthLetter(text, k, replacement));
    }

    @Test
    void replaceKthLetter_emptyText() {
        String text = "";
        int k = 2;
        char replacement = 'X';
        String expected = "";
        assertEquals(expected, TextProcessor.replaceKthLetter(text, k, replacement));
    }

    @Test
    void replaceKthLetter_nullText() {
        String text = null;
        int k = 2;
        char replacement = 'X';
        assertNull(TextProcessor.replaceKthLetter(text, k, replacement));
    }

    @Test
    void replaceKthLetter_kIsZero() {
        String text = "hello world";
        int k = 0;
        char replacement = 'X';
        String expected = "hello world";
        assertEquals(expected, TextProcessor.replaceKthLetter(text, k, replacement));
    }
}
