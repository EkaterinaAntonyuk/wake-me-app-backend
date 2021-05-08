package com.junction.stupidhack.alarm.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String token;

    private String userId;

    public Auth(String token, String userId) {
        this.token = token;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getUserId() {
        return userId;
    }
}
