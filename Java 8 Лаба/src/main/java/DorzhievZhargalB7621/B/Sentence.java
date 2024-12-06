package DorzhievZhargalB7621.B;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Sentence {
    private final List<Object> components;

    public Sentence(String sentence) {
        this.components = new ArrayList<>();
        String[] tokens = sentence.split("(?=\\W)|(?<=\\W)");
        for (String token : tokens) {
            if (token.matches("\\w+")) {
                components.add(new Word(token));
            } else {
                components.add(new Punctuation(token));
            }
        }
    }

    public List<Word> getWords() {
        return components.stream()
                .filter(c -> c instanceof Word)
                .map(c -> (Word) c)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return components.stream().map(Object::toString).collect(Collectors.joining());
    }
}