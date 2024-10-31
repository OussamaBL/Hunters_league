package com.oussama.hunters_league.web.rest.auth;

import com.oussama.hunters_league.domain.User;
import com.oussama.hunters_league.service.impl.UserServiceImpl;
import com.oussama.hunters_league.web.vm.auth.LoginVM;
import com.oussama.hunters_league.web.vm.auth.ProfileVM;
import com.oussama.hunters_league.web.vm.auth.RegisterVM;
import com.oussama.hunters_league.web.vm.ResponseUserVM;
import com.oussama.hunters_league.web.vm.mapper.auth.LoginMapper;
import com.oussama.hunters_league.web.vm.mapper.auth.ProfileMapper;
import com.oussama.hunters_league.web.vm.mapper.auth.RegisterMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/users")
public class AuthController {

    private final UserServiceImpl userServiceImpl;
    private final RegisterMapper registerMapper;
    private final LoginMapper loginMapper;
    private final ProfileMapper profileMapper;

    public AuthController(UserServiceImpl userServiceImpl, RegisterMapper registerMapper, LoginMapper loginMapper,ProfileMapper profileMapper){
        this.userServiceImpl=userServiceImpl;
        this.registerMapper=registerMapper;
        this.loginMapper=loginMapper;
        this.profileMapper=profileMapper;
    }

/*    @PostMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUsers(@RequestBody @Valid UserVM userVM){

    }*/
    @Transactional
    @PostMapping("/Register")
    public ResponseEntity<Map<String,Object>> Register(@RequestBody @Valid RegisterVM userVM){
        User user=registerMapper.toUser(userVM);
        User us=userServiceImpl.addUser(user);
        ResponseUserVM responseUserVM=registerMapper.toResponseUserVM(us);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User added successfully");
        response.put("data", responseUserVM);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping("/Login")
    public ResponseEntity<Map<String,Object>> Login(@RequestBody @Valid LoginVM loginVM){
        User user=loginMapper.toUser(loginVM);
        User us=userServiceImpl.login(user.getEmail(),user.getPassword());
        ResponseUserVM responseUserVM=loginMapper.toResponseUserVM(us);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User Login successfully");
        response.put("data", responseUserVM);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/Profile/{id}")
    public ResponseEntity<Map<String,Object>> Profile(@RequestBody @Valid ProfileVM profileVM, @PathVariable UUID id){
        User user=profileMapper.toUser(profileVM);
        user.setId(id);
        User userUpdated=userServiceImpl.updateProfile(user);

        ResponseUserVM responseUserVM=profileMapper.toResponseUserVM(userUpdated);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User updated successfully");
        response.put("data", responseUserVM);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
