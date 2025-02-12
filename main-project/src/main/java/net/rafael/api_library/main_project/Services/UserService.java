package net.rafael.api_library.main_project.Services;


import net.rafael.api_library.main_project.Models.User;
import net.rafael.api_library.main_project.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public void save (User user) {
        var password = user.getPassword();
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
    }

    public User findByLogin (String login) {
        return userRepository.findByLogin(login);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
