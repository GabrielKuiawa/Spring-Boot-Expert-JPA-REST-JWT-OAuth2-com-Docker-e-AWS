package com.github.GabrielKuiawa.libraryapi.repository;

import com.github.GabrielKuiawa.libraryapi.model.Author;
import com.github.GabrielKuiawa.libraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    // Query Method
    List<Book> findByAuthor(Author author);

    List<Book> findByTitle(String title);

    List<Book> findByIsbn(String isbn);

    List<Book> findByTitleAndPrice(String title, BigDecimal price);

    List<Book> findByTitleOrIsbn(String title, String isbn);

    List<Book> findByPublicationDateBetween(LocalDate initial, LocalDate end);

    // select b.* from book as b order by b.title, b.price
    @Query(" select b from Book as b order by b.title, b.price ")
    List<Book> listAllOrderByTitleAndPrice();

    /**
     *select a.*
     * 	from book b
     * 	join author as a on a.id = b.id_author
     */
    @Query(" select a from Book as b join b.author a ")
    List<Author> listAuthorsBooks();

    @Query( "select distinct b.title from Book as b " )
    List<String> listNamesDistinctBooks();
}
