package com.github.GabrielKuiawa.libraryapi.controller;

import com.github.GabrielKuiawa.libraryapi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;
}
