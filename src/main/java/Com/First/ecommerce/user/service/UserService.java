package Com.First.ecommerce.user.service;

import Com.First.ecommerce.user.domain.User;
import Com.First.ecommerce.user.domain.UserBuilder;
import Com.First.ecommerce.user.converter.UserDomainDtoConverter;
import Com.First.ecommerce.user.dto.UserDomainDto;
import Com.First.ecommerce.user.dto.UserUpdateRequestDto;
import Com.First.ecommerce.user.repository.UserRepository;

import Com.First.ecommerce.util.CustomResponse;
import jakarta.persistence.EntityNotFoundException;
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

    public List<UserDomainDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDomainDto> userDtoList = userDomainDtoConverter.convertList(users);
        return userDtoList;
    }

    public UserDomainDto getUserById(String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(() ->
            new EntityNotFoundException("No User found with id " + userId + ".")
        );
        UserDomainDto userDto = userDomainDtoConverter.convert(user);
        return userDto;
    }

    @Transactional(rollbackFor = Exception.class)
    public UserDomainDto updateUser(UserUpdateRequestDto requestDto) {
        String userId = requestDto.getUserId();
        User user = userRepository.findByUserId(userId).orElseThrow(() ->
            new EntityNotFoundException("User does not exist with id: " + userId)
        );
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        User updatedUser = new UserBuilder()
            .setEmail(requestDto.getEmail())
            .setUsername(user.getUsername())
            .setPassword(requestDto.getPassword())
            .build();

        updatedUser.setId(user.getId());
        updatedUser.setUserId(user.getUserId());
        userRepository.save(updatedUser);
        UserDomainDto userDto = userDomainDtoConverter.convert(updatedUser);
        return userDto;
    }

    @Transactional(rollbackFor = Exception.class)
    public String deleteUser(String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(() ->
            new EntityNotFoundException("User with id " + userId + " does not exist.")
        );
        user.setDeleted(true);
        userRepository.save(user);
        return "Successfully deleted.";
    }
}
