package DorzhievZhargalB7621.B;

import java.util.List;
import java.util.stream.Collectors;

class Word {
    private final List<Symbol> symbols;

    public Word(String word) {
        this.symbols = word.chars().mapToObj(c -> new Symbol((char) c)).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return symbols.stream().map(s -> String.valueOf(s.getValue())).collect(Collectors.joining());
    }
}
