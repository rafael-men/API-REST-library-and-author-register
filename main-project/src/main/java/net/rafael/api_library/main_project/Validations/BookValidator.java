package net.rafael.api_library.main_project.Validations;

import net.rafael.api_library.main_project.Exceptions.DuplicatedRegisterException;
import net.rafael.api_library.main_project.Models.Book;
import net.rafael.api_library.main_project.Repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookValidator {

    private final BookRepository repository;

    public BookValidator(BookRepository repository) {
        this.repository = repository;
    }

    public void validateBook(Book book) {
        if(existsWithIsbn(book)) {
            throw new DuplicatedRegisterException("ISBN already registered!");
        }
    }

    public boolean existsWithIsbn(Book book) {
        Optional<Book> bookFound = repository.findByIsbn(book.getIsbn());
        if(book.getId() == null) {
            return bookFound.isPresent();
        }
        return bookFound.map(Book::getId).stream().anyMatch(id -> !id.equals(book.getId()));
    }
}
