package net.rafael.api_library.main_project.Controllers;

import jakarta.validation.Valid;
import net.rafael.api_library.main_project.Dto.BookDto;
import net.rafael.api_library.main_project.Exceptions.DuplicatedRegisterException;
import net.rafael.api_library.main_project.Repository.BookRepository;
import net.rafael.api_library.main_project.Repository.AuthorRepository;
import net.rafael.api_library.main_project.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;


    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping("/new")
    public ResponseEntity<Object> createBook(@RequestBody @Valid BookDto dto) {
        try {
            return ResponseEntity.ok(dto);
        } catch (DuplicatedRegisterException e) {
            return (ResponseEntity<Object>) ResponseEntity.status(HttpStatus.BAD_REQUEST);        }
    }
}
