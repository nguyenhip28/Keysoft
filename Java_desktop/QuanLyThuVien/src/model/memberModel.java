package model;

public class memberModel {

    private int memberId;
    private String memberCode;
    private String fullName;
    private String gender;
    private int age;
    private String address;
    private String phone;

    // Constructor
    public memberModel(int memberId, String memberCode, String fullName, String gender, int age, String address, String phone) {
        this.memberId = memberId;
        this.memberCode = memberCode;
        this.fullName = fullName;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.phone = phone;
    }

    public memberModel(String memberCode, String fullName, String gender, int age, String address, String phone) {
        this(0, memberCode, fullName, gender, age, address, phone);
    }

    // Getters and Setters
    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
