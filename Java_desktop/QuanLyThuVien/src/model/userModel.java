package Model;

public class UserModel {
    private final String userCode;
    private final String fullName;
    private final String username;
    private final String email;
    private final int age;
    private final String gender;
    private final String address;
    private final String phone;
    private final String passwordHash;

    public UserModel(String userCode, String fullName, String username, String email, int age, String gender, String address, String phone, String passwordHash) {
        this.userCode = userCode;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.passwordHash = passwordHash;
    }

    // Getter & Setter
    public String getUserCode() { return userCode; }
    public String getFullName() { return fullName; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getPasswordHash() { return passwordHash; }
}
