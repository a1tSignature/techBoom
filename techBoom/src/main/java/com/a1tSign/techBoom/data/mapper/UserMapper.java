package com.a1tSign.techBoom.data.mapper;

import com.a1tSign.techBoom.data.dto.security.RegisterUserDTO;
import com.a1tSign.techBoom.data.dto.user.UserDTO;
import com.a1tSign.techBoom.data.entity.Role;
import com.a1tSign.techBoom.data.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "username", source = "user.username"),
            @Mapping(target = "password", source = "password"),
            @Mapping(target = "budget", source = "user.budget"),
            @Mapping(target = "XUserCoordinate", source = "user.XUserCoordinate"),
            @Mapping(target = "YUserCoordinate", source = "user.YUserCoordinate"),
            @Mapping(target = "roles", source = "roles")
    })
    User toUserEntity(RegisterUserDTO user, List<Role> roles, String password);

    @Mappings({
            @Mapping(target = "username", source = "user.userDTO"),
            @Mapping(target = "budget", source = "user.budget"),
            @Mapping(target = "XUserCoordinate", source = "user.XUSerCoordinate"),
            @Mapping(target = "YUserCoordinate", source = "user.YUserCoordinate"),
            @Mapping(target = "roles", source = "roles")
    })
    UserDTO toUserDTO(User user, List<String> roles);

}
