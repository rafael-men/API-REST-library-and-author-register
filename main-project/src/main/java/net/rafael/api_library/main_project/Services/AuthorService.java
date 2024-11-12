package net.rafael.api_library.main_project.Services;


import net.rafael.api_library.main_project.Models.Author;
import net.rafael.api_library.main_project.Repository.AuthorRepository;
import net.rafael.api_library.main_project.Validations.AuthorValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorValidator validator;

    public AuthorService(AuthorRepository authorRepository, AuthorValidator validator) {
        this.authorRepository = authorRepository;
        this.validator = validator;
    }


    public Author save(Author author) {
        validator.validate(author);
        return authorRepository.save(author);
    }



    public Optional<Author> findById(UUID id) {
        return authorRepository.findById(id);
    }

    public void deleteAuthor(Author author) {
        authorRepository.delete(author);
    }

    public List<Author> searchWithFilters(String name,String from) {
        if(name != null && from != null) {
            return authorRepository.findByNameAndFrom(name,from);
        }
        if (name != null) {
            return authorRepository.findByName(name);
        }
        if (from != null) {
            return authorRepository.findByFrom(from);
        }
        return authorRepository.findAll();
    }

    public void updateAuthor(Author author) {
        if(author.getId() == null) {
            throw new IllegalArgumentException("Author must be saved in the database");
        }
        validator.validate(author);
        authorRepository.save(author);
    }
}
