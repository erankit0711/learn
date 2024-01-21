package Com.First.CrudApp.User.Controller;

import Com.First.CrudApp.Util.CustomResponse;
import Com.First.CrudApp.User.Model.User;
import Com.First.CrudApp.User.Model.UserDto;
import Com.First.CrudApp.User.Service.UserService;
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
    @PostMapping("/create")
    public ResponseEntity<CustomResponse<UserDto>> createUser(@RequestBody User user)
    {
        CustomResponse<UserDto> response = userService.createUser(user);
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
    public ResponseEntity<CustomResponse<UserDto>> updateUser(@RequestBody User user)
    {
        CustomResponse<UserDto> response = userService.updateUser(user);
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
