package com.github.GabrielKuiawa.libraryapi.controller;

import com.github.GabrielKuiawa.libraryapi.controller.dto.UserDTO;
import com.github.GabrielKuiawa.libraryapi.controller.mappers.UserMapper;
import com.github.GabrielKuiawa.libraryapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid UserDTO dto) {
        var user = mapper.toEntity(dto);
        service.save(user);
    }
}
