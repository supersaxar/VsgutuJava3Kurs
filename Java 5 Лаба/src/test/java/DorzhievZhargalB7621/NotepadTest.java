package DorzhievZhargalB7621;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotepadTest {
    private Notepad notepad;
    private LocalDate testDate;

    @BeforeEach
    public void setUp() {
        notepad = new Notepad();
        testDate = LocalDate.of(2024, 1, 15);
    }

    @Test
    public void testAddNote() {
        notepad.addNote(testDate, "Встреча", "Важная встреча с клиентом");
        List<Notepad.Note> notes = notepad.getNotes(testDate);

        assertEquals(1, notes.size());
        assertEquals("Встреча", notes.get(0).getTitle());
        assertEquals("Важная встреча с клиентом", notes.get(0).getContent());
    }

    @Test
    public void testMultipleNotesOnSameDate() {
        notepad.addNote(testDate, "Встреча 1", "Первая встреча");
        notepad.addNote(testDate, "Встреча 2", "Вторая встреча");

        List<Notepad.Note> notes = notepad.getNotes(testDate);
        assertEquals(2, notes.size());
    }

    @Test
    public void testGetNotesCountForDate() {
        notepad.addNote(testDate, "Встреча 1", "Первая встреча");
        notepad.addNote(testDate, "Встреча 2", "Вторая встреча");

        int count = notepad.getNotesCountForDate(testDate);
        assertEquals(2, count);
    }

    @Test
    public void testGetTotalNotesCount() {
        LocalDate anotherDate = LocalDate.of(2024, 1, 16);

        notepad.addNote(testDate, "Встреча 1", "Первая встреча");
        notepad.addNote(testDate, "Встреча 2", "Вторая встреча");
        notepad.addNote(anotherDate, "Другая встреча", "Встреча в другой день");

        int totalCount = notepad.getTotalNotesCount();
        assertEquals(3, totalCount);
    }

    @Test
    public void testGetNotesForNonExistentDate() {
        LocalDate nonExistentDate = LocalDate.of(2024, 2, 1);
        List<Notepad.Note> notes = notepad.getNotes(nonExistentDate);

        assertTrue(notes.isEmpty());
    }
}