package net.rafael.api_library.main_project.Controllers;


import net.rafael.api_library.main_project.Models.Book;
import net.rafael.api_library.main_project.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookDetails(@PathVariable("id") String id) {
        Optional<Book> book = repository.findById(UUID.fromString(id));
        return book.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/new")
    public ResponseEntity<Book> createBook (@RequestBody Book book) {
        Book savedBook = repository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }
}
