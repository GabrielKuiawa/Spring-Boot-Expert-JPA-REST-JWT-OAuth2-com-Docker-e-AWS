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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
     AuthorRepository repository;

    @Autowired
    BookRepository bookRepository;

    @Test
    public  void saveTest() {
        Author author = new Author();
        author.setName("Mario");
        author.setNationality("Russian");
        author.setBirthDate(LocalDate.of(2000,5,12));

        var authorSava = repository.save(author);
        System.out.println("Author Save: " + authorSava);
    }

    @Test
    public void updateTest() {
        var id = UUID.fromString("0c36e738-bf66-4822-8516-c8433b9a67da");
        Optional<Author> probableAuthor = repository.findById(id);
        if (probableAuthor.isPresent()) {
            Author author = probableAuthor.get();
            System.out.println("Data of Author: ");
            System.out.println(author);
            author.setName("Cleber");
            repository.save(author);
        }
    }

    @Test
    public void ListTest() {
        List<Author> list = repository.findAll();
        list.forEach(System.out::println);
    }

    @Test
    public void countTest() {
        System.out.println("Authors Count: " + repository.count());
    }

    @Test
    public void deleteByIdTest() {
        var id = UUID.fromString("0c36e738-bf66-4822-8516-c8433b9a67da");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest() {
        var id = UUID.fromString("41d6320b-a58d-48f2-9dc5-c024161b6ce8");
        var author = repository.findById(id).get();
        repository.delete(author);
    }

    @Test
    void saveAthorWithBookTest() {
        Author author = new Author();
        author.setName("janie");
        author.setNationality("American");
        author.setBirthDate(LocalDate.of(1875,9,30));

        Book book = new Book();
        book.setIsbn("93484-34467");
        book.setPrice(BigDecimal.valueOf(270.86));
        book.setGenre(GenreBook.BIOGRAFIA);
        book.setTitle("That's life");
        book.setPublication_date(LocalDate.of(1997,6,25));
        book.setAuthor(author);

        Book book2 = new Book();
        book2.setIsbn("553344-34467");
        book2.setPrice(BigDecimal.valueOf(243.86));
        book2.setGenre(GenreBook.ROMANCE);
        book2.setTitle("That's love");
        book2.setPublication_date(LocalDate.of(1997,6,25));
        book2.setAuthor(author);

        author.setBooks(new ArrayList<>());
        author.getBooks().add(book);
        author.getBooks().add(book2);

        System.out.println(book);
        System.out.println(book2);
        repository.save(author);
//        bookRepository.saveAll(author.getBooks());
    }

    @Test
    void listBooksAuthor() {
        var id = UUID.fromString("5a966b85-8aa2-4ee0-953c-b5dd351bf2ab");
        var author = repository.findById(id).get();

        List<Book> books = bookRepository.findByAuthor(author);
        author.setBooks(books);
        author.getBooks().forEach(System.out::println);
    }
}
