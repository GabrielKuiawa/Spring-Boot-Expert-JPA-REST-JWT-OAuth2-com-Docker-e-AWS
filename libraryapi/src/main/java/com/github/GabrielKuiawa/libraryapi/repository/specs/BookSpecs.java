package com.github.GabrielKuiawa.libraryapi.repository.specs;

import com.github.GabrielKuiawa.libraryapi.model.Book;
import com.github.GabrielKuiawa.libraryapi.model.GenreBook;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecs {

    public static Specification<Book> isbnEqual(String isbn) {
        return  (root,
                 query,
                 cb) -> cb.equal(root.get("isbn"), isbn);

    }

    public static Specification<Book> titleLike(String title) {
        return (root,
                query,
                cb) ->
                cb.like( cb.upper(root.get("title")), "%" + title.toUpperCase() + "%");
    }

    public static Specification<Book> genreEqual(GenreBook genre) {
        return (root,
                query,
                cb) -> cb.equal(root.get("genre"),genre);
    }
}
