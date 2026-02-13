package com.github.GabrielKuiawa.springarchitecture.todos;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private TodoRepository repository;

    public TodoService(TodoRepository todoRepository) {
        this.repository = todoRepository;
    }

    public TodoEntity saveTodo(TodoEntity newTodo) {
        return  repository.save(newTodo);
    }
    public void updateStatus(TodoEntity todos) {
        repository.save(todos);
    }
    public  TodoEntity getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

}
