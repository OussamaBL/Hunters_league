package com.oussama.hunters_league.web.rest;

import com.oussama.hunters_league.service.UserService;
import com.oussama.hunters_league.service.impl.UserServiceImpl;
import jakarta.persistence.NamedStoredProcedureQueries;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;



}
