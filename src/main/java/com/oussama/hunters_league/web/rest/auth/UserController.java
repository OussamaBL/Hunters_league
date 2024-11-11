package com.oussama.hunters_league.web.rest.auth;

import com.oussama.hunters_league.domain.User;
import com.oussama.hunters_league.service.impl.UserServiceImpl;
import com.oussama.hunters_league.web.vm.user.*;
import com.oussama.hunters_league.web.vm.mapper.auth.LoginMapper;
import com.oussama.hunters_league.web.vm.mapper.auth.ProfileMapper;
import com.oussama.hunters_league.web.vm.mapper.auth.RegisterMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final RegisterMapper registerMapper;
    private final LoginMapper loginMapper;
    private final ProfileMapper profileMapper;

    public UserController(UserServiceImpl userServiceImpl, RegisterMapper registerMapper, LoginMapper loginMapper, ProfileMapper profileMapper){
        this.userServiceImpl=userServiceImpl;
        this.registerMapper=registerMapper;
        this.loginMapper=loginMapper;
        this.profileMapper=profileMapper;
    }

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

    @Transactional
    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<Map<String,Object>> Delete(@PathVariable UUID id){
        userServiceImpl.deleteUser(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/Search")
    public ResponseEntity<Map<String,Object>> Search(@RequestParam String param){
        List<User> listUser= userServiceImpl.searchUser(param);
        List<ResponseUserVM> listUserVM=new ArrayList<>();
        listUser.forEach(user->{ listUserVM.add(registerMapper.toResponseUserVM(user)); });
        Map<String, Object> response = new HashMap<>();
        response.put("data", listUserVM);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/findByCriteria")
    public ResponseEntity<List<ResponseUserVM>> findByCriteria(@RequestBody @Valid SearchDTO searchDTO){
        List<User> listUser=userServiceImpl.findByCriteria(searchDTO);
        List<ResponseUserVM> responseUserVMList=listUser.stream().map((user)->registerMapper.toResponseUserVM(user)).collect(Collectors.toList());
        return new ResponseEntity<>(responseUserVMList,HttpStatus.OK);
    }

}
