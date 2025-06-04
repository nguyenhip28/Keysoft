package Model;

public class UserModel {
    private String userCode;
    private String username;
    private String password;
    private String roleName;
    
    // Constructors
    public UserModel() {}
    
    public UserModel(String userCode, String username, String password, String roleName) {
        this.userCode = userCode;
        this.username = username;
        this.password = password;
        this.roleName = roleName;
    }
    
    // Getters and Setters
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
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}