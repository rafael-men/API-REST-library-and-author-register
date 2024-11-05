package net.rafael.api_library.main_project.Services;

import net.rafael.api_library.main_project.Models.Author;
import net.rafael.api_library.main_project.Repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService (AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }
}
