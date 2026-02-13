package com.github.GabrielKuiawa.springarchitecture.todos;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private TodoRepository repository;
    private  TodoValidator validator;
    private  MailSender mailSender;

    public TodoService(TodoRepository todoRepository,
                       TodoValidator validator,
                       MailSender mailSender) {
        this.repository = todoRepository;
        this.validator = validator;
        this.mailSender = mailSender;
    }

    public TodoEntity saveTodo(TodoEntity newTodo) {
        validator.validate(newTodo);
        return  repository.save(newTodo);
    }
    public void updateStatus(TodoEntity todo) {
        repository.save(todo);
        String status =  todo.getCompleted() == Boolean.TRUE ? "Completed" : "Not Completed";
        mailSender.sender("Todo " + todo.getDescription() +"Updated to "+ status);
    }
    public  TodoEntity getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

}
