package com.github.GabrielKuiawa.libraryapi.controller;

import com.github.GabrielKuiawa.libraryapi.controller.dto.AuthorDTO;
import com.github.GabrielKuiawa.libraryapi.controller.dto.ResponseError;
import com.github.GabrielKuiawa.libraryapi.controller.mappers.AuthorMapper;
import com.github.GabrielKuiawa.libraryapi.exceptions.DuplicateRegisterException;
import com.github.GabrielKuiawa.libraryapi.exceptions.OperationNotPermittedException;
import com.github.GabrielKuiawa.libraryapi.model.Author;
import com.github.GabrielKuiawa.libraryapi.model.User;
import com.github.GabrielKuiawa.libraryapi.security.SecurityService;
import com.github.GabrielKuiawa.libraryapi.service.AuthorService;
import com.github.GabrielKuiawa.libraryapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("authors")
@RequiredArgsConstructor
public class AuthorController implements GenericController {

    private final AuthorService service;
    private final AuthorMapper mapper;

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid AuthorDTO dto) {

        Author author = mapper.toEntity(dto);
        service.save(author);
        URI location = generateHeaderLocation(author.getId());
        return ResponseEntity.created(location).build();
    }

    @PreAuthorize("hasAnyRole('MANAGER','OPERATOR')")
    @GetMapping("{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable String id) {
        var idAuthor = UUID.fromString(id);
        return service
                .getById(idAuthor)
                .map(author -> {
                    AuthorDTO dto = mapper.toDTO(author);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        var idAuthor = UUID.fromString(id);
        Optional<Author> authorOptional = service.getById(idAuthor);

        if (authorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.delete(authorOptional.get());
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('MANAGER','OPERATOR')")
    @GetMapping
    public ResponseEntity<List<AuthorDTO>> search(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "nationality", required = false) String nationality) {
        List<Author> result = service.searchByExample(name, nationality);
        List<AuthorDTO> list = result
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("{id}")
    public ResponseEntity<Void> update(
            @PathVariable String id, @RequestBody @Valid AuthorDTO dto) {
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
    }

}
