package com.oussama.hunters_league.service.impl;

import com.oussama.hunters_league.domain.User;
import com.oussama.hunters_league.exception.NullVarException;
import com.oussama.hunters_league.exception.User.UserAlreadyExistException;
import com.oussama.hunters_league.exception.User.UserNotFoundException;
import com.oussama.hunters_league.repository.UserRepository;
import com.oussama.hunters_league.repository.impl.UserRepositoryImpl;
import com.oussama.hunters_league.service.UserService;
import com.oussama.hunters_league.utils.PasswordEncoderUtil;
import com.oussama.hunters_league.web.vm.user.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;
    @Autowired
    private PasswordEncoderUtil passwordEncoderUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

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

    @Override
    public User login(String email, String password) {
        Optional<User> us=userRepository.findByEmail(email);
        us.orElseThrow(() -> new UserNotFoundException("Email not found"));
        if(!passwordEncoderUtil.matches(password,us.get().getPassword()))
            throw new UserNotFoundException("Password incorrect");
       /* authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,password
                )
        );*/
        return us.get();
    }

    @Override
    public User updateProfile(User user) {
        if(user.getId()==null) throw new NullVarException("id is null");
        Optional<User> us=userRepository.findById(user.getId());
        us.orElseThrow(() -> new UserAlreadyExistException("User id not exist"));
        User existingUser=us.get();

        if (user.getUsername() != null && !user.getUsername().equals(existingUser.getUsername())) {
            userRepository.findByUsername(user.getUsername()).ifPresent(u -> {
                throw new UserAlreadyExistException("Username already exists");
            });
            existingUser.setUsername(user.getUsername());
        }

        if (user.getEmail() != null && !user.getEmail().equals(existingUser.getEmail())) {
            userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
                throw new UserAlreadyExistException("Email already exists");
            });
            existingUser.setEmail(user.getEmail());
        }

        if (user.getCin() != null && !user.getCin().equals(existingUser.getCin())) {
            userRepository.findByCin(user.getCin()).ifPresent(u -> {
                throw new UserAlreadyExistException("Cin already exists");
            });
            existingUser.setCin(user.getCin());
        }
        if (user.getPassword() != null) existingUser.setPassword(passwordEncoderUtil.encodePassword(user.getPassword()) );
        if (user.getFirstName() != null) existingUser.setFirstName(user.getFirstName());
        if (user.getLastName() != null) existingUser.setLastName(user.getLastName());
        if (user.getNationality() != null) existingUser.setNationality(user.getNationality());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(UUID id) {
        if(id==null) throw new NullVarException("id is null");
        Optional<User> user=userRepository.findById(id);
        user.orElseThrow( ()-> new UserNotFoundException("User not exists with this id"));
        userRepository.delete(user.get());
    }

    @Override
    public List<User> searchUser(String search) {
        if(search==null) throw new NullVarException("search is null");
        List<User> listUser= userRepository.findByFirstNameOrLastNameOrEmail(search,search,search);
        if(listUser.isEmpty()) throw new UserNotFoundException("Users not found");
        return listUser;
    }

    @Override
    public List<User> findByCriteria(SearchDTO searchDTO) {
        return userRepositoryImpl.findByCriteria(searchDTO);
    }
}
