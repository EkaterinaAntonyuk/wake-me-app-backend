package com.junction.stupidhack.alarm.configuration;

import com.junction.stupidhack.alarm.model.Auth;
import com.junction.stupidhack.alarm.repository.AuthRepository;
import com.junction.stupidhack.alarm.security.AuthHolder;
import com.junction.stupidhack.alarm.security.TokenService;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class TokenFetchingInterceptor implements HandlerInterceptor {
    private final AuthRepository authRepository;

    public TokenFetchingInterceptor(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!request.getRequestURI().contains("alarms")) {
            return true;
        }
        String token = request.getHeader("token");
        if (token == null) {
            response.setStatus(401);
            return false;
        }
        Optional<Auth> maybeToken = authRepository.findByToken(token);
        if (maybeToken.isPresent()) {
            AuthHolder.put(maybeToken.get());
            return true;
        }
        response.setStatus(401);
        return false;
    }
}
