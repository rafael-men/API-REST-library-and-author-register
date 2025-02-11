package net.rafael.api_library.main_project.Security;

import net.rafael.api_library.main_project.Models.User;
import net.rafael.api_library.main_project.Services.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder encoder;

    public AuthenticationProvider(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String digitedPassword = authentication.getCredentials().toString();

        User userFound = userService.findByLogin(login);

        if(userFound == null) {
            throw new UsernameNotFoundException("Usuário ou senha incorreto");
        }

        String encodedPassword = userFound.getPassword();

        boolean matchPasswords = encoder.matches(digitedPassword,encodedPassword);

        if(matchPasswords) {
            return new CustomAuthentication(userFound);
        }

        throw new UsernameNotFoundException("Usuário ou senha incorreto");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
