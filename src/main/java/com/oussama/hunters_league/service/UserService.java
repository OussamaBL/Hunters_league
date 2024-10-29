package com.oussama.hunters_league.service;

import com.oussama.hunters_league.domain.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsernameOrEmail(String username, String email);
}
