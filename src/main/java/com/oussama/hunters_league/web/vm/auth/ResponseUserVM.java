package com.oussama.hunters_league.web.vm.auth;

import com.oussama.hunters_league.domain.Enum.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ResponseUserVM {
    private UUID id;
    private String username;
    private String email;
    private String cin;
    private Role role;
}
