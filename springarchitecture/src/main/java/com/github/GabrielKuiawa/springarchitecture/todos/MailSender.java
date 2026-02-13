package com.github.GabrielKuiawa.springarchitecture.todos;

import org.springframework.stereotype.Component;

@Component
public class MailSender {

    public void sender(String message) {
        System.out.println("Send email: " + message);
    }
}
