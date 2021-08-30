package uz.mehrojbek.appbookshop.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.mehrojbek.appbookshop.entity.User;
import uz.mehrojbek.appbookshop.exception.RestException;
import uz.mehrojbek.appbookshop.payload.ApiResult;
import uz.mehrojbek.appbookshop.payload.LoginDto;
import uz.mehrojbek.appbookshop.repository.UserRepository;
import uz.mehrojbek.appbookshop.security.JwtProvider;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public AuthService(UserRepository userRepository, @Lazy AuthenticationManager authenticationManager, @Lazy JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    public ApiResult<?> login(LoginDto loginDto) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            User user = (User) authenticate.getPrincipal();
            String accessToken = jwtProvider.generateToken(user.getId(),30);
            return ApiResult.successRespWithData(accessToken);
        } catch (Exception e) {
            return new ApiResult<>("Parol yoki login xato",false);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(s);
        return optionalUser.orElseThrow(() -> new RestException("login yoki parol xato", HttpStatus.UNAUTHORIZED));
    }

    public UserDetails loadUserById(String s) throws UsernameNotFoundException {
        return userRepository.findById(UUID.fromString(s)).orElseThrow(() -> new RestException("login yoki parol xato", HttpStatus.UNAUTHORIZED));
    }

}
