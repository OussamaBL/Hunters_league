package com.oussama.hunters_league.web.rest;

import com.oussama.hunters_league.domain.User;
import com.oussama.hunters_league.service.impl.UserServiceImpl;
import com.oussama.hunters_league.web.vm.RegisterVM;
import com.oussama.hunters_league.web.vm.ResponseUserVM;
import com.oussama.hunters_league.web.vm.mapper.RegisterMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final RegisterMapper userMapper;

    public UserController(UserServiceImpl userServiceImpl, RegisterMapper userMapper){
        this.userServiceImpl=userServiceImpl;
        this.userMapper=userMapper;
    }

/*    @PostMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUsers(@RequestBody @Valid UserVM userVM){

    }*/
    @Transactional
    @PostMapping("/addUser")
    public ResponseEntity<Map<String,Object>> addUser(@RequestBody @Valid RegisterVM userVM){
        User user=userMapper.toUser(userVM);
        User us=userServiceImpl.addUser(user);
        ResponseUserVM responseUserVM=userMapper.toResponseUserVM(us);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User added successfully");
        response.put("data", responseUserVM);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
