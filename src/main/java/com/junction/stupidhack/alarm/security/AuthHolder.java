package com.junction.stupidhack.alarm.security;

import com.junction.stupidhack.alarm.model.Auth;

public class AuthHolder {
    private static final ThreadLocal<Auth> holder = new ThreadLocal<Auth>();

    public static Auth get() {
        return holder.get();
    }

    public static void put(Auth auth) {
        holder.set(auth);
    }
}
