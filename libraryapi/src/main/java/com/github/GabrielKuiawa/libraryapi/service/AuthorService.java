package com.github.GabrielKuiawa.libraryapi.service;

import com.github.GabrielKuiawa.libraryapi.exceptions.OperationNotPermittedException;
import com.github.GabrielKuiawa.libraryapi.model.Author;
import com.github.GabrielKuiawa.libraryapi.model.User;
import com.github.GabrielKuiawa.libraryapi.repository.AuthorRepository;
import com.github.GabrielKuiawa.libraryapi.repository.BookRepository;
import com.github.GabrielKuiawa.libraryapi.security.SecurityService;
import com.github.GabrielKuiawa.libraryapi.validator.AuthorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository repository;
    private final AuthorValidator validator;
    private final BookRepository bookRepository;
    private final SecurityService securityService;

    public Author save(Author author) {
        validator.validate(author);
        User user = securityService.getUerLogin();
        author.setUser(user);
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

//    public List<Author> search(String name, String nationality){
//        if (name != null && nationality != null) {
//            return repository.findByNameAndNationality(name, nationality);
//        }
//
//        if (name != null) {
//            return repository.findByName(name);
//        }
//
//        if (nationality != null) {
//            return repository.findByNationality(nationality);
//        }
//
//        return repository.findAll();
//    }

    public List<Author> searchByExample(String name, String nationality) {
        var author = new Author();
        author.setName(name);
        author.setNationality(nationality);

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Author> authorExample = Example.of(author,matcher);
        return repository.findAll(authorExample);
    }

    public boolean authorHasABook(Author author) {
        return bookRepository.existsByAuthor(author);
    }
}
