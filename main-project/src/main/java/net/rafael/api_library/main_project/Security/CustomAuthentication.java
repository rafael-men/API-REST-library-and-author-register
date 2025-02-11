package net.rafael.api_library.main_project.Security;


import net.rafael.api_library.main_project.Models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomAuthentication implements Authentication {

    private final User user;

    public CustomAuthentication(User user) {
        this.user = user;
    }


    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return user;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return user.getLogin();
    }
}
