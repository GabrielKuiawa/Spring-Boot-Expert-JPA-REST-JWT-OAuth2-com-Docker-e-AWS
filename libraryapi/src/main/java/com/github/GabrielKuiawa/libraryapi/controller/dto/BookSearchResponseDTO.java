package com.github.GabrielKuiawa.libraryapi.controller.dto;

import com.github.GabrielKuiawa.libraryapi.model.GenreBook;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BookSearchResponseDTO(
        String isbn,
        String title,
        LocalDate publicationDate,
        GenreBook genre,
        BigDecimal price,
        AuthorDTO author
        ) {
}
