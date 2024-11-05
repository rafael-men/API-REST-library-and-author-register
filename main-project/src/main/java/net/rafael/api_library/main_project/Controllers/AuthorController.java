package net.rafael.api_library.main_project.Controllers;


import net.rafael.api_library.main_project.Dto.AuthorDTO;
import net.rafael.api_library.main_project.Models.Author;
import net.rafael.api_library.main_project.Services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @PostMapping("/new")
    public ResponseEntity<?> saveAuthor(@RequestBody  AuthorDTO author) {

        Author authorEntity = author.mapForAuthor();
        service.save(authorEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("id")
                .buildAndExpand(authorEntity.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
