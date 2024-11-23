package lab4.A;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Text {
    private String title;
    private List<Sentence> sentences;

    public Text(String title) {
        this.title = title;
        this.sentences = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void addSentence(Sentence sentence) {
        sentences.add(sentence);
    }

    public void appendText(String text) {
        String[] sentenceStrings = text.split("(?<=[.!?])\\s*");
        for (String s : sentenceStrings) {
            if (!s.trim().isEmpty()) {
                sentences.add(new Sentence(s.trim()));
            }
        }
    }

    public void printText() {
        System.out.println("Заголовок: " + title);
        System.out.println("Текст:");
        sentences.forEach(sentence -> System.out.println(sentence.toString()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text = (Text) o;
        return Objects.equals(title, text.title) &&
                Objects.equals(sentences, text.sentences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, sentences);
    }

    @Override
    public String toString() {
        return "Текст{" +
                "заголовок='" + title + '\'' +
                ", предложения=" + sentences +
                '}';
    }
}