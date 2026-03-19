package com.github.GabrielKuiawa.libraryapi.controller.mappers;

import com.github.GabrielKuiawa.libraryapi.controller.dto.BookRegistrationDTO;
import com.github.GabrielKuiawa.libraryapi.controller.dto.BookSearchResponseDTO;
import com.github.GabrielKuiawa.libraryapi.model.Book;
import com.github.GabrielKuiawa.libraryapi.repository.AuthorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = AuthorMapper.class)
public abstract class BookMapper {

    @Autowired
    AuthorRepository repository;

    @Mapping(target = "author", expression = "java( repository.findById(dto.idAuthor()).orElse(null) )")
    public abstract Book toEntity(BookRegistrationDTO dto);

    public abstract BookSearchResponseDTO toDTO(Book book);
}
