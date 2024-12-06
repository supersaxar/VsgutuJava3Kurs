package DorzhievZhargalB7621.B;

import java.util.*;
import java.util.stream.Collectors;

public class TextProcessor {

    public static void main(String[] args) {
        String text = "The quick brown fox jumps over the lazy dog. The dog was not amused by the quick brown fox. Why was the quick fox jumping over the lazy dog anyway? The quick brown fox seemed to enjoy teasing the lazy dog. In the end, the dog decided to chase the quick brown fox. The quick fox ran away from the lazy dog as fast as it could. This story is about a quick brown fox and a lazy dog.\n";
        text = text.replaceAll("\\t| +", " ");

        List<Paragraph> paragraphs = Arrays.stream(text.split("\n\n"))
                .map(String::trim)
                .map(Paragraph::new)
                .collect(Collectors.toList());

        int maxSentences = findMaxSentencesWithCommonWords(paragraphs);
        System.out.println("Maximum number of sentences with common words: " + maxSentences);
    }

    public static int findMaxSentencesWithCommonWords(List<Paragraph> paragraphs) {
        Map<String, Set<Integer>> wordToSentenceMap = new HashMap<>();
        int sentenceIndex = 0;

        for (Paragraph paragraph : paragraphs) {
            for (Sentence sentence : paragraph.getSentences()) {
                for (Word word : sentence.getWords()) {
                    String wordStr = word.toString().toLowerCase();
                    wordToSentenceMap.computeIfAbsent(wordStr, k -> new HashSet<>()).add(sentenceIndex);
                }
                sentenceIndex++;
            }
        }

        return (int) wordToSentenceMap.values().stream()
                .filter(set -> set.size() > 1)
                .flatMap(Set::stream)
                .distinct()
                .count();
    }
}