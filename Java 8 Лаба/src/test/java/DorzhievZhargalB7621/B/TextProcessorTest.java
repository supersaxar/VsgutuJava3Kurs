package DorzhievZhargalB7621.B;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TextProcessorTest {

    @Test
    public void testWordParsing() {
        Word word = new Word("example");
        assertEquals("example", word.toString());
    }

    @Test
    public void testSentenceParsing() {
        Sentence sentence = new Sentence("Hello, world!");
        assertEquals("Hello, world!", sentence.toString());
        assertEquals(2, sentence.getWords().size());
    }

    @Test
    public void testParagraphParsing() {
        Paragraph paragraph = new Paragraph("Hello, world! How are you?");
        assertEquals(2, paragraph.getSentences().size());
    }

    @Test
    public void testFindMaxSentencesWithCommonWords() {
        String text = "Hello world. World peace. Hello everyone.";
        List<Paragraph> paragraphs = List.of(new Paragraph(text));
        int maxSentences = TextProcessor.findMaxSentencesWithCommonWords(paragraphs);
        assertEquals(3, maxSentences);
    }
}