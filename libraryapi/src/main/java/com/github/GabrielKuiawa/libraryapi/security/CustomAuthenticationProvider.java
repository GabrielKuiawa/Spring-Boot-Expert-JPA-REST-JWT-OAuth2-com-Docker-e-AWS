package com.github.GabrielKuiawa.libraryapi.security;

import com.github.GabrielKuiawa.libraryapi.model.User;
import com.github.GabrielKuiawa.libraryapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder encoder;

    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

        User userFound = userService.getByLogin(login);

        if (userFound == null) {
            throw getErrorUserNotFound();
        }

        String encryptedPassword = userFound.getPassword();

        boolean passwordsMatch = encoder.matches(password, encryptedPassword);

        if (passwordsMatch) {
            return new CustomAuthentication(userFound);
        }

        throw getErrorUserNotFound();
    }

    private UsernameNotFoundException getErrorUserNotFound() {
        return new UsernameNotFoundException("Incorrect username and/or password!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
