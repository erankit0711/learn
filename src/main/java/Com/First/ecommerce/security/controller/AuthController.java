package Com.First.ecommerce.security.controller;

import Com.First.ecommerce.security.dto.UserLoginRequestDto;
import Com.First.ecommerce.security.dto.UserRegisterRequestDto;
import Com.First.ecommerce.security.service.AuthService;

import Com.First.ecommerce.user.dto.UserDomainDto;
import Com.First.ecommerce.util.CustomResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class AuthController {
    @Autowired
    AuthService authService;

    //Register User
    @PostMapping("/register")
    public ResponseEntity<CustomResponse<UserDomainDto>> registerUser(
        @RequestBody UserRegisterRequestDto request) {
        UserDomainDto response = authService.registerUser(request);
        return new ResponseEntity<>(CustomResponse.success(response, null), HttpStatus.CREATED);
    }

    //Login User
    @PostMapping("/login")
    public ResponseEntity<CustomResponse<String>> login(@RequestBody UserLoginRequestDto request) {
        String response = authService.login(request);
        return new ResponseEntity<>(CustomResponse.success(response, null), HttpStatus.OK);
    }
}
