package com.github.GabrielKuiawa.libraryapi.controller.mappers;

import com.github.GabrielKuiawa.libraryapi.controller.dto.AuthorDTO;
import com.github.GabrielKuiawa.libraryapi.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author toEntity(AuthorDTO dto);

    AuthorDTO toDTO(Author author);
}
