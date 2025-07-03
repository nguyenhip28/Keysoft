package model;

public class ProfileModel {

    private int profileId;
    private String userCode;
    private String fullName;
    private String email;
    private int age;
    private String gender;
    private String address;
    private String phone;

    // Constructor không tham số
    public ProfileModel() {
    }

    // Constructor đầy đủ
    public ProfileModel(int profileId, String userCode, String fullName, String email, int age, String gender, String address, String phone) {
        this.profileId = profileId;
        this.userCode = userCode;
        this.fullName = fullName;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }

    // Constructor không có profileId (dùng khi insert)
    public ProfileModel(String userCode, String fullName, String email, int age, String gender, String address, String phone) {
        this.userCode = userCode;
        this.fullName = fullName;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }

    // Getters và Setters
    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
