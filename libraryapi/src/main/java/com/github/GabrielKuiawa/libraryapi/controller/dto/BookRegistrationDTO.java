package com.github.GabrielKuiawa.libraryapi.controller.dto;

import com.github.GabrielKuiawa.libraryapi.model.GenreBook;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BookRegistrationDTO(
        String isbn,
        String title,
        LocalDate publicationDate,
        GenreBook genre,
        BigDecimal price,
        UUID idAuthor) {
}
