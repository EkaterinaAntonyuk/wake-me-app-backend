package com.junction.stupidhack.alarm.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junction.stupidhack.alarm.repository.AuthRepository;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private final AuthRepository authRepository;

    public WebConfiguration(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(
                new TokenFetchingInterceptor(authRepository)
        );
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer init() {
        return new Jackson2ObjectMapperBuilderCustomizer() {
            @Override
            public void customize(Jackson2ObjectMapperBuilder builder) {
                builder.timeZone(TimeZone.getDefault());
            }
        };
    }
}
