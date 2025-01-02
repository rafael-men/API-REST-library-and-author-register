package net.rafael.api_library.main_project.Security;

import net.rafael.api_library.main_project.Models.User;
import net.rafael.api_library.main_project.Services.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserService service;
    private final PasswordEncoder encoder;



    public CustomAuthenticationProvider(UserService service, PasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();
        User UserFound = service.findByLogin(login);

        if(UserFound == null) {
            throw new UsernameNotFoundException("Invalid User");
        }

        String cryptoPassword = UserFound.getPassword();
        boolean equalPassword = encoder.matches(password,cryptoPassword);
        if (equalPassword) {
            return new CustomAuthentication(UserFound);
        }
        throw new UsernameNotFoundException("Invalid User, try again.");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
