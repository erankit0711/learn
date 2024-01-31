package Com.First.ecommerce.user.domain;

import Com.First.ecommerce.address.Address;
import java.util.List;

public class UserBuilder {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private List<Address> address;

    public UserBuilder() {
    }
    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }
    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }
    public UserBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }
    public UserBuilder setAddress(List<Address> address) {
        this.address = address;
        return this;
    }
    public User build(){
        return new User(firstName,lastName,username,email,phoneNumber,password,address);
    }
}
