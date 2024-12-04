package net.rafael.api_library.main_project.Controllers;

import jakarta.validation.Valid;
import net.rafael.api_library.main_project.Dto.BookDto;
import net.rafael.api_library.main_project.Dto.SearchBookDto;
import net.rafael.api_library.main_project.Exceptions.DuplicatedRegisterException;
import net.rafael.api_library.main_project.Mapper.BookMapper;
import net.rafael.api_library.main_project.Models.Book;
import net.rafael.api_library.main_project.Models.BookGenres;
import net.rafael.api_library.main_project.Services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


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

    @GetMapping("/search")
    public ResponseEntity<List<SearchBookDto>> searchBook(@RequestParam(value = "isbn",required = false) String isbn, @RequestParam(value = "title",required = false) String title, @RequestParam(value = "author",required = false) String author,@RequestParam(value = "genre",required = false) BookGenres genre) {
        var result = service.searchWithFilters(isbn,title,genre);
        var list =  result.stream().map(bookMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        return service.findById(UUID.fromString(id))
                .map(book -> {
                    service.deleteBook(book);
                    return ResponseEntity.noContent().build();
                }).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable("id")String id,@RequestBody @Valid BookDto dto) {
        return service.findById(UUID.fromString(id)).map(book -> {
            Book entity = bookMapper.toEntity(dto);
            book.setPublicationDate(entity.getPublicationDate());
            book.setIsbn(entity.getIsbn());
            book.setPrice(entity.getPrice());
            book.setGenre(entity.getGenre());
            book.setTitle(entity.getTitle());
            book.setAuthor(entity.getAuthor());

            service.updateBook(book);

            return ResponseEntity.noContent().build();
        }).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PatchMapping("/updatePartially/{id}")
    public ResponseEntity<SearchBookDto> updateBookPartially(@PathVariable("id") UUID id, @RequestBody @Valid BookDto dto) {
        return service.findById(id)
                .map(existingBook -> {
                    if (dto.isbn() != null && !dto.isbn().isBlank()) {
                        existingBook.setIsbn(dto.isbn());
                    }
                    if (dto.title() != null && !dto.title().isBlank()) {
                        existingBook.setTitle(dto.title());
                    }
                    if (dto.publicationDate() != null) {
                        existingBook.setPublicationDate(dto.publicationDate());
                    }
                    if (dto.genre() != null) {
                        existingBook.setGenre(dto.genre());
                    }
                    if (dto.price() != null) {
                        existingBook.setPrice(dto.price());
                    }
                    service.saveBook(existingBook);
                    return ResponseEntity.ok(bookMapper.toDTO(existingBook));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
