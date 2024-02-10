package Com.First.ecommerce.security.controller;

import Com.First.ecommerce.security.dto.UserLoginRequestDto;
import Com.First.ecommerce.security.dto.UserRegisterRequestDto;
import Com.First.ecommerce.security.service.AuthService;

import Com.First.ecommerce.user.dto.UserDomainDto;
import Com.First.ecommerce.util.CustomResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class AuthController {
    @Autowired
    AuthService authService;
    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    //Register User
    @PostMapping("/register")
    public ResponseEntity<CustomResponse<UserDomainDto>> registerUser(@RequestBody UserRegisterRequestDto request) {
        CustomResponse<UserDomainDto> response = authService.registerUser(request);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    //Login User
    @PostMapping("/login")
    public ResponseEntity<CustomResponse<String>> login(@RequestBody UserLoginRequestDto request){
        CustomResponse<String> response = authService.login(request);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

}
