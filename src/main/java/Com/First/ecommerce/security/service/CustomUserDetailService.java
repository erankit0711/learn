package Com.First.ecommerce.security.service;

import Com.First.ecommerce.user.domain.User;
import Com.First.ecommerce.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public User loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(()->
                new UsernameNotFoundException("User not found."));
        return user;
    }
}
