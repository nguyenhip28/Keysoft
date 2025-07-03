package model;

import java.time.LocalDateTime;

public class AccountModel {

    private int userId;
    private String userCode;
    private String username;
    private String passwordHash;
    private LocalDateTime createdAt;

    // Constructor mặc định
    public AccountModel() {
    }

    // Constructor đầy đủ
    public AccountModel(int userId, String userCode, String username, String passwordHash, LocalDateTime createdAt) {
        this.userId = userId;
        this.userCode = userCode;
        this.username = username;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
    }

    // Constructor không có userId (dùng khi đăng ký)
    public AccountModel(String userCode, String username, String passwordHash) {
        this.userCode = userCode;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    // Getters và Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
