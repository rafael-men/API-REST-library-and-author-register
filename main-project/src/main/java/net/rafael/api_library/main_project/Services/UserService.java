package net.rafael.api_library.main_project.Services;

import net.rafael.api_library.main_project.Models.User;
import net.rafael.api_library.main_project.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(User user) {
        var senha = user.getPassword();
        user.setPassword(passwordEncoder.encode(senha));
        repository.save(user);
    }

    public User findByLogin(String login) {
        return repository.findByLogin(login);
    }
}
