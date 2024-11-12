package net.rafael.api_library.main_project.Validations;

import net.rafael.api_library.main_project.Exceptions.DuplicatedRegisterException;
import net.rafael.api_library.main_project.Models.Author;
import net.rafael.api_library.main_project.Repository.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorValidator {


    private final AuthorRepository repository;

    public AuthorValidator(AuthorRepository repository) {
        this.repository = repository;
    }


    public void validate(Author author) {
        if(existsOrNot(author)) {
            throw new DuplicatedRegisterException("This author is already registered.");
        }
    }

    private boolean existsOrNot(Author author) {
        Optional<Author> encounteredAuthor = repository.findByNameAndBirthDateAndFrom(author.getName(),author.getBirthDate(),author.getFrom());
        if (encounteredAuthor.isEmpty()) {
            return false;
        }

        if(author.getId() == null) {
            return true;
        }

        return author.getId().equals(encounteredAuthor.get().getId()) && encounteredAuthor.isPresent();
    }
}
