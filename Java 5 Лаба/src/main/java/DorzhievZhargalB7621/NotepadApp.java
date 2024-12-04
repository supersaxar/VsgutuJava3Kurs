package DorzhievZhargalB7621;

import java.time.LocalDate;
import java.util.List;

public class NotepadApp {
    public static void main(String[] args) {
        Notepad notepad = new Notepad();

        LocalDate today = LocalDate.now();

        notepad.addNote(today, "Встреча", "Важная встреча с командой");
        notepad.addNote(today, "Покупки", "Купить молоко и хлеб");
        notepad.addNote(LocalDate.of(2024, 12, 4), "Отпуск", "Планирование отпуска");

        System.out.println("Записи за сегодня:");
        List<Notepad.Note> todayNotes = notepad.getNotes(today);
        for (Notepad.Note note : todayNotes) {
            System.out.println("- " + note.getTitle() + ": " + note.getContent());
        }

        System.out.println("\nОбщее количество записей: " + notepad.getTotalNotesCount());

        System.out.println("Количество записей за сегодня: " + notepad.getNotesCountForDate(today));
    }
}