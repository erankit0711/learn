package Com.First.ecommerce.user.service;

import Com.First.ecommerce.user.domain.UserBuilder;
import Com.First.ecommerce.util.CustomResponse;
import Com.First.ecommerce.user.dto.UserDto;
import Com.First.ecommerce.user.domain.User;
import Com.First.ecommerce.user.dto.UserDtoBuilder;
import Com.First.ecommerce.user.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
public class UserService {
    @Autowired
    private UserRepository UserRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //Create User
    @Transactional(rollbackFor = Exception.class)
    public CustomResponse<UserDto> registerUser(UserDto userDto) {
        try {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            User user = userDtoToUser(userDto);
            User u = UserRepo.save(user);
            UserDto data = userToDto(u);
            return CustomResponse.success(data, null, HttpStatus.CREATED.value());

        } catch (Exception e){
            e.printStackTrace();
            return CustomResponse.failure(null, e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    //Read User
    public CustomResponse<List<UserDto>> getAllUser() {
        try {
            List<User> users = UserRepo.findAll();
            List<UserDto> data = new ArrayList<>();
            for(int i=0;i<users.size();i++){
                data.add(userToDto(users.get(i)));
            }
            return CustomResponse.success(data, null, HttpStatus.OK.value());

        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.failure(null, e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    public CustomResponse<UserDto> getUserById(String userId) {
        try {
            User user = UserRepo.findByUserId(userId).orElseThrow(()-> new Exception("No User found with id "+userId+"."));
            UserDto data = userToDto(user);
            return CustomResponse.success(data, null, HttpStatus.OK.value());

        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.failure(null, e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    //Update User
    @Transactional(rollbackFor = Exception.class)
    public CustomResponse<UserDto> updateUser(UserDto userDto){
        try {
            User user = UserRepo.findByUserId(userDto.getUserId()).orElseThrow(
                    ()->new NullPointerException("User does not exist with id: "+userDto.getUserId())
            );
            Long id = user.getId();
            user = userDtoToUser(userDto);
            user.setId(id);
            user.setUserId(userDto.getUserId());
            User u = UserRepo.save(user);
            UserDto data = userToDto(u);
            return CustomResponse.success(data, null, HttpStatus.OK.value());
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.failure(null, e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    //Delete User
    @Transactional(rollbackFor = Exception.class)
    public CustomResponse<String> deleteUser(Long userId) {
        try {
            User user = UserRepo.findById(userId).orElseThrow(()-> new Exception("User with id "+userId+" does not exist."));
            user.setDeleted(true);
            UserRepo.save(user);
            return CustomResponse.success("Successfully deleted.", null, HttpStatus.OK.value());

        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.failure(null, e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    //DTO
    public UserDto userToDto(User user) {
        //UserDto userDto = new UserDto(user.getUserId(),user.getFirstName(),user.getLastName(),user.getUsername(),user.getEmail(), user.getPhoneNumber(), user.getAddress());
        UserDto userDto = new UserDtoBuilder().setUserId(user.getUserId())
                .setLastName(user.getLastName()).setFirstName(user.getFirstName())
                .setUsername(user.getUsername()).setEmail(user.getEmail())
                .setPhoneNumber(user.getPhoneNumber()).build();
        return userDto;
    }

    public User userDtoToUser(UserDto userDto){

        User user = new UserBuilder().setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName()).setUsername(userDto.getUsername())
                .setEmail(userDto.getEmail()).setPhoneNumber(userDto.getPhoneNumber())
                .setPassword(userDto.getPassword()).setAddress(userDto.getAddress()).build();

        return user;
    }

}