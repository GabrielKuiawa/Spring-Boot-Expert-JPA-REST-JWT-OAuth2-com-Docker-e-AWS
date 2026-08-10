package com.github.GabrielKuiawa.libraryapi.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UserDTO(
        @NotBlank(message = "Required field!")
        String login,
        @NotBlank(message = "Required field!")
        @Email(message = "Invalid!")
        String email,
        @NotBlank(message = "Required field!")
        String password,
        List<String> roles) {
}
