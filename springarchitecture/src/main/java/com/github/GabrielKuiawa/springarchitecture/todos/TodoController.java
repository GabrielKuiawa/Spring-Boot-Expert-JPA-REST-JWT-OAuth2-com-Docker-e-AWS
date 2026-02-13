package com.github.GabrielKuiawa.springarchitecture.todos;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("todos")
public class TodoController {

    private TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @PostMapping
    public TodoEntity save(@RequestBody TodoEntity todo) {
        return this.service.saveTodo(todo);
    }

    @PutMapping("{id}")
    public void updateStatus (
            @PathVariable("id") Integer id, @RequestBody TodoEntity todo
    ) {
        todo.setId(id);
        service.updateStatus(todo);
    }

    @GetMapping("{id}")
    public  TodoEntity get(@PathVariable("id") Integer id) {
        return  service.getById(id);
    }
}
