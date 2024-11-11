package com.oussama.hunters_league.service;

import com.oussama.hunters_league.domain.User;
import com.oussama.hunters_league.web.vm.user.SearchDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Optional<User> findByUsernameOrEmailOrCin(String username, String email, String cin);
    List<User> searchUser(String search);

    User addUser(User user);
    User login(String username,String password);

    User updateProfile(User user);
    void deleteUser(UUID id);

    List<User> findByCriteria(SearchDTO searchDTO);

}
