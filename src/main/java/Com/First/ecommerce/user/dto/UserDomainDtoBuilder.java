package Com.First.ecommerce.user.dto;

public class UserDomainDtoBuilder {
    private String userId;
    private String username;
    private String email;

    public UserDomainDtoBuilder() {

    }

    public UserDomainDtoBuilder setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public UserDomainDtoBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserDomainDtoBuilder setEmail(String email) {
        this.email = email;
        return this;
    }


    public UserDomainDto build() {
        return new UserDomainDto(userId, username, email);
    }
}