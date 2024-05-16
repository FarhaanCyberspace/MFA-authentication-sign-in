package proj_auth.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import proj_auth.AuthenticationManager;
import proj_auth.AuthenticationMethod;

import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationManagerTest {

    private AuthenticationManager authManager;

    @BeforeEach
    void setUp() {
        authManager = new AuthenticationManager();
    }

    @Test
    void testAuthenticateWithValidPassword() {
        assertTrue(authManager.authenticate("user1", "password123", AuthenticationMethod.PASSWORD));
    }

    @Test
    void testAuthenticateWithInvalidPassword() {
        assertFalse(authManager.authenticate("user1", "wrongpassword", AuthenticationMethod.PASSWORD));
    }

    @Test
    void testAuthenticateWithValidQRCode() {
        assertTrue(authManager.authenticate("user1", "qrcode123", AuthenticationMethod.QR_CODE));
    }

    @Test
    void testAuthenticateWithInvalidQRCode() {
        assertFalse(authManager.authenticate("user1", "wrongqrcode", AuthenticationMethod.QR_CODE));
    }

    @Test
    void testAuthenticateWithValidPinCode() {
        assertTrue(authManager.authenticate("user1", "1234", AuthenticationMethod.PIN_CODE));
    }

    @Test
    void testAuthenticateWithInvalidPinCode() {
        assertFalse(authManager.authenticate("user1", "wrongpin", AuthenticationMethod.PIN_CODE));
    }

    @Test
    void testAuthenticateWithInvalidUser() {
        assertFalse(authManager.authenticate("nonexistentuser", "password123", AuthenticationMethod.PASSWORD));
    }

    @Test
    void testAuthenticateWithEmptyCredentials() {
        assertFalse(authManager.authenticate("user1", "", AuthenticationMethod.PASSWORD));
    }

    @Test
    void testAuthenticateWithNullCredentials() {
        assertFalse(authManager.authenticate("user1", null, AuthenticationMethod.PASSWORD));
    }

    @Test
    void testAuthenticateWithNullUser() {
        assertFalse(authManager.authenticate(null, "password123", AuthenticationMethod.PASSWORD));
    }

    @Test
    void testAuthenticateWithEmptyUser() {
        assertFalse(authManager.authenticate("", "password123", AuthenticationMethod.PASSWORD));
    }

    @Test
    void testAuthenticateWithInvalidAuthenticationMethod() {
        assertFalse(authManager.authenticate("user1", "password123", AuthenticationMethod.valueOf("INVALID")));
    }

    @Test
    void testAuthenticateWithNullAuthenticationMethod() {
        assertFalse(authManager.authenticate("user1", "password123", null));
    }

    @Test
    void testAuthenticateWithInvalidPasswordFormat() {
        assertFalse(authManager.authenticate("user1", "noDigitOrUppercase", AuthenticationMethod.PASSWORD));
    }

    @Test
    void testAuthenticateWithValidPasswordAndQRCode() {
        assertTrue(authManager.authenticate("user1", "password123", AuthenticationMethod.PASSWORD));
        assertTrue(authManager.authenticate("user1", "qrcode123", AuthenticationMethod.QR_CODE));
    }

    @Test
    void testAuthenticateWithValidPasswordAndInvalidQRCode() {
        assertTrue(authManager.authenticate("user1", "password123", AuthenticationMethod.PASSWORD));
        assertFalse(authManager.authenticate("user1", "invalidQR", AuthenticationMethod.QR_CODE));
    }


}
