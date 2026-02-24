package com.github.GabrielKuiawa.libraryapi.service;

import com.github.GabrielKuiawa.libraryapi.model.Author;
import com.github.GabrielKuiawa.libraryapi.model.Book;
import com.github.GabrielKuiawa.libraryapi.model.GenreBook;
import com.github.GabrielKuiawa.libraryapi.repository.AuthorRepository;
import com.github.GabrielKuiawa.libraryapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransactionalService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public  void execute () {
        Author author = new Author();
        author.setName("Machado");
        author.setNationality("Brazilean");
        author.setBirthDate(LocalDate.of(2000,5,12));

        authorRepository.save(author);

        Book book = new Book();
        book.setIsbn("93484-68866");
        book.setPrice(BigDecimal.valueOf(200));
        book.setGenre(GenreBook.MISTERIO);
        book.setTitle("Machado books");
        book.setPublicationDate(LocalDate.of(1967,3,2));

        book.setAuthor(author);

        bookRepository.save(book);

        if (author.getName().equals("Machado")) {
            throw  new RuntimeException("Rollback");
        }
    }
}
