package DorzhievZhargalB7621;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ProcessManagementTest {
    private Book book;
    private Journal journal;
    private Manual manual;

    @BeforeEach
    void setUp() {
        book = new Book("Тестовая книга", "Автор А");
        journal = new Journal("Тестовый журнал", "2024-01");
        manual = new Manual("Тестовое учебное пособие", "Тема А");
    }

    @Test
    void testBookMethods() {
        assertDoesNotThrow(() -> book.createContract("Клиент Б"));
        assertDoesNotThrow(() -> book.editContent("Обновлена книга"));
        assertDoesNotThrow(book::format);
        assertDoesNotThrow(book::sendToPrint);
        assertDoesNotThrow(book::cancel);
        assertDoesNotThrow(book::pay);
        assertDoesNotThrow(book::resume);
        assertDoesNotThrow(book::close);
    }

    @Test
    void testJournalMethods() {
        assertDoesNotThrow(() -> journal.createContract("Клиент В"));
        assertDoesNotThrow(() -> journal.editContent("Обновлен журнал"));
        assertDoesNotThrow(journal::format);
        assertDoesNotThrow(journal::sendToPrint);
        assertDoesNotThrow(journal::cancel);
        assertDoesNotThrow(journal::pay);
        assertDoesNotThrow(journal::resume);
        assertDoesNotThrow(journal::close);
    }

    @Test
    void testManualMethods() {
        assertDoesNotThrow(() -> manual.createContract("Клиент Г"));
        assertDoesNotThrow(() -> manual.editContent("Обновлено учебное пособие "));
        assertDoesNotThrow(manual::format);
        assertDoesNotThrow(manual::sendToPrint);
        assertDoesNotThrow(manual::cancel);
        assertDoesNotThrow(manual::pay);
        assertDoesNotThrow(manual::resume);
        assertDoesNotThrow(manual::close);
    }
}
