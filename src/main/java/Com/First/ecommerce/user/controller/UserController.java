package Com.First.ecommerce.user.controller;

import Com.First.ecommerce.util.CustomResponse;
import Com.First.ecommerce.user.dto.UserDto;
import Com.First.ecommerce.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserService userService;

    //Create User
    @PostMapping("/register")
    public ResponseEntity<CustomResponse<UserDto>> registerUser(@RequestBody UserDto userDto)
    {
        CustomResponse<UserDto> response = userService.registerUser(userDto);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    //Read User
    @GetMapping
    public ResponseEntity<CustomResponse<List<UserDto>>> getAllUsers()
    {
        CustomResponse<List<UserDto>> response = userService.getAllUser();
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<CustomResponse<UserDto>> getUserById(@PathVariable String userId)
    {
        CustomResponse<UserDto> response = userService.getUserById(userId);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }


    //Update User
    @PutMapping
    public ResponseEntity<CustomResponse<UserDto>> updateUser(@RequestBody UserDto userDto)
    {
        CustomResponse<UserDto> response = userService.updateUser(userDto);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    //Delete User
    @DeleteMapping("/{userId}")
    public ResponseEntity<CustomResponse<String>> deleteUser(@PathVariable Long userId)
    {
        CustomResponse<String> response = userService.deleteUser(userId);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }
}
