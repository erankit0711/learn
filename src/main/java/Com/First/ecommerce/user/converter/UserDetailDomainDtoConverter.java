package Com.First.ecommerce.user.converter;

import Com.First.ecommerce.user.domain.UserDetail;
import Com.First.ecommerce.user.dto.UserDetailDomainDto;
import Com.First.ecommerce.user.dto.UserDetailDomainDtoBuilder;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

@Component
public class UserDetailDomainDtoConverter implements Converter<UserDetail, UserDetailDomainDto> {
    @Override
    public UserDetailDomainDto convert(UserDetail userDetail) {
        if(Objects.isNull(userDetail)){
            return null;
        }
        return new UserDetailDomainDtoBuilder()
                .setUserDetailId(userDetail.getUserDetailId())
                .setLastName(userDetail.getLastName())
                .setFirstName(userDetail.getFirstName())
                .setPhoneNumber(userDetail.getPhoneNumber())
                .build();
    }
    public List<UserDetailDomainDto> convertList(List<UserDetail> users){
        List<UserDetailDomainDto> userDetailDomainDtos = new ArrayList<>();
        users.forEach(user->{
            userDetailDomainDtos.add(convert(user));
        });
        return userDetailDomainDtos;
    }
}
