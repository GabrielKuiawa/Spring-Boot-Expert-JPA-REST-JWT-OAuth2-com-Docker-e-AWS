package com.github.GabrielKuiawa.libraryapi.controller.mappers;

import com.github.GabrielKuiawa.libraryapi.controller.dto.UserDTO;
import com.github.GabrielKuiawa.libraryapi.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO dto);
}
