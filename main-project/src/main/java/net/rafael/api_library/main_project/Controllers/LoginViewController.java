package net.rafael.api_library.main_project.Controllers;

import net.rafael.api_library.main_project.Security.CustomAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginViewController {

    @GetMapping("/login")
    public String loginPage() {
        return "Login";
    }

    @GetMapping("/")
    @ResponseBody
    public String homePage(Authentication authentication) {
        if(authentication instanceof CustomAuthentication customAuth) {
            System.out.println(customAuth.getName());
        }
        return "Olá " + authentication.getName();
    }
}
