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

        String expected = """
                Заголовок: Тестовый заголовок
                Текст:
                Тестовое предложение.""";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        text.printText();

        System.setOut(originalOut);

        assertEquals(expected, outContent.toString().trim());
    }

    @Test
    void testAppendText() {
        text.appendText("Первое предложение. Второе предложение!");

        String expected = """
                Заголовок: Тестовый заголовок
                Текст:
                Первое предложение.
                Второе предложение!""";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        text.printText();

        System.setOut(originalOut);

        assertEquals(expected, outContent.toString().trim());
    }

    @Test
    void testTextEquality() {
        Text sameText = new Text("Тестовый заголовок");
        Text differentText = new Text("Другой заголовок");

        assertEquals(text, sameText);
        assertNotEquals(text, differentText);
    }
}