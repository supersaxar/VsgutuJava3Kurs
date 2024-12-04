package DorzhievZhargalB7621;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Notepad {
    public class Note {
        private String title;
        private String content;

        public Note(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        @Override
        public String toString() {
            return "Note{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }

    private Map<LocalDate, List<Note>> notesMap;

    public Notepad() {
        this.notesMap = new HashMap<>();
    }

    public void addNote(LocalDate date, String title, String content) {
        Note newNote = new Note(title, content);
        notesMap.computeIfAbsent(date, k -> new ArrayList<>()).add(newNote);
    }

    public List<Note> getNotes(LocalDate date) {
        return notesMap.getOrDefault(date, new ArrayList<>());
    }

    public int getTotalNotesCount() {
        return notesMap.values().stream()
                .mapToInt(List::size)
                .sum();
    }

    public int getNotesCountForDate(LocalDate date) {
        return notesMap.getOrDefault(date, new ArrayList<>()).size();
    }
}