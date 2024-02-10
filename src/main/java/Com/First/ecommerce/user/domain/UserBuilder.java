package Com.First.ecommerce.user.domain;

import java.util.Set;

public final class UserBuilder {
    private String username;
    private String email;
    private String password;
    private Set<String> roles;

    public UserBuilder() {
    }

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }


    public User build() {
        return new User(username, email, password);
    }
}
