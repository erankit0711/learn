package Com.First.CrudApp.Security.Controller;

import Com.First.CrudApp.Security.Model.JwtRequest;
import Com.First.CrudApp.Security.Service.CustomUserDetailService;
import Com.First.CrudApp.Security.Utils.JwtHelper;
import Com.First.CrudApp.Util.Cache.CacheStore;
import Com.First.CrudApp.Util.CustomResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AuthController {
    @Autowired
    private CustomUserDetailService userDetailService;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    CacheStore<String> tokenCache;
    private Logger logger = LoggerFactory.getLogger(AuthController.class);
    @GetMapping("test-zero")
    public String testZero(){
        return "No authentication required";
    }
    @GetMapping("test-one")
    public String testOne(){
        return "ADMIN Role based authentication required";
    }
    @GetMapping("test-two")
    public String testTwo(){
        return "USER Role based authentication required";
    }
    @PostMapping("/users/login")
    public ResponseEntity<CustomResponse<String>> login(@RequestBody JwtRequest request){
        this.doAuthenticate(request.getUsername(), request.getPassword());
        String token;
        if(tokenCache.get(request.getUsername()) != null){
            token = tokenCache.get(request.getUsername());
        }else{
            UserDetails userDetails = userDetailService.loadUserByUsername(request.getUsername());
            token = jwtHelper.generateToken(userDetails);
            tokenCache.add(request.getUsername(), token);
        }
        CustomResponse<String> response = CustomResponse.success(token, null, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }
    public void doAuthenticate(String email, String password){
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try{
            manager.authenticate(authentication);
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Invalid username or password!!");
        }
    }

}
