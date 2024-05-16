package proj_auth.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import proj_auth.MultiFactorAuthenticationDemo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiFactorAuthenticationDemoTest {

    @Test
    void testValidAuthentication() {
        String input = "user1\npassword123\nqrcode123\n1234\n";
        String expectedOutput = "Enter username: Enter password: Enter QR code: Enter PIN code: " +
                "Password authentication successful.\nQR code authentication successful.\nPIN code authentication successful.\n";

        runTestWithInput(input, expectedOutput);
    }

    @ParameterizedTest
    @ValueSource(strings = {"weak", "short"})
    void testWeakPassword(String weakPassword) {
        String input = "user1\n" + weakPassword + "\nqrcode123\n1234\n";
        String expectedOutput = "Enter username: Enter password: Weak password. Password should be at least 8 characters long and include uppercase, lowercase, and a digit.\n";

        runTestWithInput(input, expectedOutput);
    }

    @Test
    void testInvalidQRCode() {
        String input = "user1\npassword123\ninvalidQR\n1234\n";
        String expectedOutput = "Enter username: Enter password: Enter QR code: QR code authentication failed.\nPIN code authentication successful.\n";

        runTestWithInput(input, expectedOutput);
    }

    @Test
    void testInvalidPINCode() {
        String input = "user1\npassword123\nqrcode123\ninvalidPIN\n";
        String expectedOutput = "Enter username: Enter password: Enter QR code: Enter PIN code: PIN code authentication failed.\n";

        runTestWithInput(input, expectedOutput);
    }

    @Test
    void testInvalidPasswordAndQRCode() {
        String input = "user1\ninvalidPassword\ninvalidQR\n1234\n";
        String expectedOutput = "Enter username: Enter password: Password authentication failed.\n";

        runTestWithInput(input, expectedOutput);
    }
    
    @Test
    void testInvalidPasswordAndInvalidQRCodeAndInvalidPINCode() {
        String input = "user1\ninvalidPassword\ninvalidQR\ninvalidPIN\n";
        String expectedOutput = "Enter username: Enter password: Password authentication failed.\n";

        runTestWithInput(input, expectedOutput);
    }

    @Test
    void testInvalidUsernameAndPasswordAndInvalidQRCodeAndInvalidPINCode() {
        String input = "invalidUser\ninvalidPassword\ninvalidQR\ninvalidPIN\n";
        String expectedOutput = "Enter username: User not found.\n";

        runTestWithInput(input, expectedOutput);
    }

    @Test
    void testInvalidUsernameAndPasswordAndInvalidQRCodeAndInvalidPINCodeAndWeakPassword() {
        String input = "invalidUser\nweakPassword\ninvalidQR\ninvalidPIN\n";
        String expectedOutput = "Enter username: Enter password: Weak password. Password should be at least 8 characters long and include uppercase, lowercase, and a digit.\n";

        runTestWithInput(input, expectedOutput);
    }

    @Test
    void testInvalidUsernameAndPasswordAndInvalidQRCodeAndInvalidPINCodeAndShortPassword() {
        String input = "invalidUser\nshort\ninvalidQR\ninvalidPIN\n";
        String expectedOutput = "Enter username: Enter password: Weak password. Password should be at least 8 characters long and include uppercase, lowercase, and a digit.\n";

        runTestWithInput(input, expectedOutput);
    }

    @Test
    void testInvalidPasswordAndInvalidQRCode() {
        String input = "user1\ninvalidPassword\ninvalidQR\ninvalidPIN\n";
        String expectedOutput = "Enter username: Enter password: Password authentication failed.\n";

        runTestWithInput(input, expectedOutput);
    }

    private void runTestWithInput(String input, String expectedOutput) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        MultiFactorAuthenticationDemo.main(new String[]{});

        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }
}
