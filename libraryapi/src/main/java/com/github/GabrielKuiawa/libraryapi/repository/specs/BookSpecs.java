package com.github.GabrielKuiawa.libraryapi.repository.specs;

import com.github.GabrielKuiawa.libraryapi.model.Author;
import com.github.GabrielKuiawa.libraryapi.model.Book;
import com.github.GabrielKuiawa.libraryapi.model.GenreBook;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
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

    public static Specification<Book> publicationYearEqual(Integer year) {
        return (root,
                query,
                cb) ->
                cb.equal(cb.function("to_char", String.class,
                        root.get("publicationDate"),cb.literal("YYYY")), year.toString());
    }

    public static Specification<Book> authorLike(String name) {
        return (root,
                query,
                cb) -> {
            Join<Object, Object> join = root.join("author", JoinType.LEFT);
            return cb.like(cb.upper(join.get("name")),"%" + name.toUpperCase() +"%");
//            return cb.like( cb.upper(root.get("author").get("name")),"%" + name.toUpperCase() +"%");
        };
    }
}
