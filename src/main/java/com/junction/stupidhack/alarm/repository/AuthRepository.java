package com.junction.stupidhack.alarm.repository;

import com.junction.stupidhack.alarm.model.Auth;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthRepository extends CrudRepository<Auth, Long> {
    Optional<Auth> findByToken(String token);
}
