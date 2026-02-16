package com.github.GabrielKuiawa.libraryapi.repository;

import com.github.GabrielKuiawa.libraryapi.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
     AuthorRepository repository;

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
}
