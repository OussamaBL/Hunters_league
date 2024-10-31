package com.oussama.hunters_league.service;

import com.oussama.hunters_league.domain.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsernameOrEmailOrCin(String username, String email, String cin);

    User addUser(User user);
    User login(String username,String password);

    User updateProfile(User user);
}
