package Com.First.ecommerce.user.controller;

import Com.First.ecommerce.user.dto.UserDetailCreateRequestDto;
import Com.First.ecommerce.user.dto.UserDetailUpdateRequestDto;
import Com.First.ecommerce.util.CustomResponse;
import Com.First.ecommerce.user.dto.UserDetailDomainDto;
import Com.First.ecommerce.user.service.UserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userDetails")
public class UserDetailController {
    @Autowired
    private UserDetailService userDetailService;

    @PostMapping("/create")
    public ResponseEntity<CustomResponse<UserDetailDomainDto>> createUserDetail(
        @RequestBody UserDetailCreateRequestDto requestDto) {
        UserDetailDomainDto response = userDetailService.createUserDetail(requestDto);
        return new ResponseEntity<>(CustomResponse.success(response, null), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CustomResponse<List<UserDetailDomainDto>>> getAllUserDetails() {
        List<UserDetailDomainDto> response = userDetailService.getAllUserDetails();
        return new ResponseEntity<>(CustomResponse.success(response, null), HttpStatus.OK);
    }

    @GetMapping("/{userDetailId}")
    public ResponseEntity<CustomResponse<UserDetailDomainDto>> getUserDetailById(
        @PathVariable String userDetailId) {
        UserDetailDomainDto response = userDetailService.getUserDetailById(userDetailId);
        return new ResponseEntity<>(CustomResponse.success(response, null), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CustomResponse<UserDetailDomainDto>> updateUserDetail(
        @RequestBody UserDetailUpdateRequestDto requestDto) {
        UserDetailDomainDto response = userDetailService.updateUserDetail(requestDto);
        return new ResponseEntity<>(CustomResponse.success(response, null), HttpStatus.OK);
    }

    @DeleteMapping("/{userDetailId}")
    public ResponseEntity<CustomResponse<String>> deleteUserDetail(
        @PathVariable String userDetailId) {
        String response = userDetailService.deleteUserDetail(userDetailId);
        return new ResponseEntity<>(CustomResponse.success(response, null), HttpStatus.OK);
    }
}
