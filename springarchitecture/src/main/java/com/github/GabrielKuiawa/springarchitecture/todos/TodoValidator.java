package com.github.GabrielKuiawa.springarchitecture.todos;

import org.springframework.stereotype.Component;

@Component
public class TodoValidator {
    private  TodoRepository repository;

    public TodoValidator(TodoRepository repository) {
        this.repository = repository;
    }

    public  void validate(TodoEntity todo) {
        if (existsTodoDescription(todo.getDescription())) {
            throw  new IllegalArgumentException("There is already a TODO with that description.");
        }
    }

    private  boolean existsTodoDescription(String description) {
        return repository.existsByDescription(description);
    }
}
