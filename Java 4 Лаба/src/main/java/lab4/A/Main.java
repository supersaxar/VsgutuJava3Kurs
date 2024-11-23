package lab4.A;

public class Main {
    public static void main(String[] args) {
        Text text = new Text("Пример текста");

        Sentence sentence1 = new Sentence("Это первое предложение");
        Sentence sentence2 = new Sentence("Это второе предложение");

        text.addSentence(sentence1);
        text.addSentence(sentence2);

        text.appendText("Это третье предложение. А это четвертое!");

        text.printText();
    }
}