package net.rafael.api_library.main_project.Repository;

import net.rafael.api_library.main_project.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface AuthorRepository extends JpaRepository<Author, UUID> {

    List<Author> findByName(String name);
    List<Author> findByFrom(String from);
    List<Author> findByNameAndFrom(String name,String from);
    Optional<Author> findByNameAndBirthDateAndFrom (String name, LocalDate birthDate,String from);
}
