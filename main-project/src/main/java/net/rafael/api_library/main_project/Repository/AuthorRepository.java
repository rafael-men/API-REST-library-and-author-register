package net.rafael.api_library.main_project.Repository;

import net.rafael.api_library.main_project.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;


public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
