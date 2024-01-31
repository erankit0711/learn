package Com.First.ecommerce.user.service;

import Com.First.ecommerce.user.converter.UserDomainDtoConverter;
import Com.First.ecommerce.user.domain.UserBuilder;
import Com.First.ecommerce.util.CustomResponse;
import Com.First.ecommerce.user.dto.UserDomainDto;
import Com.First.ecommerce.user.domain.User;
import Com.First.ecommerce.user.dto.UserDomainDtoBuilder;
import Com.First.ecommerce.user.repository.UserRepository;
import org.checkerframework.checker.units.qual.A;
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
    @Autowired
    private UserDomainDtoConverter userDomainDtoConverter;

    //Create User
    @Transactional(rollbackFor = Exception.class)
    public CustomResponse<UserDomainDto> registerUser(UserDomainDto userDomainDto) {
        try {
            userDomainDto.setPassword(passwordEncoder.encode(userDomainDto.getPassword()));
            User user = userDtoToUser(userDomainDto);
            User u = UserRepo.save(user);
            UserDomainDto data = userDomainDtoConverter.convert(u);
            return CustomResponse.success(data, null, HttpStatus.CREATED.value());

        } catch (Exception e){
            e.printStackTrace();
            return CustomResponse.failure(null, e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    //Read User
    public CustomResponse<List<UserDomainDto>> getAllUser() {
        try {
            List<User> users = UserRepo.findAll();
            List<UserDomainDto> data = userDomainDtoConverter.convertList(users);
            return CustomResponse.success(data, null, HttpStatus.OK.value());

        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.failure(null, e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    public CustomResponse<UserDomainDto> getUserById(String userId) {
        try {
            User user = UserRepo.findByUserId(userId).orElseThrow(()-> new Exception("No User found with id "+userId+"."));
            UserDomainDto data = userDomainDtoConverter.convert(user);
            return CustomResponse.success(data, null, HttpStatus.OK.value());

        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.failure(null, e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    //Update User
    @Transactional(rollbackFor = Exception.class)
    public CustomResponse<UserDomainDto> updateUser(UserDomainDto userDomainDto){
        try {
            User user = UserRepo.findByUserId(userDomainDto.getUserId()).orElseThrow(
                    ()->new NullPointerException("User does not exist with id: "+ userDomainDto.getUserId())
            );
            Long id = user.getId();
            user = userDtoToUser(userDomainDto);
            user.setId(id);
            user.setUserId(userDomainDto.getUserId());
            User u = UserRepo.save(user);
            UserDomainDto data = userDomainDtoConverter.convert(user);
            return CustomResponse.success(data, null, HttpStatus.OK.value());
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.failure(null, e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    //Delete User
    @Transactional(rollbackFor = Exception.class)
    public CustomResponse<String> deleteUser(String userId) {
        try {
            User user = UserRepo.findByUserId(userId).orElseThrow(()-> new Exception("User with id "+userId+" does not exist."));
            user.setDeleted(true);
            UserRepo.save(user);
            return CustomResponse.success("Successfully deleted.", null, HttpStatus.OK.value());

        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.failure(null, e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    public User userDtoToUser(UserDomainDto userDomainDto){

        User user = new UserBuilder().setFirstName(userDomainDto.getFirstName())
                .setLastName(userDomainDto.getLastName()).setUsername(userDomainDto.getUsername())
                .setEmail(userDomainDto.getEmail()).setPhoneNumber(userDomainDto.getPhoneNumber())
                .setPassword(userDomainDto.getPassword()).setAddress(userDomainDto.getAddress()).build();

        return user;
    }

}