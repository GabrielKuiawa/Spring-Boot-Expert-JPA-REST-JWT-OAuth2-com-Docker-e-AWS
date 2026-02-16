package com.github.GabrielKuiawa.libraryapi.repository;

import com.github.GabrielKuiawa.libraryapi.model.Author;
import com.github.GabrielKuiawa.libraryapi.model.Book;
import com.github.GabrielKuiawa.libraryapi.model.GenreBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    BookRepository repository;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void saveTest() {
        Book book = new Book();
        book.setIsbn("344444444");
        book.setPrice(BigDecimal.valueOf(200));
        book.setGenre(GenreBook.MISTERIO);
        book.setTitle("Ufoo");
        book.setPublication_date(LocalDate.of(1967,3,2));

        Author author =  authorRepository
                .findById(UUID.fromString("dbbd0abd-a737-418e-a008-c6ec33ae53dc"))
                .orElse(null);

        book.setAuthor(author);

        repository.save(book);
    }
}