package com.github.GabrielKuiawa.libraryapi.controller.dto;

import java.time.LocalDate;

public record AuthorDTO(
        String name,
        LocalDate birthDate,
        String nationality) {
}
