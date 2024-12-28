package net.rafael.api_library.main_project.Controllers;


import net.rafael.api_library.main_project.Dto.AuthorDTO;
import net.rafael.api_library.main_project.Exceptions.DuplicatedRegisterException;
import net.rafael.api_library.main_project.Models.Author;
import net.rafael.api_library.main_project.Services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
=======
import org.springframework.security.access.prepost.PreAuthorize;
>>>>>>> a75693c09ee9223d8a8755cccb1bf0fb32bc9c0a
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/author")
public class AuthorController implements GenericController{


    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping
<<<<<<< HEAD
=======
    @PreAuthorize("hasRole('ADMIN')")
>>>>>>> a75693c09ee9223d8a8755cccb1bf0fb32bc9c0a
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<Author> authors = service.findAll();
        List<AuthorDTO> authorsDto = authors.stream()
                .map(author -> new AuthorDTO(author.getId(), author.getName(), author.getBirthDate(), author.getFrom()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(authorsDto);
    }


    @PostMapping("/new")
<<<<<<< HEAD
=======
    @PreAuthorize("hasRole('ADMIN')")
>>>>>>> a75693c09ee9223d8a8755cccb1bf0fb32bc9c0a
    public ResponseEntity<Void> saveAuthor(@RequestBody  AuthorDTO author) {
        try {
            Author authorEntity = author.mapForAuthor();
            service.save(authorEntity);

            URI location = generateHeaderLocation(author.id());
            return ResponseEntity.created(location).build();
        } catch (DuplicatedRegisterException e){
            String errorMessage = "Fatal Error conflict: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.CONFLICT).header("Error-Message", errorMessage).build();
        }
    }

    @GetMapping("/{id}")
<<<<<<< HEAD
=======
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
>>>>>>> a75693c09ee9223d8a8755cccb1bf0fb32bc9c0a
    public ResponseEntity<AuthorDTO> acquireDetails(@PathVariable("id") String id) {
        var AuthorId = UUID.fromString(id);
        Optional<Author> author = service.findById(AuthorId);
        if(author.isPresent()) {
            Author entity = author.get();
            AuthorDTO dto = new AuthorDTO(author.get().getId(), author.get().getName(),author.get().getBirthDate(),author.get().getFrom());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
<<<<<<< HEAD
=======
    @PreAuthorize("hasRole('ADMIN')")
>>>>>>> a75693c09ee9223d8a8755cccb1bf0fb32bc9c0a
    public ResponseEntity<Void> deleteAuthorById(@PathVariable("id") String id) {
        var idAuthor = UUID.fromString(id);
        Optional<Author> author = service.findById(idAuthor);
        if(author.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteAuthor(author.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filtersearch")
<<<<<<< HEAD
=======
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
>>>>>>> a75693c09ee9223d8a8755cccb1bf0fb32bc9c0a
    public ResponseEntity<List<AuthorDTO>>  searchAuthorWithFilters(@RequestParam(value = "name",required = false) String name,@RequestParam(value = "from",required = false) String from) {
        List<Author> result = service.searchWithFilters(name, from);
        List<AuthorDTO> list = result.stream().map(author -> new AuthorDTO(author.getId(), author.getName(), author.getBirthDate(),author.getFrom())).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PutMapping("/update/{id}")
<<<<<<< HEAD
=======
    @PreAuthorize("hasRole('ADMIN')")
>>>>>>> a75693c09ee9223d8a8755cccb1bf0fb32bc9c0a
    public ResponseEntity<Void> updateAuthor(@PathVariable("id") String id, @RequestBody() AuthorDTO dto) {
        var idAuthor = UUID.fromString(id);
        Optional<Author> author = service.findById(idAuthor);
        if(author.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var authortwo = author.get();
        authortwo.setName(dto.name());
        authortwo.setFrom(dto.from());
        authortwo.setBirthDate(dto.birthDate());

        service.updateAuthor(authortwo);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/HalfUpdate/{id}")
<<<<<<< HEAD
=======
    @PreAuthorize("hasRole('ADMIN')")
>>>>>>> a75693c09ee9223d8a8755cccb1bf0fb32bc9c0a
    public ResponseEntity<Void> partiallyUpdateAuthor(@PathVariable("id") String id, @RequestBody AuthorDTO dto) {
        var idAuthor = UUID.fromString(id);
        Optional<Author> author = service.findById(idAuthor);

        if (author.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var existingAuthor = author.get();

        if (dto.name() != null && !dto.name().isBlank()) {
            existingAuthor.setName(dto.name());
        }
        if (dto.from() != null && !dto.from().isBlank()) {
            existingAuthor.setFrom(dto.from());
        }
        if (dto.birthDate() != null) {
            existingAuthor.setBirthDate(dto.birthDate());
        }

        service.updateAuthor(existingAuthor);
        return ResponseEntity.noContent().build();
    }


}
