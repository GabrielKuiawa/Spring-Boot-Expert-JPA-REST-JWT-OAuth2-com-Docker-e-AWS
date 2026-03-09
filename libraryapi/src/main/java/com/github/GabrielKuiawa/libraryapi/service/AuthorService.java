package com.github.GabrielKuiawa.libraryapi.service;

import com.github.GabrielKuiawa.libraryapi.exceptions.OperationNotPermittedException;
import com.github.GabrielKuiawa.libraryapi.model.Author;
import com.github.GabrielKuiawa.libraryapi.repository.AuthorRepository;
import com.github.GabrielKuiawa.libraryapi.repository.BookRepository;
import com.github.GabrielKuiawa.libraryapi.validator.AuthorValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    private final AuthorRepository repository;
    private final AuthorValidator validator;
    private final BookRepository bookRepository;

    public  AuthorService(
            AuthorRepository repository,
            AuthorValidator validator,
            BookRepository bookRepository) {
        this.repository = repository;
        this.validator = validator;
        this.bookRepository = bookRepository;
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
        if (authorHasABook(author)) {
            throw  new OperationNotPermittedException(
                    "It is not permitted to exclude an author who has registered books!");
        }
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

    public boolean authorHasABook(Author author) {
        return bookRepository.existsByAuthor(author);
    }
}
