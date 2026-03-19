package com.github.GabrielKuiawa.libraryapi.validator;

import com.github.GabrielKuiawa.libraryapi.exceptions.DuplicateRegisterException;
import com.github.GabrielKuiawa.libraryapi.exceptions.InvalidFieldException;
import com.github.GabrielKuiawa.libraryapi.model.Book;
import com.github.GabrielKuiawa.libraryapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookValidator {

    private final BookRepository repository;

    private static final int PRICE_REQUIRED_YEAR = 2020;

    public void validate(Book book) {
        if (exitsBookWithIsbn(book)) {
            throw new DuplicateRegisterException("Exists Isbn!");
        }

        if(isPriceMissing(book)) {
            throw new InvalidFieldException("price",
                    "For books published in or after 2020, the price is mandatory.");
        }
    }

    private boolean isPriceMissing(Book book) {
        return book.getPrice() == null && book.getPublicationDate().getYear() >= PRICE_REQUIRED_YEAR;
    }

    private boolean exitsBookWithIsbn(Book book) {
        Optional<Book> bookFound = repository.findByIsbn(book.getIsbn());

        if (book.getId() == null) {
            return bookFound.isPresent();
        }

        return bookFound
                .map(Book::getId)
                .stream()
                .anyMatch(id -> !id.equals(book.getId()));

    }
}
