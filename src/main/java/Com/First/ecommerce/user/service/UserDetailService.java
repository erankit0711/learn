package Com.First.ecommerce.user.service;

import Com.First.ecommerce.user.converter.UserDetailDomainDtoConverter;
import Com.First.ecommerce.user.domain.User;
import Com.First.ecommerce.user.domain.UserDetail;
import Com.First.ecommerce.user.domain.UserDetailBuilder;
import Com.First.ecommerce.user.dto.UserDetailCreateRequestDto;
import Com.First.ecommerce.user.dto.UserDetailUpdateRequestDto;
import Com.First.ecommerce.util.CustomResponse;
import Com.First.ecommerce.user.dto.UserDetailDomainDto;
import Com.First.ecommerce.user.repository.UserDetailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
@Service
public class UserDetailService {
    @Autowired
    private UserDetailRepository userDetailRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailDomainDtoConverter userDetailDomainDtoConverter;
    private Logger logger = LoggerFactory.getLogger(UserDetailService.class);

    //Create User
    @Transactional(rollbackFor = Exception.class)
    public CustomResponse<UserDetailDomainDto> createUserDetail(UserDetailCreateRequestDto requestDto) {
        try {
            User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserDetail userDetail = new UserDetailBuilder()
                    .setUserId(user)
                    .setFirstName(requestDto.getFirstName())
                    .setLastName(requestDto.getLastName())
                    .setPhoneNumber(requestDto.getPhoneNumber())
                    .setAddress(requestDto.getAddress())
                    .build();

            UserDetail savedUser = userDetailRepository.save(userDetail);
            UserDetailDomainDto data = userDetailDomainDtoConverter.convert(savedUser);
            return CustomResponse.success(data, null, HttpStatus.CREATED.value());

        } catch (Exception e){
            e.printStackTrace();
            return CustomResponse.failure(null, e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    //Read User
    public CustomResponse<List<UserDetailDomainDto>> getAllUserDetails() {
        try {
            List<UserDetail> usersDetails = userDetailRepository.findAll();
            List<UserDetailDomainDto> data = userDetailDomainDtoConverter.convertList(usersDetails);
            return CustomResponse.success(data, null, HttpStatus.OK.value());

        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.failure(null, e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    public CustomResponse<UserDetailDomainDto> getUserDetailById(String userId) {
        try {
            UserDetail user = userDetailRepository.findByUserDetailId(userId)
                    .orElseThrow(()-> new Exception("No User found with id "+userId+"."));
            UserDetailDomainDto data = userDetailDomainDtoConverter.convert(user);
            return CustomResponse.success(data, null, HttpStatus.OK.value());

        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.failure(null, e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    //Update User
    @Transactional(rollbackFor = Exception.class)
    public CustomResponse<UserDetailDomainDto> updateUserDetail(UserDetailUpdateRequestDto requestDto){
        try {
            UserDetail userDetail = userDetailRepository.findByUserDetailId(requestDto.getUserDetailId()).orElseThrow(()->
                    new NullPointerException("User does not exist with id: "+ requestDto.getUserDetailId())
            );
            Long id = userDetail.getId();
            UserDetail updatedUserDetail = new UserDetailBuilder()
                    .setFirstName(requestDto.getFirstName())
                    .setLastName(requestDto.getLastName())
                    .setPhoneNumber(requestDto.getPhoneNumber())
                    .setAddress(requestDto.getAddress())
                    .build();

            updatedUserDetail.setId(id);
            updatedUserDetail.setUserDetailId(requestDto.getUserDetailId());
            UserDetail u = userDetailRepository.save(updatedUserDetail);
            UserDetailDomainDto data = userDetailDomainDtoConverter.convert(updatedUserDetail);
            return CustomResponse.success(data, null, HttpStatus.OK.value());
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.failure(null, e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    //Delete User
    @Transactional(rollbackFor = Exception.class)
    public CustomResponse<String> deleteUserDetail(String userId) {
        try {
            UserDetail user = userDetailRepository.findByUserDetailId(userId).orElseThrow(()-> new Exception("User with id "+userId+" does not exist."));
            user.setDeleted(true);
            userDetailRepository.save(user);
            return CustomResponse.success("Successfully deleted.", null, HttpStatus.OK.value());

        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.failure(null, e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

}