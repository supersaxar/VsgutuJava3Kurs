package DorzhievZhargalB7621.A;

public class TextProcessor {
    public static String replaceKthLetter(String text, int k, char replacement) {
        if (k <= 0 || text == null || text.isEmpty()) {
            return text;
        }

        StringBuilder result = new StringBuilder();

        for (String word : text.split(" ")) {
            if (word.length() >= k) {
                result.append(word.substring(0, k - 1))
                        .append(replacement)
                        .append(word.substring(k));
            } else {
                result.append(word);
            }
            result.append(" ");
        }

        return result.toString().trim();
    }

    public static void main(String[] args) {
        String inputText = "hello world";
        int k = 2;
        char replacementChar = 'X';

        String result = replaceKthLetter(inputText, k, replacementChar);
        System.out.println("Original text: " + inputText);
        System.out.println("Modified text: " + result);
    }
}