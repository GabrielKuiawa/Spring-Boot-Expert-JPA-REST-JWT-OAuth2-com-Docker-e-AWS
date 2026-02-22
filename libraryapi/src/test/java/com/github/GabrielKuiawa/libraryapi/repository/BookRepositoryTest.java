package com.github.GabrielKuiawa.libraryapi.repository;

import com.github.GabrielKuiawa.libraryapi.model.Author;
import com.github.GabrielKuiawa.libraryapi.model.Book;
import com.github.GabrielKuiawa.libraryapi.model.GenreBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
        book.setIsbn("553445");
        book.setPrice(BigDecimal.valueOf(200));
        book.setGenre(GenreBook.CIENCIA);
        book.setTitle("láaaaaa");
        book.setPublicationDate(LocalDate.of(1965, 4, 23));

        Author author = authorRepository
                .findById(UUID.fromString("6d3aef0b-a85a-4aa1-b808-87a4b1eeaa31"))
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
        book.setPublicationDate(LocalDate.of(1967,3,2));

        Author author = new Author();
        author.setName("Machado");
        author.setNationality("Brazilean");
        author.setBirthDate(LocalDate.of(2000,5,12));

        book.setAuthor(author);
        repository.save(book);
    }

    @Test
    void updateAuthorBookTest() {
        UUID id = UUID.fromString("4c95dcb7-d6c5-4f39-b23b-5f2001acda21");
        var bookForUpdate = repository.findById(id).orElse(null);

        UUID idAuthor = UUID.fromString("dbbd0abd-a737-418e-a008-c6ec33ae53dc");
        Author author = authorRepository.findById(idAuthor).orElse(null);

        bookForUpdate.setAuthor(author);

        repository.save(bookForUpdate);
    }

    @Test
    void deleteTest() {
        UUID id = UUID.fromString("4c95dcb7-d6c5-4f39-b23b-5f2001acda21");
        repository.deleteById(id);
    }

    @Test
    @Transactional
    void getBookTest() {
        UUID id = UUID.fromString("0bdf3b6a-add9-4cd5-b057-6a6be2936b55");
        Book book = repository.findById(id).orElse(null);
        System.out.println("Book: ");
        System.out.println(book.getTitle());

        System.out.println("Author: ");
        System.out.println(book.getAuthor().getName());
    }

    @Test
    void searchByTitleTest() {
        List<Book> list = repository.findByTitle("That's life");
        list.forEach(System.out::println);
    }

    @Test
    void searchByIsbnTest() {
        List<Book> list = repository.findByIsbn("553344-34467");
        list.forEach(System.out::println);
    }

    @Test
    void searchByTitleAndPriceTest() {
        List<Book> list = repository.findByTitleAndPrice("That's life", BigDecimal.valueOf(20));
        list.forEach(System.out::println);
    }

    @Test
    void listBooksWithQueryJPQLTest() {
        List<Book> result = repository.listAllOrderByTitleAndPrice();
        result.forEach(System.out::println);
    }

    @Test
    void listAuthorsBooksTest() {
        List<Author> result = repository.listAuthorsBooks();
        result.forEach(System.out::println);
    }

    @Test
    void listTitlesDistinctBooksTest() {
        List<String> result = repository.listNamesDistinctBooks();
        result.forEach(System.out::println);
    }

    @Test
    void listByGenreQueryParamTest() {
        var result = repository.findByGenre(GenreBook.ROMANCE,"publicationDate");
        result.forEach(System.out::println);
    }

    @Test
    void listByGenrePositionalParametersQueryParamTest() {
        var result = repository.findByGenrePositionalParameters(GenreBook.ROMANCE,"publicationDate");
        result.forEach(System.out::println);
    }

    @Test
    void deleteByGenreTest() {
        repository.deleteByGenre(GenreBook.CIENCIA);
    }

    @Test
    void updatePublicationDateTest() {
        repository.updatePublicationDate(LocalDate.of(2000,1,1));
    }

}