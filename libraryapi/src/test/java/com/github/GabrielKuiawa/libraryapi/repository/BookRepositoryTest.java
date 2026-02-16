package com.github.GabrielKuiawa.libraryapi.repository;

import com.github.GabrielKuiawa.libraryapi.model.Author;
import com.github.GabrielKuiawa.libraryapi.model.Book;
import com.github.GabrielKuiawa.libraryapi.model.GenreBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
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
        book.setPublication_date(LocalDate.of(1967, 3, 2));

        Author author = authorRepository
                .findById(UUID.fromString("dbbd0abd-a737-418e-a008-c6ec33ae53dc"))
                .orElse(null);

        book.setAuthor(author);

        repository.save(book);
    }

    @Test
    void saveCascadeTest() {
        Book book = new Book();
        book.setIsbn("93484");
        book.setPrice(BigDecimal.valueOf(200));
        book.setGenre(GenreBook.MISTERIO);
        book.setTitle("cleber in the viagens");
        book.setPublication_date(LocalDate.of(1967,3,2));

        Author author = new Author();
        author.setName("Machado");
        author.setNationality("Brazilean");
        author.setBirthDate(LocalDate.of(2000,5,12));

        book.setAuthor(author);
        repository.save(book);
    }

    @Test
    void updateAuthorBook() {
        UUID id = UUID.fromString("4c95dcb7-d6c5-4f39-b23b-5f2001acda21");
        var bookForUpdate = repository.findById(id).orElse(null);

        UUID idAuthor = UUID.fromString("dbbd0abd-a737-418e-a008-c6ec33ae53dc");
        Author author = authorRepository.findById(idAuthor).orElse(null);

        bookForUpdate.setAuthor(author);

        repository.save(bookForUpdate);
    }

    @Test
    void delete() {
        UUID id = UUID.fromString("4c95dcb7-d6c5-4f39-b23b-5f2001acda21");
        repository.deleteById(id);
    }
}