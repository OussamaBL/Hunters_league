package com.oussama.hunters_league.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderUtil {
    private PasswordEncoder passwordEncoder;
    public PasswordEncoderUtil(){
        this.passwordEncoder=new BCryptPasswordEncoder();
    }
    public String encodePassword(String currentPassword) {
        return passwordEncoder.encode(currentPassword);
    }

    public boolean matches(String currentPassword, String encodedPassword) {
        return passwordEncoder.matches(currentPassword, encodedPassword);
    }
}
