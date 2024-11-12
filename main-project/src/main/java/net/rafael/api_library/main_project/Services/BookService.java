package net.rafael.api_library.main_project.Services;

import net.rafael.api_library.main_project.Models.Author;
import net.rafael.api_library.main_project.Models.Book;
import net.rafael.api_library.main_project.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByAuthor(Author author) {
        return bookRepository.findByAuthorId(author);
    }

    public Optional<Book> updateBook(UUID id, Book bookDetails) {
        return bookRepository.findById(id).map(book -> {
            book.setIsbn(bookDetails.getIsbn());
            book.setTitle(bookDetails.getTitle());
            book.setPublicationDate(bookDetails.getPublicationDate());
            book.setGenre(bookDetails.getGenre());
            book.setPrice(bookDetails.getPrice());
            book.setAuthor(bookDetails.getAuthor());
            return bookRepository.save(book);
        });
    }
}
