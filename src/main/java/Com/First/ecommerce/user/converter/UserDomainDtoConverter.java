package Com.First.ecommerce.user.converter;

import Com.First.ecommerce.user.domain.User;
import Com.First.ecommerce.user.dto.UserDomainDto;
import Com.First.ecommerce.user.dto.UserDomainDtoBuilder;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UserDomainDtoConverter implements Converter<User, UserDomainDto> {
    @Override
    public UserDomainDto convert(User user) {
        if(Objects.isNull(user)){
            return null;
        }
        return new UserDomainDtoBuilder().setUserId(user.getUserId())
                .setEmail(user.getEmail())
                .setUsername(user.getUsername()).build();
    }
    public List<UserDomainDto> convertList(List<User> users){
        List<UserDomainDto> userDomainDtos = new ArrayList<>();
        users.forEach(user->{
            userDomainDtos.add(convert(user));
        });
        return userDomainDtos;
    }
}
