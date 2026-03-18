package com.github.GabrielKuiawa.libraryapi.controller.dto;

import com.github.GabrielKuiawa.libraryapi.model.GenreBook;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BookRegistrationDTO(
        @NotBlank(message = "required field")
        @ISBN
        String isbn,
        @NotBlank(message = "required field")
        String title,
        @NotNull(message = "required field")
        @Past(message = "It cannot be a future date.")
        LocalDate publicationDate,
        GenreBook genre,
        BigDecimal price,
        @NotNull(message = "required field")
        UUID idAuthor) {
}
