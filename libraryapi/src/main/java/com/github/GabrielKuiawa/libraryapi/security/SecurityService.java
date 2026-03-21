package com.github.GabrielKuiawa.libraryapi.security;

import com.github.GabrielKuiawa.libraryapi.model.User;
import com.github.GabrielKuiawa.libraryapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityService {

    private final UserService service;

    public User getUerLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)  authentication.getPrincipal();
        String login = userDetails.getUsername();
        return service.getByLogin(login);
    }
}
