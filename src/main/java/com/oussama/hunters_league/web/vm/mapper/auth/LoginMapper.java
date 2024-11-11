package com.oussama.hunters_league.web.vm.mapper.auth;

import com.oussama.hunters_league.domain.User;
import com.oussama.hunters_league.web.vm.user.LoginVM;
import com.oussama.hunters_league.web.vm.user.ResponseUserVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper {
    ResponseUserVM toResponseUserVM(User user);
    User toUser(LoginVM loginVM);
}
