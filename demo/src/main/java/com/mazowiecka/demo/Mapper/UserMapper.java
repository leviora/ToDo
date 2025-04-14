package com.mazowiecka.demo.Mapper;

import com.mazowiecka.demo.DTO.UserDTO;
import com.mazowiecka.demo.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);
}
