package lab4.A;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class WordTest {
    private Word word;

    @BeforeEach
    void setUp() {
        word = new Word("Тест");
    }

    @Test
    void testWordCreation() {
        assertEquals("Тест", word.getContent());
    }

    @Test
    void testWordEquality() {
        Word sameWord = new Word("Тест");
        Word differentWord = new Word("Другой");

        assertEquals(word, sameWord);
        assertNotEquals(word, differentWord);
    }

    @Test
    void testWordToString() {
        assertEquals("Тест", word.toString());
    }
}