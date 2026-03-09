package com.github.GabrielKuiawa.libraryapi.validator;

import com.github.GabrielKuiawa.libraryapi.exceptions.DuplicateRegisterException;
import com.github.GabrielKuiawa.libraryapi.model.Author;
import com.github.GabrielKuiawa.libraryapi.repository.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorValidator {

    private AuthorRepository repository;

    public AuthorValidator(AuthorRepository repository) {
        this.repository = repository;
    }

    public void validate(Author author) {
        if (authorRegistrationExists(author)) {
            throw new DuplicateRegisterException("The author is already registered!");
        }
    }

    private boolean authorRegistrationExists (Author author) {
        Optional<Author> authorOptional = repository.findByNameAndBirthDateAndNationality(
                author.getName(), author.getBirthDate(), author.getNationality()
        );

        if (author.getId() == null) {
            return authorOptional.isPresent();
        }

        return !author.getId().equals(authorOptional.get().getId()) && authorOptional.isPresent();
    }
}
