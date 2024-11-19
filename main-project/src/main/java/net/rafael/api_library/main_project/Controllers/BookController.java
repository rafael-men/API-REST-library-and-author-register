package net.rafael.api_library.main_project.Controllers;

import jakarta.validation.Valid;
import net.rafael.api_library.main_project.Dto.BookDto;
import net.rafael.api_library.main_project.Dto.SearchBookDto;
import net.rafael.api_library.main_project.Exceptions.DuplicatedRegisterException;
import net.rafael.api_library.main_project.Mapper.BookMapper;
import net.rafael.api_library.main_project.Models.Book;
import net.rafael.api_library.main_project.Services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/books")
public class BookController implements GenericController{

    private final BookService service;
    private final BookMapper bookMapper;


    public BookController(BookService service, BookMapper bookMapper) {
        this.service = service;
        this.bookMapper = bookMapper;
    }

    @GetMapping
    public ResponseEntity<List<SearchBookDto>> getAllBooks() {
        List<Book> books = service.findAllBooks();
        List<SearchBookDto> bookDtos = books.stream()
                .map(bookMapper::toDTO)
                .toList();
        return ResponseEntity.ok(bookDtos);
    }

    @PostMapping("/new")
    public ResponseEntity<Object> createBook(@RequestBody @Valid BookDto dto) {
        try {
            Book book = bookMapper.toEntity(dto);
            service.saveBook(book);
            var url = generateHeaderLocation(book.getId());
            return ResponseEntity.created(url).build();
        } catch (DuplicatedRegisterException e) {
            return (ResponseEntity<Object>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SearchBookDto> getBooksbyId(@PathVariable("id") String id) {
        return service.findById(UUID.fromString(id))
                .map(book -> {
                    var dto = bookMapper.toDTO(book);
                    return ResponseEntity.ok(dto);
                }).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        return service.findById(UUID.fromString(id))
                .map(book -> {
                    service.deleteBook(book);
                    return ResponseEntity.noContent().build();
                }).orElseGet(()-> ResponseEntity.notFound().build());
    }
}
