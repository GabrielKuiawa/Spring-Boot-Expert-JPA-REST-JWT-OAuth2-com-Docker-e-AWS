package com.github.GabrielKuiawa.libraryapi.controller.dto;

import com.github.GabrielKuiawa.libraryapi.model.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO(
        UUID id,
        @NotBlank(message = "required field")
        String name,
        @NotNull(message = "required field")
        LocalDate birthDate,
        @NotBlank(message = "required field")
        String nationality) {

    public Author mapToAuthor() {
        Author author = new Author();
        author.setName(this.name);
        author.setBirthDate(this.birthDate);
        author.setNationality(this.nationality);
        return author;
    }
}
