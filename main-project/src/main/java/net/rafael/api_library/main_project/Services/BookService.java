package net.rafael.api_library.main_project.Services;


import net.rafael.api_library.main_project.Models.Book;
import net.rafael.api_library.main_project.Models.BookGenres;
import net.rafael.api_library.main_project.Repository.BookRepository;
import net.rafael.api_library.main_project.Specs.BookSpecs;
import net.rafael.api_library.main_project.Validations.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    private final BookValidator validator;

    public BookService(BookValidator validator) {
        this.validator = validator;
    }

    public Book saveBook(Book book) {
       validator.validateBook(book);
       return  bookRepository.save(book);
    }

    public Optional<Book> findById(UUID id) {
        return bookRepository.findById(id);
    }

    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }
    public List<Book> searchWithFilters(String isbn, String title, BookGenres genre) {
        Specification<Book> specs = Specification.where(BookSpecs.isbnEqual(isbn)).and(BookSpecs.titleLike(title)).and(BookSpecs.genderEqual(genre));
        if (isbn != null) {
            specs = specs.and(BookSpecs.isbnEqual(isbn));
        }
        if(title != null) {
            specs = specs.and(BookSpecs.titleLike(title));
        }
        if (genre != null) {
            specs = specs.and(BookSpecs.genderEqual(genre));
        }
        return bookRepository.findAll(BookSpecs.isbnEqual(isbn));
    }

    public void updateBook(Book book) {

        if(book.getId() == null) {
            throw new IllegalArgumentException("The Book must exist");
        }

        validator.validateBook(book);
        bookRepository.save(book);
    }
}
