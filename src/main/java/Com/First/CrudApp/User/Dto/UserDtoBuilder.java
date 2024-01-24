package Com.First.CrudApp.User.Dto;

import Com.First.CrudApp.Address.Address;

import java.util.List;

public class UserDtoBuilder {
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private List<Address> address;

    public UserDtoBuilder() {

    }

    public UserDtoBuilder setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public UserDtoBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserDtoBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserDtoBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserDtoBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDtoBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDtoBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserDtoBuilder setAddress(List<Address> address) {
        this.address = address;
        return this;
    }

    public UserDto build(){
        return new UserDto(userId,firstName,lastName,username,password,email,phoneNumber,address);
    }
}
