package lab4.A;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TextTest {
    private Text text;

    @BeforeEach
    void setUp() {
        text = new Text("Тестовый заголовок");
    }

    @Test
    void testTextCreation() {
        assertEquals("Тестовый заголовок", text.getTitle());
    }

    @Test
    void testAddSentence() {
        Sentence sentence = new Sentence("Тестовое предложение");
        text.addSentence(sentence);

        String expected = normalize("""
                Заголовок: Тестовый заголовок
                Текст:
                Тестовое предложение.""");
        
        String actual = captureSystemOut(() -> text.printText());

        assertEquals(expected, actual);
    }

    @Test
    void testAppendText() {
        text.appendText("Первое предложение. Второе предложение!");

        String expected = normalize("""
                Заголовок: Тестовый заголовок
                Текст:
                Первое предложение.
                Второе предложение!""");

        String actual = captureSystemOut(() -> text.printText());

        assertEquals(expected, actual);
    }

    @Test
    void testTextEquality() {
        Text sameText = new Text("Тестовый заголовок");
        Text differentText = new Text("Другой заголовок");

        assertEquals(text, sameText);
        assertNotEquals(text, differentText);
    }

    private String normalize(String input) {
        return input.replace("\r\n", "\n").trim();
    }

    private String captureSystemOut(Runnable action) {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(outContent));
            action.run();
            return normalize(outContent.toString());
        } finally {
            System.setOut(originalOut);
        }
    }
}
