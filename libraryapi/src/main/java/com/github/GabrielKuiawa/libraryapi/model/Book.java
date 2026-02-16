package com.github.GabrielKuiawa.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "book")
@Data
public class Book {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn", length = 20, nullable = false)
    private String isbn;

    @Column(name = "title", length = 150, nullable = false)
    private String title;

    @Column(name = "publication_date")
    private LocalDate publication_date;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre", length = 30, nullable = false)
    private GenreBook genre;

    @Column(name = "price",precision = 18, scale = 2)
    private BigDecimal price;

    @ManyToOne(
            //cascade = CascadeType.ALL
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "id_author")
    private Author author;

}
