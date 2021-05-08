package com.junction.stupidhack.alarm.repository;

import com.junction.stupidhack.alarm.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
