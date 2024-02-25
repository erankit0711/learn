package Com.First.ecommerce.user.dto;

public class UserUpdateRequestDto {
    private String userId;
    private String email;
    private String password;

    public UserUpdateRequestDto(String userId, String username, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
