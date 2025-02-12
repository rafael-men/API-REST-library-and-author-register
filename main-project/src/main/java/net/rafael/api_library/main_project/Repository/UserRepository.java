package net.rafael.api_library.main_project.Repository;

import net.rafael.api_library.main_project.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByLogin(String login);

    User findByEmail(String email);
}
