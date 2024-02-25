package Com.First.ecommerce.user.dto;

public class UserDetailDomainDtoBuilder {
    private String userDetailId;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public UserDetailDomainDtoBuilder() {

    }

    public UserDetailDomainDtoBuilder setUserDetailId(String userDetailId) {
        this.userDetailId = userDetailId;
        return this;
    }

    public UserDetailDomainDtoBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserDetailDomainDtoBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }


    public UserDetailDomainDtoBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }


    public UserDetailDomainDto build(){
        return new UserDetailDomainDto(userDetailId,firstName,lastName,phoneNumber);
    }
}
