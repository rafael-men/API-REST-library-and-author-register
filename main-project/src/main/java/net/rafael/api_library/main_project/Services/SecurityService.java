package net.rafael.api_library.main_project.Services;


import net.rafael.api_library.main_project.Models.User;
import net.rafael.api_library.main_project.Security.CustomAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityService {

    private final UserService service;

    public SecurityService(UserService service) {
        this.service = service;
    }

    public User findByLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication instanceof CustomAuthentication customAuth) {
            return customAuth.getUser();
        }
        return null;
    }

}
