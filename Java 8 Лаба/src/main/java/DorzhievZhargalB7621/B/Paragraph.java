package DorzhievZhargalB7621.B;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Paragraph {
    private final List<Sentence> sentences;

    public Paragraph(String paragraph) {
        sentences = Arrays.stream(paragraph.split("(?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)(?<=\\.|\\?|\\!)(\s)"))
                .map(String::trim)
                .map(Sentence::new)
                .collect(Collectors.toList());
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    @Override
    public String toString() {
        return sentences.stream().map(Sentence::toString).collect(Collectors.joining(" "));
    }
}
