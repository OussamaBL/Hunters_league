package com.oussama.hunters_league.web.vm.mapper.auth;

import com.oussama.hunters_league.domain.User;
import com.oussama.hunters_league.web.vm.user.ResponseUserVM;
import com.oussama.hunters_league.web.vm.user.ProfileVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ResponseUserVM toResponseUserVM(User user);
    User toUser(ProfileVM profileVM);
}
