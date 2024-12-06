package DorzhievZhargalB7621.DorzhievZhargalB7621.C;

import DorzhievZhargalB7621.C.PasswordChecker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordCheckerTest {

    @Test
    public void testValidStrongPassword() {
        assertTrue(PasswordChecker.isStrongPassword("StrongPass1_"));
    }

    @Test
    public void testPasswordTooShort() {
        assertFalse(PasswordChecker.isStrongPassword("Short1_"));
    }

    @Test
    public void testMissingUpperCaseLetter() {
        assertFalse(PasswordChecker.isStrongPassword("weakpass1_"));
    }

    @Test
    public void testMissingLowerCaseLetter() {
        assertFalse(PasswordChecker.isStrongPassword("STRONGPASS1_"));
    }

    @Test
    public void testMissingDigit() {
        assertFalse(PasswordChecker.isStrongPassword("StrongPass_"));
    }

    @Test
    public void testInvalidCharacters() {
        assertFalse(PasswordChecker.isStrongPassword("StrongPass1!"));
    }

    @Test
    public void testValidPasswordWithUnderscore() {
        assertTrue(PasswordChecker.isStrongPassword("Valid_123A"));
    }

    @Test
    public void testNullPassword() {
        assertFalse(PasswordChecker.isStrongPassword(null));
    }

    @Test
    public void testEmptyPassword() {
        assertFalse(PasswordChecker.isStrongPassword(""));
    }
}
