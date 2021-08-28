package uz.mehrojbek.appbookshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.mehrojbek.appbookshop.payload.ApiResult;
import uz.mehrojbek.appbookshop.payload.LoginDto;
import uz.mehrojbek.appbookshop.service.AuthService;
import uz.mehrojbek.appbookshop.utils.RestConstants;

@RestController
@RequestMapping(RestConstants.AUTH_CONTROLLER)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ApiResult<?> login(LoginDto loginDto){
        return authService.login(loginDto);
    }

}
