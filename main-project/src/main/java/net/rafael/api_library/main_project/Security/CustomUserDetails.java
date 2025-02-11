package net.rafael.api_library.main_project.Security;

import net.rafael.api_library.main_project.Models.User;
import net.rafael.api_library.main_project.Services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetails implements UserDetailsService {

    private final UserService service;

    public CustomUserDetails(UserService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = service.findByLogin(login);

        if(user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(user.getRoles().toArray(new String[user.getRoles().size()]))
                .build();
    }
}
