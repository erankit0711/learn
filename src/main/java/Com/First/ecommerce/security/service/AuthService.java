package Com.First.ecommerce.security.service;

import Com.First.ecommerce.security.Utils.JwtHelper;
import Com.First.ecommerce.security.dto.UserLoginRequestDto;
import Com.First.ecommerce.security.dto.UserRegisterRequestDto;
import Com.First.ecommerce.user.converter.UserDomainDtoConverter;
import Com.First.ecommerce.user.domain.User;
import Com.First.ecommerce.user.domain.UserBuilder;
import Com.First.ecommerce.user.dto.UserDomainDto;
import Com.First.ecommerce.user.repository.UserRepository;
import Com.First.ecommerce.util.Cache.CacheStore;
import Com.First.ecommerce.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    CacheStore<String> tokenCache;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDomainDtoConverter UserDomainDtoConverter;

    //Login UserServletException
    public String login(UserLoginRequestDto request) {
        this.doAuthenticate(request.getUsername(), request.getPassword());
        String token;
        if (tokenCache.get(request.getUsername()) != null) {
            token = tokenCache.get(request.getUsername());
        } else {
            User user = customUserDetailService.loadUserByUsername(request.getUsername());
            token = jwtHelper.generateToken(user);
            tokenCache.add(request.getUsername(), token);
        }
        return token;
    }

    //Register User
    @Transactional(rollbackFor = Exception.class)
    public UserDomainDto registerUser(UserRegisterRequestDto request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = new UserBuilder()
            .setEmail(request.getEmail())
            .setUsername(request.getUsername())
            .setPassword(request.getPassword())
            .build();

        User savedUser = userRepository.save(user);
        UserDomainDto data = UserDomainDtoConverter.convert(savedUser);
        return data;
    }

    public void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password!!");
        }
    }
}
