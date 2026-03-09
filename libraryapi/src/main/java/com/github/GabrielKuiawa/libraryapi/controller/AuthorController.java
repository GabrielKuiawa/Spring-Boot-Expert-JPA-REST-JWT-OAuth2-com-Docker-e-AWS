package com.github.GabrielKuiawa.libraryapi.controller;

import com.github.GabrielKuiawa.libraryapi.controller.dto.AuthorDTO;
import com.github.GabrielKuiawa.libraryapi.controller.dto.ResponseError;
import com.github.GabrielKuiawa.libraryapi.exceptions.DuplicateRegisterException;
import com.github.GabrielKuiawa.libraryapi.exceptions.OperationNotPermittedException;
import com.github.GabrielKuiawa.libraryapi.model.Author;
import com.github.GabrielKuiawa.libraryapi.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("authors")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody AuthorDTO author){
        try {
            var authorEntity = author.mapToAuthor();
            service.save(authorEntity);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(authorEntity.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (DuplicateRegisterException e) {
            var errorDTO = ResponseError.conflict(e.getMessage());
            return ResponseEntity.status(errorDTO.status()).body(errorDTO);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable String id) {
        var idAuthor = UUID.fromString(id);
        Optional<Author> authorOptional = service.getById(idAuthor);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            AuthorDTO dto = new AuthorDTO(
                    author.getId(),
                    author.getName(),
                    author.getBirthDate(),
                    author.getNationality()
            );
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete (@PathVariable String id) {
        try {
            var idAuthor = UUID.fromString(id);
            Optional<Author> authorOptional = service.getById(idAuthor);

            if (authorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            service.delete(authorOptional.get());
            return ResponseEntity.noContent().build();
        } catch (OperationNotPermittedException e) {
            var errorDTO = ResponseError.defaultResponse(e.getMessage());
            return ResponseEntity.status(errorDTO.status()).body(errorDTO);
        }
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> search(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "nationality", required = false) String nationality) {
        List<Author> result = service.search(name,nationality);
        List<AuthorDTO> list = result
                .stream()
                .map(author -> new AuthorDTO(
                author.getId(),
                author.getName(),
                author.getBirthDate(),
                author.getNationality()
        )).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(
            @PathVariable String id, @RequestBody AuthorDTO dto) {
        try {
            var idAuthor = UUID.fromString(id);
            Optional<Author> authorOptional = service.getById(idAuthor);

            if (authorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var author = authorOptional.get();
            author.setName(dto.name());
            author.setBirthDate(dto.birthDate());
            author.setNationality(dto.nationality());

            service.update(author);

            return ResponseEntity.noContent().build();
        } catch (DuplicateRegisterException e) {
            var errorDTO = ResponseError.conflict(e.getMessage());
            return ResponseEntity.status(errorDTO.status()).body(errorDTO);
        }
    }

}
