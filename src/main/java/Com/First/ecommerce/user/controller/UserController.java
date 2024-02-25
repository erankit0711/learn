package Com.First.ecommerce.user.controller;

import Com.First.ecommerce.user.dto.UserUpdateRequestDto;
import Com.First.ecommerce.user.service.UserService;
import Com.First.ecommerce.user.dto.UserDomainDto;
import Com.First.ecommerce.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    //Read User
    @GetMapping
    public ResponseEntity<CustomResponse<List<UserDomainDto>>> getAllUsers() {
        List<UserDomainDto> response = userService.getAllUsers();
        return new ResponseEntity<>(CustomResponse.success(response, null), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CustomResponse<UserDomainDto>> getUserById(@PathVariable String userId) {
        UserDomainDto response = userService.getUserById(userId);
        return new ResponseEntity<>(CustomResponse.success(response, null), HttpStatus.OK);
    }

    //Update User
    @PutMapping
    public ResponseEntity<CustomResponse<UserDomainDto>> updateUser(@RequestBody UserUpdateRequestDto requestDto) {
        UserDomainDto response = userService.updateUser(requestDto);
        return new ResponseEntity<>(CustomResponse.success(response, null), HttpStatus.OK);
    }

    //Delete User
    @DeleteMapping("/{userId}")
    public ResponseEntity<CustomResponse<String>> deleteUser(@PathVariable String userId) {
        String response = userService.deleteUser(userId);
        return new ResponseEntity<>(CustomResponse.success(response, null), HttpStatus.OK);
    }
}
