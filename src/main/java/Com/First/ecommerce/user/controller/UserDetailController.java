package Com.First.ecommerce.user.controller;

import Com.First.ecommerce.user.dto.UserDetailCreateRequestDto;
import Com.First.ecommerce.user.dto.UserDetailUpdateRequestDto;
import Com.First.ecommerce.util.CustomResponse;
import Com.First.ecommerce.user.dto.UserDetailDomainDto;
import Com.First.ecommerce.user.service.UserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userDetails")
public class UserDetailController
{
    @Autowired
    private UserDetailService userDetailService;

    //Create User
    @PostMapping("/create")
    public ResponseEntity<CustomResponse<UserDetailDomainDto>> createUserDetail(@RequestBody UserDetailCreateRequestDto requestDto) throws Exception {
        CustomResponse<UserDetailDomainDto> response = userDetailService.createUserDetail(requestDto);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    //Read User
    @GetMapping
    public ResponseEntity<CustomResponse<List<UserDetailDomainDto>>> getAllUserDetails() {
        CustomResponse<List<UserDetailDomainDto>> response = userDetailService.getAllUserDetails();
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    @GetMapping("/{userDetailId}")
    public ResponseEntity<CustomResponse<UserDetailDomainDto>> getUserDetailById(@PathVariable String userDetailId) {
        CustomResponse<UserDetailDomainDto> response = userDetailService.getUserDetailById(userDetailId);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    //Update User
    @PutMapping
    public ResponseEntity<CustomResponse<UserDetailDomainDto>> updateUserDetail(@RequestBody UserDetailUpdateRequestDto requestDto) {
        CustomResponse<UserDetailDomainDto> response = userDetailService.updateUserDetail(requestDto);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    //Delete User
    @DeleteMapping("/{userDetailId}")
    public ResponseEntity<CustomResponse<String>> deleteUserDetail(@PathVariable String userDetailId) {
        CustomResponse<String> response = userDetailService.deleteUserDetail(userDetailId);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

}
