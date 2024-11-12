package net.rafael.api_library.main_project.Controllers;



import net.rafael.api_library.main_project.Models.Book;
import net.rafael.api_library.main_project.Repository.AuthorRepository;
import net.rafael.api_library.main_project.Repository.BookRepository;
import net.rafael.api_library.main_project.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository repository;
    private BookService bookService;
    private AuthorRepository authorRepository;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/library")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/library/{id}")
    public ResponseEntity<Book> getBookDetails(@PathVariable("id") String id) {
        Optional<Book> book = repository.findById(UUID.fromString(id));
        return book.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/new")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        if (book.getAuthor() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

        if (book.getAuthor().getId() == null) {
            authorRepository.save(book.getAuthor());
        }

        Book savedBook = repository.save(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }





    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable UUID id, @RequestBody Book bookDetails) {
        Optional<Book> updatedBook = bookService.updateBook(id, bookDetails);
        return updatedBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


}
