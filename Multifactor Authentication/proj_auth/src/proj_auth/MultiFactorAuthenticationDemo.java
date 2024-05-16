package proj_auth;

import java.util.Scanner;

public class MultiFactorAuthenticationDemo {
    private static final int MAX_PASSWORD_ATTEMPTS = 3;

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        AuthenticationManager authManager = new AuthenticationManager();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        // Password authentication
        int passwordAttempts = 0;
        while (passwordAttempts < MAX_PASSWORD_ATTEMPTS) {
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (isStrongPassword(password)) {
                if (authManager.authenticate(username, password, AuthenticationMethod.PASSWORD)) {
                    System.out.println("Password authentication successful.");
                    break;
                } else {
                    System.out.println("Password authentication failed.");
                    return;
                }
            } else {
                passwordAttempts++;
                System.out.println("Weak password. Password should be at least 8 characters long and include uppercase, lowercase, and a digit.");
                if (passwordAttempts < MAX_PASSWORD_ATTEMPTS) {
                    System.out.println("You have " + (MAX_PASSWORD_ATTEMPTS - passwordAttempts) + " attempts remaining.");
                } else {
                    System.out.println("Maximum attempts reached. Exiting program.");
                    return;
                }
            }
        }

        // QR code authentication
        System.out.print("Enter QR code: ");
        String qrCode = scanner.nextLine();
        if (authManager.authenticate(username, qrCode, AuthenticationMethod.QR_CODE)) {
            System.out.println("QR code authentication successful.");
        } else {
            System.out.println("QR code authentication failed.");
            return;
        }

        // PIN code authentication
        System.out.print("Enter PIN code: ");
        String pinCode = scanner.nextLine();
        if (authManager.authenticate(username, pinCode, AuthenticationMethod.PIN_CODE)) {
            System.out.println("PIN code authentication successful.");
        } else {
            System.out.println("PIN code authentication failed.");
        }

        scanner.close();
    }

    private static boolean isStrongPassword(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*\\d.*");
    }
}
