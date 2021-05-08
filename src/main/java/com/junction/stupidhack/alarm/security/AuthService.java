package com.junction.stupidhack.alarm.security;

import com.junction.stupidhack.alarm.model.User;
import com.junction.stupidhack.alarm.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final TokenService tokenService;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder encoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.tokenService = tokenService;
    }

    public TokenDto register(AuthDto authDto) {
        userRepository.save(
                new User(authDto.getLogin(), encoder.encode(authDto.getPassword()))
        );
        return tokenService.generateToken(authDto.getLogin());
    }

    public TokenDto login(AuthDto authDto) {
        Optional<User> mayBeUser = userRepository.findById(authDto.getLogin());
        if (mayBeUser.isPresent()) {
            User user = mayBeUser.get();
            if (encoder.matches(authDto.getPassword(), user.getPassword())) {
                return tokenService.generateToken(authDto.getLogin());
            }
        }
        throw new RuntimeException("Bad auth");
    }
}
