package Com.First.ecommerce.user.service;

import Com.First.ecommerce.address.domain.Address;
import Com.First.ecommerce.user.converter.UserDetailDomainDtoConverter;
import Com.First.ecommerce.user.domain.User;
import Com.First.ecommerce.user.domain.UserDetail;
import Com.First.ecommerce.user.domain.UserDetailBuilder;
import Com.First.ecommerce.user.dto.UserDetailCreateRequestDto;
import Com.First.ecommerce.user.dto.UserDetailUpdateRequestDto;
import Com.First.ecommerce.user.dto.UserDetailDomainDto;
import Com.First.ecommerce.user.repository.UserDetailRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional(rollbackFor = Exception.class)
    public UserDetailDomainDto createUserDetail(UserDetailCreateRequestDto requestDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Address> addressList = requestDto.getAddress();
        List<Address> buildAddressList = addressList.stream()
            .map(address -> new Address.AddressBuilder()
                .withStreetNo(address.getStreetNo())
                .withAddressLine1(address.getAddressLine1())
                .withAddressLine2(address.getAddressLine2())
                .withCity(address.getCity())
                .withState(address.getState())
                .withCountry(address.getCountry())
                .withPincode(address.getpincode())
                .build())
            .collect(Collectors.toList());

        UserDetail userDetail = new UserDetailBuilder()
            .setUserId(user)
            .setFirstName(requestDto.getFirstName())
            .setLastName(requestDto.getLastName())
            .setPhoneNumber(requestDto.getPhoneNumber())
            .setAddress(buildAddressList)
            .build();

        UserDetail savedUser = userDetailRepository.save(userDetail);
        UserDetailDomainDto userDetailDto = userDetailDomainDtoConverter.convert(savedUser);
        return userDetailDto;
    }

    public List<UserDetailDomainDto> getAllUserDetails() {
        List<UserDetail> usersDetails = userDetailRepository.findAll();
        List<UserDetailDomainDto> dtoList = userDetailDomainDtoConverter.convertList(usersDetails);
        return dtoList;
    }

    public UserDetailDomainDto getUserDetailById(String userId) {
        UserDetail user = userDetailRepository.findByUserDetailId(userId).orElseThrow(() ->
            new EntityNotFoundException("No User found with id " + userId + ".")
        );
        UserDetailDomainDto userDetailDto = userDetailDomainDtoConverter.convert(user);
        return userDetailDto;
    }

    @Transactional(rollbackFor = Exception.class)
    public UserDetailDomainDto updateUserDetail(UserDetailUpdateRequestDto requestDto) {
        String userDetailId = requestDto.getUserDetailId();
        UserDetail userDetail =
            userDetailRepository.findByUserDetailId(userDetailId).orElseThrow(() ->
                new EntityNotFoundException("User does not exist with id: " + userDetailId)
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
        UserDetailDomainDto dto = userDetailDomainDtoConverter.convert(updatedUserDetail);
        return dto;
    }

    @Transactional(rollbackFor = Exception.class)
    public String deleteUserDetail(String userId) {
        UserDetail user = userDetailRepository.findByUserDetailId(userId).orElseThrow(() ->
            new EntityNotFoundException("User with id " + userId + " does not exist.")
        );
        user.setDeleted(true);
        userDetailRepository.save(user);
        return "Successfully deleted.";
    }
}