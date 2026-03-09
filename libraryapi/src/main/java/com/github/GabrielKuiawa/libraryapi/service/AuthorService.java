package com.github.GabrielKuiawa.libraryapi.service;

import com.github.GabrielKuiawa.libraryapi.model.Author;
import com.github.GabrielKuiawa.libraryapi.repository.AuthorRepository;
import com.github.GabrielKuiawa.libraryapi.validator.AuthorValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    private final AuthorRepository repository;
    private final AuthorValidator validator;

    public  AuthorService(AuthorRepository repository, AuthorValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Author save(Author author) {
        validator.validate(author);
        return repository.save(author);
    }

    public void update(Author author) {
        if (author.getId() == null){
            throw new IllegalArgumentException("Required ID");
        }
        validator.validate(author);
        repository.save(author);
    }

    public Optional<Author> getById(UUID id) {
        return  repository.findById(id);
    }

    public void delete(Author author) {
        repository.delete(author);
    }

    public List<Author> search(String name, String nationality){
        if (name != null && nationality != null) {
            return repository.findByNameAndNationality(name, nationality);
        }

        if (name != null) {
            return repository.findByName(name);
        }

        if (nationality != null) {
            return repository.findByNationality(nationality);
        }

        return repository.findAll();
    }
}
