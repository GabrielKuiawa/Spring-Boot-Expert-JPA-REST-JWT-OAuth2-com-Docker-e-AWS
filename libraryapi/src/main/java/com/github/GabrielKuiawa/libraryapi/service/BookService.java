package com.github.GabrielKuiawa.libraryapi.service;

import com.github.GabrielKuiawa.libraryapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
}
