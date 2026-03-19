package com.github.GabrielKuiawa.libraryapi.controller;

import com.github.GabrielKuiawa.libraryapi.controller.dto.BookRegistrationDTO;
import com.github.GabrielKuiawa.libraryapi.controller.dto.BookSearchResponseDTO;
import com.github.GabrielKuiawa.libraryapi.controller.dto.ResponseError;
import com.github.GabrielKuiawa.libraryapi.controller.mappers.BookMapper;
import com.github.GabrielKuiawa.libraryapi.exceptions.DuplicateRegisterException;
import com.github.GabrielKuiawa.libraryapi.model.Book;
import com.github.GabrielKuiawa.libraryapi.model.GenreBook;
import com.github.GabrielKuiawa.libraryapi.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController implements GenericController {

    private final BookService service;
    private final BookMapper mapper;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid BookRegistrationDTO dto) {
        Book book = mapper.toEntity(dto);
        service.save(book);
        var url = generateHeaderLocation(book.getId());
        return ResponseEntity.created(url).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<BookSearchResponseDTO> getDetails(
            @PathVariable String id
    ) {
        return service.getById(UUID.fromString(id))
                .map(book -> {
                    var dto = mapper.toDTO(book);
                    return ResponseEntity.ok(dto);
                }).orElseGet( () -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        return service.getById(UUID.fromString(id))
                .map( book -> {
                    service.delete(book);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<BookSearchResponseDTO>> search(
            @RequestParam(value = "isbn", required = false)
            String isbn,
            @RequestParam(value = "title", required = false)
            String title,
                @RequestParam(value = "author-name", required = false)
            String authorName,
            @RequestParam(value = "genre-book", required = false)
            GenreBook genreBook,
            @RequestParam(value = "publication-year",required = false)
            Integer publicationYear
    ) {
        var result  = service.search(isbn, title, authorName, genreBook, publicationYear);
        var list = result
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(
            @PathVariable String id,@RequestBody @Valid BookRegistrationDTO dto) {
        return service.getById(UUID.fromString(id))
                .map(book -> {
                    Book entity = mapper.toEntity(dto);
                    book.setPublicationDate(entity.getPublicationDate());
                    book.setIsbn(entity.getIsbn());
                    book.setPrice(entity.getPrice());
                    book.setTitle(entity.getTitle());
                    book.setGenre(entity.getGenre());
                    book.setAuthor(entity.getAuthor());

                    service.update(book);

                    return ResponseEntity.noContent().build();
                }).orElseGet( () -> ResponseEntity.notFound().build());
    }

}
