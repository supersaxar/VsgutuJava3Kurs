package lab4.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class Sentence {
    private List<Word> words;

    public Sentence() {
        this.words = new ArrayList<>();
    }

    public Sentence(String sentence) {
        this.words = Arrays.stream(sentence.split("\\s+"))
                .map(Word::new)
                .collect(Collectors.toList());
    }

    public void addWord(Word word) {
        words.add(word);
    }

    public List<Word> getWords() {
        return words;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(words, sentence.words);
    }

    @Override
    public int hashCode() {
        return Objects.hash(words);
    }

    @Override
    public String toString() {
        return words.stream()
                .map(Word::toString)
                .collect(Collectors.joining(" ")) + ".";
    }
}