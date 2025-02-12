package net.rafael.api_library.main_project.Security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.rafael.api_library.main_project.Models.User;
import net.rafael.api_library.main_project.Services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class LoginSocialSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    private static final String DEFAULT_PASSWORD = "123";
    private final UserService service;
    private final PasswordEncoder encoder;

    public LoginSocialSuccessHandler(UserService service, PasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        OAuth2AuthenticationToken auth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = auth2AuthenticationToken.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        User user = service.findByEmail(email);

        if(user == null) {

            user = saveUserOnBase(email);
        }

        authentication = new CustomAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        super.onAuthenticationSuccess(request,response,authentication);
    }

    private User saveUserOnBase(String email) {
        var user = new User();
        user.setEmail(email);
        user.setLogin(findLoginByEmail(email));
        user.setPassword(encoder.encode(DEFAULT_PASSWORD));
        user.setRoles(List.of("ADMIN"));
        service.save(user);
        return user;
    }

    private String findLoginByEmail(String email) {
        return email.substring(0, email.indexOf("@"));
    }


}
