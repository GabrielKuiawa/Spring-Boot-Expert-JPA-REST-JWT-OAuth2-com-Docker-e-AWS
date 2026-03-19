package com.github.GabrielKuiawa.libraryapi.service;

import com.github.GabrielKuiawa.libraryapi.model.Book;
import com.github.GabrielKuiawa.libraryapi.model.GenreBook;
import com.github.GabrielKuiawa.libraryapi.repository.BookRepository;
import com.github.GabrielKuiawa.libraryapi.repository.specs.BookSpecs;
import com.github.GabrielKuiawa.libraryapi.validator.BookValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.github.GabrielKuiawa.libraryapi.repository.specs.BookSpecs.*;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final BookValidator validator;

    public Book save(Book book) {
        validator.validate(book);
        return repository.save(book);
    }

    public Optional<Book> getById(UUID id) {
        return repository.findById(id);
    }

    public void delete(Book book) {
        repository.delete(book);
    }

    public List<Book> search(
            String isbn,
            String title,
            String authorName,
            GenreBook genre,
            Integer publicationYear
    ) {
//        Specification<Book> specs = Specification
//                .where(BookSpecs.isbnEqual(isbn))
//                .and(BookSpecs.titleLike(title))
//                .and(BookSpecs.genreEqual(genre));

        Specification<Book> specs = Specification
                .where((root,
                        query,
                        cb) ->  cb.conjunction());

        if (isbn != null) {
            specs = specs.and(isbnEqual(isbn));
        }

        if (title != null) {
            specs = specs.and(titleLike(title));
        }

        if (genre != null) {
            specs = specs.and(genreEqual(genre));
        }

        if (publicationYear != null) {
            specs = specs.and(publicationYearEqual(publicationYear));
        }

        if (authorName != null) {
            specs = specs.and(authorLike(authorName));
        }
        return repository.findAll(specs);
    }

    public void update(Book book) {
        if (book.getId() == null) {
            throw new IllegalArgumentException("Required ID");
        }
        validator.validate(book);
        repository.save(book);
    }
}
