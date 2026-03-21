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
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyRole('MANAGER','OPERATOR')")
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid BookRegistrationDTO dto) {
        Book book = mapper.toEntity(dto);
        service.save(book);
        var url = generateHeaderLocation(book.getId());
        return ResponseEntity.created(url).build();
    }

    @PreAuthorize("hasAnyRole('MANAGER','OPERATOR')")
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

    @PreAuthorize("hasAnyRole('MANAGER','OPERATOR')")
    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        return service.getById(UUID.fromString(id))
                .map( book -> {
                    service.delete(book);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('MANAGER','OPERATOR')")
    @GetMapping
    public ResponseEntity<Page<BookSearchResponseDTO>> search(
            @RequestParam(value = "isbn", required = false)
            String isbn,
            @RequestParam(value = "title", required = false)
            String title,
                @RequestParam(value = "author-name", required = false)
            String authorName,
            @RequestParam(value = "genre-book", required = false)
            GenreBook genreBook,
            @RequestParam(value = "publication-year",required = false)
            Integer publicationYear,
            @RequestParam(value = "page", defaultValue = "0")
            Integer page,
            @RequestParam(value = "size", defaultValue = "10")
            Integer size
    ) {
        Page<Book> pageResult  = service.search(
                isbn, title, authorName, genreBook, publicationYear, page, size);

        Page<BookSearchResponseDTO> result = pageResult.map(mapper::toDTO);

        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyRole('MANAGER','OPERATOR')")
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
