package net.rafael.api_library.main_project.Services;


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

    public Book saveBook(Book book) {
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
}
