package Com.First.ecommerce.user.controller;

import Com.First.ecommerce.util.CustomResponse;
import Com.First.ecommerce.user.dto.UserDomainDto;
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
    public ResponseEntity<CustomResponse<UserDomainDto>> registerUser(@RequestBody UserDomainDto userDomainDto)
    {
        CustomResponse<UserDomainDto> response = userService.registerUser(userDomainDto);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    //Read User
    @GetMapping
    public ResponseEntity<CustomResponse<List<UserDomainDto>>> getAllUsers()
    {
        CustomResponse<List<UserDomainDto>> response = userService.getAllUser();
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<CustomResponse<UserDomainDto>> getUserById(@PathVariable String userId)
    {
        CustomResponse<UserDomainDto> response = userService.getUserById(userId);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }


    //Update User
    @PutMapping
    public ResponseEntity<CustomResponse<UserDomainDto>> updateUser(@RequestBody UserDomainDto userDomainDto)
    {
        CustomResponse<UserDomainDto> response = userService.updateUser(userDomainDto);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    //Delete User
    @DeleteMapping("/{userId}")
    public ResponseEntity<CustomResponse<String>> deleteUser(@PathVariable String userId)
    {
        CustomResponse<String> response = userService.deleteUser(userId);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }
}
