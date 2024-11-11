package net.rafael.api_library.main_project.Dto;

import net.rafael.api_library.main_project.Models.Author;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO(UUID id, String name, LocalDate birthDate, String from) {
    public Author mapForAuthor() {
        Author author = new Author();
        author.setName(this.name);
        author.setBirthDate(this.birthDate);
        author.setFrom(this.from);
        return author;
    }
}
