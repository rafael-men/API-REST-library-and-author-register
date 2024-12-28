package net.rafael.api_library.main_project.Controllers;

<<<<<<< HEAD
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
=======
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
>>>>>>> a75693c09ee9223d8a8755cccb1bf0fb32bc9c0a

@Controller
public class LoginViewController {

    @GetMapping("/login")
    public String loginPage() {
        return "LoginPage";
    }
<<<<<<< HEAD

    @GetMapping("/")
    @ResponseBody
    public String HomePage(Authentication authentication) {
        return "Hello " + authentication.getName() + " you are now connected.";
    }
=======
>>>>>>> a75693c09ee9223d8a8755cccb1bf0fb32bc9c0a
}
