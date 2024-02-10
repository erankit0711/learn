package Com.First.ecommerce.user.domain;

import Com.First.ecommerce.address.Address;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;

import java.util.List;

public class UserDetailBuilder {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private User userId;
    private List<Address> address;

    public UserDetailBuilder() {
    }

    public UserDetailBuilder setUserId(User userId) {
        this.userId = userId;
        return this;
    }
    public UserDetailBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    public UserDetailBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserDetailBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserDetailBuilder setAddress(List<Address> address) {
        this.address = address;
        return this;
    }
    public UserDetail build(){
        return new UserDetail(firstName,lastName,phoneNumber,userId,address);
    }
}
