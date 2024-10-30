package com.oussama.hunters_league.web.vm.mapper;

import com.oussama.hunters_league.domain.User;
import com.oussama.hunters_league.web.vm.UserVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "nationality", target = "nationality")
    @Mapping(source = "joinDate", target = "joinDate")
    @Mapping(source = "licenseExpirationDate", target = "licenseExpirationDate")
    UserVM toUserVM(User user);

    User toUser(UserVM userVM);
}
