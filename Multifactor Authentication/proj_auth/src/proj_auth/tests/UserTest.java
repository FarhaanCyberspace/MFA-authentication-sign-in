package proj_auth.tests;

import org.junit.jupiter.api.Test;
import proj_auth.User;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void testGetUsername() {
        User user = new User("testuser", "testpassword", "testqrcode", "testpin");
        assertEquals("testuser", user.getUsername());
    }

    @Test
    void testGetPassword() {
        User user = new User("testuser", "testpassword", "testqrcode", "testpin");
        assertEquals("testpassword", user.getPassword());
    }

    @Test
    void testGetQrCode() {
        User user = new User("testuser", "testpassword", "testqrcode", "testpin");
        assertEquals("testqrcode", user.getQrCode());
    }

    @Test
    void testGetPinCode() {
        User user = new User("testuser", "testpassword", "testqrcode", "testpin");
        assertEquals("testpin", user.getPinCode());
    }

    @Test
    void testSetters() {
        User user = new User("initialUser", "initialPassword", "initialQRCode", "initialPINCode");

        // Test setter methods
        user.setUsername("newUser");
        user.setPassword("newPassword");
        user.setQrCode("newQRCode");
        user.setPinCode("newPINCode");

        // Test getter methods after setting new values
        assertEquals("newUser", user.getUsername());
        assertEquals("newPassword", user.getPassword());
        assertEquals("newQRCode", user.getQrCode());
        assertEquals("newPINCode", user.getPinCode());
    }

    @Test
    void testEqualsAndHashCode() {
        User user1 = new User("testuser", "testpassword", "testqrcode", "testpin");
        User user2 = new User("testuser", "testpassword", "testqrcode", "testpin");

        // Test equals and hashCode methods
        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());

        User user3 = new User("anotheruser", "anotherpassword", "anotherqrcode", "anotherpin");

        // Test non-equality for a different user
        assertNotEquals(user1, user3);
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }

    @Test
    void testToString() {
        User user = new User("testuser", "testpassword", "testqrcode", "testpin");

        // Test toString method
        assertEquals("User{username='testuser', password='testpassword', qrCode='testqrcode', pinCode='testpin'}", user.toString());
    }
}
