package com.junction.stupidhack.alarm.security;

import com.junction.stupidhack.alarm.model.Auth;
import com.junction.stupidhack.alarm.repository.AuthRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {
    private final AuthRepository authRepository;

    public TokenService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public TokenDto generateToken(String username) {
        String token = UUID.randomUUID().toString();
        authRepository.save(new Auth(token, username));
        return new TokenDto(token);
    }
}
