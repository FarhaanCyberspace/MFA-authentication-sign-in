package proj_auth;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationManager {
    private Map<String, User> userDatabase;

    public AuthenticationManager() {
        this.userDatabase = new HashMap<>();
        userDatabase.put("user1", new User("user1", "Password@12345", "qrcode123", "1234"));
    }

    public boolean authenticate(String username, String credentials, AuthenticationMethod method) {
        User user = userDatabase.get(username);

        if (user == null) {
            System.out.println("User not found.");
            return false;
        }

        switch (method) {
            case PASSWORD:
                boolean passwordAuth = authenticatePassword(user, credentials);
                if (passwordAuth) {
                    System.out.println("Password authentication successful. Welcome, " + username + "!");
                }
                return passwordAuth;
            case QR_CODE:
                boolean qrCodeAuth = authenticateQrCode(user, credentials);
                if (qrCodeAuth) {
                    System.out.println("QR code authentication successful. Welcome, " + username + "!");
                }
                return qrCodeAuth;
            case PIN_CODE:
                boolean pinCodeAuth = authenticatePinCode(user, credentials);
                if (pinCodeAuth) {
                    System.out.println("PIN code authentication successful. Welcome, " + username + "!");
                }
                return pinCodeAuth;
            default:
                System.out.println("Invalid authentication method.");
                return false;
        }
    }

    private boolean authenticatePassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    private boolean authenticateQrCode(User user, String qrCode) {
        return user.getQrCode().equals(qrCode);
    }

    private boolean authenticatePinCode(User user, String pinCode) {
        return user.getPinCode().equals(pinCode);
    }
}
