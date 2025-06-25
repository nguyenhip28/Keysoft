package model;

public class memberModel {

    private int memberId;
    private String memberCode;
    private int userId;

    // Constructor đầy đủ (dùng khi lấy dữ liệu từ DB)
    public memberModel(int memberId, String memberCode, int userId) {
        this.memberId = memberId;
        this.memberCode = memberCode;
        this.userId = userId;
    }

    // Constructor tạo mới (chỉ cần memberCode và userId)
    public memberModel(String memberCode, int userId) {
        this.memberCode = memberCode;
        this.userId = userId;
    }

    // Getters & Setters
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
