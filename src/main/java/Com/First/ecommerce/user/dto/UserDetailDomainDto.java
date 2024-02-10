package Com.First.ecommerce.user.dto;

import Com.First.ecommerce.address.Address;

import java.util.List;

public class UserDetailDomainDto {
    private String userDetailId;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public UserDetailDomainDto() {
    }

    public UserDetailDomainDto(String userDetailId, String firstName, String lastName, String phoneNumber) {
        this.userDetailId = userDetailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getUserDetailId() {
        return userDetailId;
    }

    public void setUserDetailId(String userDetailId) {
        this.userDetailId = userDetailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}