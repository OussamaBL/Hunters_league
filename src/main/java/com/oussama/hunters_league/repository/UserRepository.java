package com.oussama.hunters_league.repository;

import com.oussama.hunters_league.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsernameOrEmailOrCin(String username, String email,String cin);
    List<User> findByFirstNameOrLastNameOrEmail(String firstName, String LastName, String email);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findById(UUID id);
    Optional<User> findByCin(String cin);
}
