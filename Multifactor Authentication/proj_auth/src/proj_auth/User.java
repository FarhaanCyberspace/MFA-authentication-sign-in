package proj_auth;

public class User {
    private String username;
    private String password;
    private String qrCode;
    private String pinCode;

    public User(String username, String password, String qrCode, String pinCode) {
        this.username = username;
        this.password = password;
        this.qrCode = qrCode;
        this.pinCode = pinCode;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getQrCode() {
        return qrCode;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
}
