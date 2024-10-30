package com.oussama.hunters_league.web.rest;

import com.oussama.hunters_league.domain.User;
import com.oussama.hunters_league.service.impl.UserServiceImpl;
import com.oussama.hunters_league.web.vm.UserVM;
import com.oussama.hunters_league.web.vm.mapper.UserMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    private UserMapper userMapper;

    public UserController(UserMapper userMapper){
        this.userMapper=userMapper;
    }

/*    @PostMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUsers(@RequestBody @Valid UserVM userVM){

    }*/
    @Transactional
    @PostMapping("/addUser")
    public ResponseEntity<UserVM> addUser(@RequestBody @Valid UserVM userVM){
        User user=userMapper.toUser(userVM);
        User us=userServiceImpl.addUser(user);
        UserVM userResponseVM=userMapper.toUserVM(us);
        return new ResponseEntity<>(userResponseVM, HttpStatus.CREATED);
    }

}
