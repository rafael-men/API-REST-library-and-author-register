package net.rafael.api_library.main_project.Dto;

import jakarta.validation.constraints.NotBlank;
import net.rafael.api_library.main_project.Models.Author;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO(UUID id, @NotBlank(message = "Obrigatory field") String name, LocalDate birthDate, @NotBlank(message =
        "Obrigatory field") String from) {
    public Author mapForAuthor() {
        Author author = new Author();
        author.setName(this.name);
        author.setBirthDate(this.birthDate);
        author.setFrom(this.from);
        return author;
    }
}
