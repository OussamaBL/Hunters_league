package com.oussama.hunters_league.service.impl;

import com.oussama.hunters_league.domain.User;
import com.oussama.hunters_league.exception.UserAlreadyExistException;
import com.oussama.hunters_league.repository.UserRepository;
import com.oussama.hunters_league.service.UserService;
import com.oussama.hunters_league.utils.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoderUtil passwordEncoderUtil;

    @Override
    public Optional<User> findByUsernameOrEmailOrCin(String username, String email, String cin) {
        return userRepository.findByUsernameOrEmailOrCin(username,email,cin);
    }

    @Override
    public User addUser(User user) {
        user.setPassword(passwordEncoderUtil.encodePassword(user.getPassword()));
        Optional<User> us=this.findByUsernameOrEmailOrCin(user.getUsername(),user.getEmail(),user.getCin());
        us.ifPresent(u -> { throw new UserAlreadyExistException("Username or Email or cin already exist"); });
        return userRepository.save(user);
    }
}
