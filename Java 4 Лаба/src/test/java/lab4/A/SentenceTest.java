package lab4.A;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SentenceTest {
    private Sentence sentence;

    @BeforeEach
    void setUp() {
        sentence = new Sentence("Это тестовое предложение");
    }

    @Test
    void testSentenceCreation() {
        assertEquals(3, sentence.getWords().size());
        assertEquals("Это", sentence.getWords().get(0).toString());
    }

    @Test
    void testAddWord() {
        Sentence sentence = new Sentence();
        sentence.addWord(new Word("Тест"));
        assertEquals(1, sentence.getWords().size());
    }

    @Test
    void testSentenceToString() {
        assertEquals("Это тестовое предложение.", sentence.toString());
    }

    @Test
    void testSentenceEquality() {
        Sentence sameSentence = new Sentence("Это тестовое предложение");
        Sentence differentSentence = new Sentence("Другое предложение");

        assertEquals(sentence, sameSentence);
        assertNotEquals(sentence, differentSentence);
    }
}