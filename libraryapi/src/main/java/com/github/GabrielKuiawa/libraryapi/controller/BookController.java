package com.github.GabrielKuiawa.libraryapi.controller;

import com.github.GabrielKuiawa.libraryapi.controller.dto.BookRegistrationDTO;
import com.github.GabrielKuiawa.libraryapi.controller.dto.ResponseError;
import com.github.GabrielKuiawa.libraryapi.controller.mappers.BookMapper;
import com.github.GabrielKuiawa.libraryapi.exceptions.DuplicateRegisterException;
import com.github.GabrielKuiawa.libraryapi.model.Book;
import com.github.GabrielKuiawa.libraryapi.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;
    private final BookMapper mapper;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid BookRegistrationDTO dto) {
        try {
            Book book = mapper.toEntity(dto);
            service.save(book);
            return ResponseEntity.ok(dto);
        } catch (DuplicateRegisterException e) {
            ResponseError errorDTO = ResponseError.conflict(e.getMessage());
            return ResponseEntity.status(errorDTO.status()).body(errorDTO);
        }
    }

}
