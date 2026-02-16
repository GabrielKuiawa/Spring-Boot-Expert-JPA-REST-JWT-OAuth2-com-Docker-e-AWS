package com.github.GabrielKuiawa.libraryapi;

import com.github.GabrielKuiawa.libraryapi.model.Author;
import com.github.GabrielKuiawa.libraryapi.repository.AuthorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		var context = SpringApplication.run(Application.class, args);
		AuthorRepository repository = context.getBean(AuthorRepository.class);
		exampleSaveRepository(repository);
	}

	public static void  exampleSaveRepository(AuthorRepository authorRepository) {
		Author author = new Author();
		author.setName("Fiodor");
		author.setNationality("Russian");
		author.setBirthDate(LocalDate.of(1824,5,12));

		var authorSava = authorRepository.save(author);
		System.out.println("Author Save: " + authorSava);
	}

}
