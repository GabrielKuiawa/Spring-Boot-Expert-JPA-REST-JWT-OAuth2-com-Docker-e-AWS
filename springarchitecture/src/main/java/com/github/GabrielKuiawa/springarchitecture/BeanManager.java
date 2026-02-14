package com.github.GabrielKuiawa.springarchitecture;

import com.github.GabrielKuiawa.springarchitecture.todos.TodoEntity;
import com.github.GabrielKuiawa.springarchitecture.todos.TodoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class BeanManager {

    @Autowired
    private TodoValidator validator;

    @Autowired
    public BeanManager(TodoValidator validator) {
        this.validator = validator;
    }

    private void util() {
        var todo = new TodoEntity();
        validator.validate(todo);
    }

    @Autowired
    public void setValidator(TodoValidator validator) {
        this.validator = validator;
    }
}
