package com.github.GabrielKuiawa.springarchitecture;

import com.github.GabrielKuiawa.springarchitecture.todos.*;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

public class DependencyInjectionExample {
    public static void main(String[] args) throws  Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("url");
        dataSource.setUrl("user");
        dataSource.setPassword("password");

        Connection connection = dataSource.getConnection();

        EntityManager entityManager = null;
        TodoRepository repository = null;//new SimpleJpaRepository<TodoEntity,Integer>(null);
        TodoValidator todoValidator = new TodoValidator(repository);
        MailSender sender = new MailSender();
        TodoService todoService = new TodoService(repository,todoValidator, sender);
    }
}
