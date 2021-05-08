package com.junction.stupidhack.alarm.api;

import com.junction.stupidhack.alarm.security.AuthDto;
import com.junction.stupidhack.alarm.security.AuthService;
import com.junction.stupidhack.alarm.security.TokenDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthApi {
    private final AuthService authService;

    public AuthApi(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public TokenDto register(@RequestBody AuthDto authDto) {
        return authService.register(authDto);
    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody AuthDto authDto) {
        return authService.login(authDto);
    }
}
