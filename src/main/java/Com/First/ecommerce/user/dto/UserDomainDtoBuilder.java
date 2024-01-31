package Com.First.ecommerce.user.dto;

import Com.First.ecommerce.address.Address;

import java.util.List;

public class UserDomainDtoBuilder {
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private List<Address> address;

    public UserDomainDtoBuilder() {

    }

    public UserDomainDtoBuilder setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public UserDomainDtoBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserDomainDtoBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserDomainDtoBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserDomainDtoBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDomainDtoBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDomainDtoBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserDomainDtoBuilder setAddress(List<Address> address) {
        this.address = address;
        return this;
    }

    public UserDomainDto build(){
        return new UserDomainDto(userId,firstName,lastName,username,password,email,phoneNumber,address);
    }
}
