package com.oussama.hunters_league.web.vm.mapper.auth;

import com.oussama.hunters_league.domain.User;
import com.oussama.hunters_league.web.vm.user.RegisterVM;
import com.oussama.hunters_league.web.vm.user.ResponseUserVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterMapper {
    ResponseUserVM toResponseUserVM(User user);
    User toUser(RegisterVM userVM);
}
