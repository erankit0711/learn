package Com.First.CrudApp.User.Model;

public class UserDtoBuilder {
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private String address;

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

    public UserDtoBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDtoBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserDtoBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public UserDto build(){
        return new UserDto(userId,firstName,lastName,username,email,phoneNumber,address);
    }
}
