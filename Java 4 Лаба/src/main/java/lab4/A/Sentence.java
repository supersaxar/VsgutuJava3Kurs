package lab4.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class Sentence {
    private List<Word> words;
    private String punctuation;

    public Sentence() {
        this.words = new ArrayList<>();
        this.punctuation = ".";
    }

    public Sentence(String sentence) {
        this.punctuation = sentence.matches(".*[.!?]$")
                ? sentence.substring(sentence.length() - 1)
                : ".";

        sentence = sentence.replaceAll("[.!?]$", "");

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
        return Objects.equals(words, sentence.words) &&
                Objects.equals(punctuation, sentence.punctuation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(words, punctuation);
    }

    @Override
    public String toString() {
        return words.stream()
                .map(Word::toString)
                .collect(Collectors.joining(" ")) + punctuation;
    }
}
