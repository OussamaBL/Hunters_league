package com.oussama.hunters_league.web.vm.mapper;

import com.oussama.hunters_league.domain.User;
import com.oussama.hunters_league.web.vm.RegisterVM;
import com.oussama.hunters_league.web.vm.ResponseUserVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RegisterMapper {
    ResponseUserVM toResponseUserVM(User user);
    User toUser(RegisterVM userVM);
}
