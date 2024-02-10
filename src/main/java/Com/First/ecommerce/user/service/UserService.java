package Com.First.ecommerce.user.service;

import Com.First.ecommerce.user.domain.User;
import Com.First.ecommerce.user.domain.UserBuilder;
import Com.First.ecommerce.user.converter.UserDomainDtoConverter;
import Com.First.ecommerce.user.dto.UserDomainDto;
import Com.First.ecommerce.user.dto.UserUpdateRequestDto;
import Com.First.ecommerce.user.repository.UserRepository;

import Com.First.ecommerce.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDomainDtoConverter userDomainDtoConverter;


    //Read User1
    public CustomResponse<List<UserDomainDto>> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            List<UserDomainDto> data = userDomainDtoConverter.convertList(users);
            return CustomResponse.success(data, null, HttpStatus.OK.value());

        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.failure(null, e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    public CustomResponse<UserDomainDto> getUserById(String userId) {
        try {
            User user = userRepository.findByUserId(userId).orElseThrow(()->
                    new Exception("No User1 found with id "+userId+".")
            );
            UserDomainDto data = userDomainDtoConverter.convert(user);
            return CustomResponse.success(data, null, HttpStatus.OK.value());

        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.failure(null, e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    //Update User1
    @Transactional(rollbackFor = Exception.class)
    public CustomResponse<UserDomainDto> updateUser(UserUpdateRequestDto requestDto){
        try {
            User user = userRepository.findByUserId(requestDto.getUserId()).orElseThrow(()->
                    new NullPointerException("User1 does not exist with id: "+ requestDto.getUserId())
            );
            requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
            User updatedUser = new UserBuilder()
                    .setEmail(requestDto.getEmail())
                    .setUsername(requestDto.getUsername())
                    .setPassword(requestDto.getPassword())
                    .build();

            updatedUser.setId(user.getId());
            updatedUser.setUserId(user.getUserId());
            userRepository.save(updatedUser);
            UserDomainDto data = userDomainDtoConverter.convert(updatedUser);
            return CustomResponse.success(data, null, HttpStatus.OK.value());
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.failure(null, e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    //Delete User1
    @Transactional(rollbackFor = Exception.class)
    public CustomResponse<String> deleteUser(String userId) {
        try {
            User user = userRepository.findByUserId(userId).orElseThrow(()->
                    new Exception("User1 with id "+userId+" does not exist.")
            );
            user.setDeleted(true);
            userRepository.save(user);
            return CustomResponse.success("Successfully deleted.", null, HttpStatus.OK.value());

        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.failure(null, e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

}
