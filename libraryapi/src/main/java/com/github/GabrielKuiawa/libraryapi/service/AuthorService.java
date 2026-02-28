package com.github.GabrielKuiawa.libraryapi.service;

import com.github.GabrielKuiawa.libraryapi.model.Author;
import com.github.GabrielKuiawa.libraryapi.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public  AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public Author save(Author author) {
        return repository.save(author);
    }

    public Optional<Author> getById(UUID id) {
        return  repository.findById(id);
    }

    public void delete(Author author) {
        repository.delete(author);
    }
}
